package life.YKT;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 14-1-28
 * Time: 下午5:43
 */

import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 查询一卡通信息（余额）
 */
public class ManageYKT {

    private static final Logger log = LoggerFactory.getLogger(ManageYKT.class);

    /**
     * 区分用户是做什么查询
     * 0开始界面的余额
     * 1消费明细
     * 2补助
     * 3刷卡考勤
     */
    public static final int Type_State = 0, Type_Detail = 1, Type_HelpMoney = 2, Type_KaoQin = 3;

    /**
     * 抓取获得一卡通余额,和现在的卡的使用状态,保存到数据库
     *
     * @param cookies 获取到的cookies
     * @param XH      学号
     * @return 如果抓取失败, 就返回null
     */
    private static MyYktEntity spiderState(String XH, Map<String, String> cookies) {
        try {
            Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/cardinfo");
            connection.cookies(cookies);
            connection.userAgent(R.USER_AGENT);
            connection.timeout(R.ConnectTimeout * 100);
            Document document;
            document = connection.get();
            Elements tds = document.getElementsByClass("baseTable").first().getElementsByTag("tr").last().getElementsByTag("td");
            //构造一个MyYktEntity保存到数据库
            MyYktEntity myYktEntity = new MyYktEntity();
            myYktEntity.setXh(XH);
            myYktEntity.setRemainMoney(tds.last().text());//目前余额
            myYktEntity.setLocation(tds.get(5).text());//目前状态,是在用还是解挂
            myYktEntity.setType(Type_State);
            myYktEntity.setTime(Tool.time_YYYY_MM_DD_HH_MM_NOW());//目前查询的时间
            HibernateUtil.addOrUpdateEntity(myYktEntity);//把查询成功的保存到数据库
            return myYktEntity;
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return null;
        }

    }


    /**
     * 获得一卡通从今天前4天的消费明细
     *
     * @param XH      学号
     * @param cookies 获取到的cookies
     * @return 如果抓取失败就返回null
     */
    private static List<MyYktEntity> spiderDetail(String XH, Map<String, String> cookies) {
        Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/deal");
        try {
            connection.cookies(cookies);
            connection.data("start_date", getBeforeDate(4));//起始时间
            connection.data("end_date", Tool.time_YYYY_MM_DD());//结束时间
            connection.timeout(R.ConnectTimeout * 100);
            connection.userAgent(R.USER_AGENT);
            Document document;
            document = connection.post();
            Elements trs;
            trs = document.getElementsByClass("baseTable").first().getElementsByTag("tr");
            List<MyYktEntity> re = new LinkedList<>();
            //解析数据
            for (int i = 1; i < trs.size(); i++) {
                try {
                    Elements tds = trs.get(i).getElementsByTag("td");
                    String date = tds.get(1).text();
                    String location = tds.get(2).text();
                    String changeMoney = tds.get(4).text();
                    String remainMoney = tds.get(5).text();
                    MyYktEntity oneChange = new MyYktEntity();
                    oneChange.setType(Type_Detail);
                    oneChange.setXh(XH);
                    oneChange.setTime(date);
                    oneChange.setLocation(location);
                    oneChange.setChangeMoney(changeMoney);
                    oneChange.setRemainMoney(remainMoney);
                    re.add(oneChange);
                } catch (Exception e) {
                    log.error(Arrays.toString(e.getStackTrace()));
                }
            }
            HibernateUtil.addOrUpdateEntitys(re);//把查询成功的保存到数据库
            return re;
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * 获得一卡通从2013-01-01开始到现在的补助明细
     *
     * @param XH      学号
     * @param cookies 获取到的验证cookies
     * @return 如果抓取失败, 就从数据库缓存中获取最新的查询数据,如果数据库中也没有就返回null
     */
    private static List<MyYktEntity> spiderHelpMoney(String XH, Map<String, String> cookies) {
        Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/allowance");
        try {
            connection.cookies(cookies);
            String curDate = Tool.time_YYYY_MM_DD();
            connection.data("start_date", curDate.split("-")[0] + "-01-01");//起始时间
            connection.data("end_date", curDate);//结束时间
            connection.timeout(R.ConnectTimeout * 100);
            connection.userAgent(R.USER_AGENT);
            Document document;
            document = connection.post();
            Elements trs;
            trs = document.getElementsByClass("baseTable").first().getElementsByTag("tr");
            List<MyYktEntity> re = new LinkedList<>();
            //解析数据
            for (int i = 1; i < trs.size(); i++) {
                try {
                    Elements tds = trs.get(i).getElementsByTag("td");
                    String date = tds.get(3).text();
                    String location = tds.get(4).text();//这是补助名称
                    String changeMoney = tds.get(5).text();
                    String remainMoney = tds.get(6).text();
                    MyYktEntity oneChange = new MyYktEntity();
                    oneChange.setType(Type_HelpMoney);
                    oneChange.setXh(XH);
                    oneChange.setTime(date);
                    oneChange.setLocation(location);
                    oneChange.setChangeMoney(changeMoney);
                    oneChange.setRemainMoney(remainMoney);
                    re.add(oneChange);
                } catch (Exception e) {
                    log.error(Arrays.toString(e.getStackTrace()));
                }
            }
            HibernateUtil.addOrUpdateEntitys(re);//把查询成功的保存到数据库
            return re;
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return null;
        }

    }

    /**
     * 获得最近一个星期内的考勤
     *
     * @param XH      学号
     * @param cookies 获取到的验证cookies
     * @return 如果抓取失败, 就从数据库缓存中获取最新的查询数据,如果数据库中也没有就返回null
     */
    private static List<MyYktEntity> spiderKaoQin(String XH, Map<String, String> cookies) {
        Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/timer");
        try {
            connection.cookies(cookies);
            String curDate = Tool.time_YYYY_MM_DD();
            connection.data("start_date", Tool.DateFormat_YYYY_MM_DD.format(new Date(System.currentTimeMillis() - 604800000)));//起始时间,一周前
            connection.data("end_date", curDate);//结束时间
            connection.timeout(R.ConnectTimeout * 100);
            connection.userAgent(R.USER_AGENT);
            Document document;
            document = connection.post();
            Elements trs;
            trs = document.getElementsByClass("baseTable").first().getElementsByTag("tr");
            List<MyYktEntity> re = new LinkedList<>();
            //解析数据
            for (int i = 1; i < trs.size(); i++) {
                try {
                    Elements tds = trs.get(i).getElementsByTag("td");
                    String date = tds.get(1).text();
                    String location = tds.get(2).text();
                    MyYktEntity oneChange = new MyYktEntity();
                    oneChange.setType(Type_KaoQin);
                    oneChange.setXh(XH);
                    oneChange.setTime(date);
                    oneChange.setLocation(location);
                    re.add(oneChange);
                } catch (Exception e) {
                    log.error(Arrays.toString(e.getStackTrace()));

                }
            }
            HibernateUtil.addOrUpdateEntitys(re);//把查询成功的保存到数据库
            return re;
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * 挂失解挂
     * TODO 带调试,无法工作
     *
     * @param XH 学号
     * @param MM 密码
     * @return 结果
     */
    public static String gsjg(String XH, String MM, String yktMM, boolean isJG) throws Exception {
        String url = "http://192.168.44.7:10000/sisms/index.php/person/guashi";
        if (isJG) {
            url = "http://192.168.44.7:10000/sisms/index.php/person/jiegua";
        }
        Connection connection = Jsoup.connect(url);
        try {
            connection.cookies(getCookies(XH, MM));
            connection.userAgent(R.USER_AGENT);
            connection.timeout(R.ConnectTimeout);
            connection.data("password", yktMM);
            connection.data("cardid", spiderCardID(XH, MM));
            Document document = connection.post();
            return document.toString();
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(connection.response().toString());
            throw new Exception("学校一卡通服务器繁忙");
        }
    }


    /**
     * 获得一卡通编号
     *
     * @param XH 学号
     * @param MM 密码
     * @throws tool.NetworkException  学校服务器异常
     * @throws tool.ValidateException 身份验证失败
     */
    private static String spiderCardID(String XH, String MM) throws NetworkException, ValidateException {
        Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/cardinfo");
        connection.cookies(getCookies(XH, MM));
        connection.userAgent(R.USER_AGENT);
        connection.timeout(R.ConnectTimeout * 100);
        Document document;
        try {
            document = connection.get();
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new NetworkException("一卡通服务器繁忙");
        }
        return document.getElementsByClass("baseTable").first().getElementsByTag("tr").last().getElementsByTag("td").first().text();
    }

    /**
     * 获得cookie
     *
     * @param XH 学号
     * @param MM 密码
     * @return cookies
     * @throws tool.NetworkException  学校服务器异常
     * @throws tool.ValidateException 身份验证失败
     */
    private static Map<String, String> getCookies(String XH, String MM) throws NetworkException, ValidateException {
        Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/login/getimgcode");
        connection.ignoreContentType(true);
        Map<String, String> cookies;
        try {
            connection.get();
            //获得会话cookies
            cookies = connection.response().cookies();
            //获得验证码
            byte[] img = connection.response().bodyAsBytes();
            String result = YKTORC.orc(img);
            //登入
            connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/login/dologin");
            connection.cookies(cookies);
            connection.data("username", XH);
            connection.data("password", MM);
            connection.data("usertype", "1");
            connection.data("schoolcode", "001");
            connection.data("imgcode", result);
            connection.post();
            String responseBody = connection.response().body();
            if (responseBody.contains("错误")) {
                throw new ValidateException("身份验证失败");
            }
            return cookies;
        } catch (IOException e) {
            e.printStackTrace();
            throw new NetworkException("一卡通服务器繁忙");
        }
    }

    /**
     * 获得yyyy-MM-dd格式的系统当前时间day天前的时间
     */
    private static String getBeforeDate(int day) {
        Date reDate = new Date(System.currentTimeMillis() - day * 3600000 * 24);
        return Tool.DateFormat_YYYY_MM_DD.format(reDate);
    }

    /**
     * 一次性抓取所有信息,使用同一个cookies
     * 开启一个新进程去抓取除去state的其他的信息
     *
     * @param xh      学号
     * @param cookies 密码
     * @return 获取到的state, 如果获取state失败就返回null
     */
    private static MyYktEntity spiderAll(String xh, Map<String, String> cookies) {
        MyYktEntity state = spiderState(xh, cookies);
        //开启一个新进程去抓取其他的信息
        ((Runnable) () -> {
            //每个spider都会把抓取到的数据保存到数据库
            spiderDetail(xh, cookies);
            spiderHelpMoney(xh, cookies);
            spiderKaoQin(xh, cookies);
        }).run();
        return state;
    }

    /**
     * * 当进入一卡通查询界面是要调用这个方法获取state
     * 他会去抓取最新的state并返回
     * 同时会开启一个新的进程去剩余的所有信息
     * 如果获取state失败就会从数据库缓存中获取最新的查询数据,如果数据库中也没有就返回null
     *
     * @param xh 学号
     * @param mm 密码
     * @return 如果没有抓取到数据而且数据库中也没有数据就返回null
     * @throws NetworkException  网络异常
     * @throws ValidateException 账号密码错误
     */
    public static MyYktEntity spiderAndGet(String xh, String mm) throws NetworkException, ValidateException {
        Map<String, String> cookies = getCookies(xh, mm);
        MyYktEntity re = spiderAll(xh, cookies);
        if (re == null) {
            List<MyYktEntity> list = get(Type_State, xh, 0, 1);
            if (list != null) {
                re = list.get(0);
            } else {
                re = null;
            }
        }
        return re;
    }

    /**
     * 安装时间排序返回最近的记录
     *
     * @param type    要查询的数据类型区分是一卡通状态0,消费明细1,补助2,还是刷卡考勤3
     * @param xh      学号
     * @param begin   分页查询第一条index
     * @param maxSize 一次性最多去除多少个
     * @return 目前最近的数据, 如果数据库中一个也没有就返回null
     */
    public static List<MyYktEntity> get(int type, String xh, int begin, int maxSize) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from MyYktEntity where type=? and xh=? order by time desc");
        query.setInteger(0, type);
        query.setString(1, xh);
        query.setFirstResult(begin);
        query.setMaxResults(maxSize);
        List<MyYktEntity> re = query.list();
        HibernateUtil.closeSession(session);
        if (re.size() < 1) {
            return null;
        } else {
            return re;
        }
    }

    public static String tranTypeInt(int type) {
        switch (type) {
            case Type_Detail:
                return "一卡通消费明细";
            case Type_HelpMoney:
                return "补助明细";
            case Type_KaoQin:
                return "一周考勤";
        }
        return null;
    }

    /**
     * 该请求的cookies中如果有XH就返回XH,如果没有就返回""放在第0个
     * 该请求的cookies中如果有MM就返回MM,如果没有就返回""放在第1个
     * 如果cookies中没有该cookies的话就返回"",而不是null
     */
    public static String[] getXHMMfromCookie(HttpServletRequest request) {
        String XHMM[] = {"", ""};
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return XHMM;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("XH") && cookie.getValue() != null) {
                XHMM[0] = cookie.getValue();
            } else if (cookie.getName().equals("YKTMM") && cookie.getValue() != null) {
                XHMM[1] = cookie.getValue();
            }
        }
        return XHMM;
    }

    /**
     * 把该学号密码写到cookies(更目录)中保存一月(共享Cookie)
     */
    public static void setXHMMtoCookies(HttpServletResponse response, String XH, String MM) {
        Cookie cookieXH = new Cookie("XH", XH);
        cookieXH.setPath("/");
        cookieXH.setMaxAge(R.CookiesStoreTime);
        Cookie cookieMM = new Cookie("YKTMM", MM);
        cookieMM.setPath("/");
        cookieMM.setMaxAge(R.CookiesStoreTime);
        response.addCookie(cookieXH);
        response.addCookie(cookieMM);
    }
}
