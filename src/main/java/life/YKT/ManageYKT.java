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
import tool.ccnu.CCNUPortal;

import java.io.IOException;
import java.util.*;

/**
 * 查询一卡通信息（余额）
 */
public class ManageYKT {

	private static final Logger log = LoggerFactory.getLogger(ManageYKT.class);

	private static final int Type_State = 0, Type_Detail = 1, Type_HelpMoney = 2, Type_KaoQin = 3;

	/**
	 * 如果抓取失败, 就从数据库缓存中获取最新的查询数据,每次拿出几个?
	 */
	private static final int MaxSize = 5;

	/**
	 * @param type    要查询的数据类型区分是一卡通状态0,消费明细1,补助2,还是刷卡考勤3
	 * @param xh      学号
	 * @param maxSize 一次性最多去除多少个
	 * @return 目前最近的数据, 如果数据库中一个也没有就返回null
	 */
	public static List<MyYktEntity> get(int type, String xh, int maxSize) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyYktEntity where type=? and xh=?");
		query.setInteger(0, type);
		query.setString(1, xh);
		query.setMaxResults(maxSize);
		List<MyYktEntity> re = query.list();
		HibernateUtil.closeSession(session);
		if (re.size() < 1) {
			return null;
		} else {
			return re;
		}
	}

	/**
	 * 获得一卡通余额,和现在的卡的使用状态
	 *
	 * @param XH 学号
	 * @param MM 密码
	 * @return 如果抓取失败, 就从数据库缓存中获取最新的查询数据,如果数据库中也没有就返回null
	 */
	public static MyYktEntity getState(String XH, String MM) {
		try {
			Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/cardinfo");
			connection.cookies(getCookies(XH, MM));
			connection.userAgent(R.USER_AGENT);
			connection.timeout(R.ConnectTimeout * 100);
			Document document;
			document = connection.get();
			Elements tds = document.getElementsByClass("baseTable").first().getElementsByTag("tr").last().getElementsByTag("td");
			//构造一个MyYktEntity保存到数据库
			MyYktEntity myYktEntity = new MyYktEntity();
			myYktEntity.setRemainMoney(tds.last().text());//目前余额
			myYktEntity.setLocation(tds.get(5).text());//目前状态,是在用还是解挂
			myYktEntity.setType(Type_State);
			myYktEntity.setTime(Tool.time_YYYY_MM_DD_HH_MM_NOW());//目前查询的时间
			HibernateUtil.addOrUpdateEntity(myYktEntity);//把查询成功的保存到数据库
			return myYktEntity;
		} catch (Exception e) {
			log.error(Arrays.toString(e.getStackTrace()));
			List<MyYktEntity> re = get(Type_State, XH, 1);
			if (re != null) {
				return re.get(0);
			} else {
				return null;
			}
		}

	}


	/**
	 * 获得一卡通从今天前4天的消费明细
	 *
	 * @param XH 学号
	 * @param MM 密码
	 * @return 如果抓取失败, 就从数据库缓存中获取最新的查询数据,如果数据库中也没有就返回null
	 */
	public static List<MyYktEntity> getDetail(String XH, String MM) {
		Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/deal");
		try {
			connection.cookies(getCookies(XH, MM));
		} catch (NetworkException | ValidateException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			return get(Type_Detail, XH, MaxSize);
		}
		connection.data("start_date", getBeforeDate(4));//起始时间
		connection.data("end_date", Tool.time_YYYY_MM_DD());//结束时间
		connection.timeout(R.ConnectTimeout * 100);
		connection.userAgent(R.USER_AGENT);
		Document document;
		try {
			document = connection.post();
		} catch (IOException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			return get(Type_Detail, XH, MaxSize);
		}
		Elements trs = document.getElementsByClass("baseTable").first().getElementsByTag("tr");
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
				oneChange.setTime(date);
				oneChange.setLocation(location);
				oneChange.setChangeMoney(changeMoney);
				oneChange.setRemainMoney(remainMoney);
				re.add(oneChange);
			} catch (Exception e) {
				log.error(Arrays.toString(e.getStackTrace()));
			}
		}
		HibernateUtil.addEntitys(re);//把查询成功的保存到数据库
		return re;
	}


	/**
	 * 获得一卡通从2013-01-01开始到现在的补助明细
	 *
	 * @param XH 学号
	 * @param MM 密码
	 * @return 如果抓取失败, 就从数据库缓存中获取最新的查询数据,如果数据库中也没有就返回null
	 */
	public static List<MyYktEntity> getHelpMoney(String XH, String MM) {
		Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/allowance");
		try {
			connection.cookies(getCookies(XH, MM));
		} catch (NetworkException | ValidateException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			return get(Type_HelpMoney, XH, MaxSize);
		}
		String curDate = Tool.time_YYYY_MM_DD();
		connection.data("start_date", curDate.split("-")[0] + "-01-01");//起始时间
		connection.data("end_date", curDate);//结束时间
		connection.timeout(R.ConnectTimeout * 100);
		connection.userAgent(R.USER_AGENT);
		Document document;
		try {
			document = connection.post();
		} catch (IOException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			return get(Type_HelpMoney, XH, MaxSize);
		}
		Elements trs = document.getElementsByClass("baseTable").first().getElementsByTag("tr");
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
				oneChange.setTime(date);
				oneChange.setLocation(location);
				oneChange.setChangeMoney(changeMoney);
				oneChange.setRemainMoney(remainMoney);
				re.add(oneChange);
			} catch (Exception e) {
				log.error(Arrays.toString(e.getStackTrace()));
			}
		}
		HibernateUtil.addEntitys(re);//把查询成功的保存到数据库
		return re;
	}

	/**
	 * 获得最近一个星期内的考勤
	 *
	 * @param XH 学号
	 * @param MM 密码
	 * @return 如果抓取失败, 就从数据库缓存中获取最新的查询数据,如果数据库中也没有就返回null
	 */
	public static List<MyYktEntity> getKaoQin(String XH, String MM) {
		Connection connection = Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/timer");
		try {
			connection.cookies(getCookies(XH, MM));
		} catch (NetworkException | ValidateException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			return get(Type_KaoQin, XH, MaxSize);
		}
		String curDate = Tool.time_YYYY_MM_DD();
		connection.data("start_date", Tool.DateFormat_YYYY_MM_DD.format(new Date(System.currentTimeMillis() - 604800000)));//起始时间,一周前
		connection.data("end_date", curDate);//结束时间
		connection.timeout(R.ConnectTimeout * 100);
		connection.userAgent(R.USER_AGENT);
		Document document;
		try {
			document = connection.post();
		} catch (IOException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			return get(Type_KaoQin, XH, MaxSize);
		}
		Elements trs = document.getElementsByClass("baseTable").first().getElementsByTag("tr");
		List<MyYktEntity> re = new LinkedList<>();
		//解析数据
		for (int i = 1; i < trs.size(); i++) {
			try {
				Elements tds = trs.get(i).getElementsByTag("td");
				String date = tds.get(1).text();
				String location = tds.get(2).text();
				MyYktEntity oneChange = new MyYktEntity();
				oneChange.setTime(date);
				oneChange.setLocation(location);
				re.add(oneChange);
			} catch (Exception e) {
				log.error(Arrays.toString(e.getStackTrace()));

			}
		}
		HibernateUtil.addEntitys(re);//把查询成功的保存到数据库
		return re;
	}


	/**
	 * 挂失解挂
	 * TODO 带调试,无法工作
	 *
	 * @param XH
	 * @param MM
	 * @return
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
			connection.data("cardid", getCardID(XH, MM));
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
	private static String getCardID(String XH, String MM) throws NetworkException, ValidateException {
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
	public static Map<String, String> getCookies(String XH, String MM) throws NetworkException, ValidateException {
		Map<String, String> CCNUPORATLcookies = CCNUPortal.getCookie(XH, MM);
		Connection connection = Jsoup.connect("http://portal.ccnu.edu.cn/roamingAction.do?appId=ECARD");
		connection.cookies(CCNUPORATLcookies);
		connection.userAgent(R.USER_AGENT);
		connection.timeout(R.ConnectTimeout);
		try {
			connection.get();
		} catch (IOException e) {
			log.error(e.getMessage());
			log.error(connection.response().toString());
			throw new NetworkException("信息门户服务器繁忙");
		}
		return connection.response().cookies();
	}

	/**
	 * 获得yyyy-MM-dd格式的系统当前时间day天前的时间
	 */
	private static String getBeforeDate(int day) {
		Date reDate = new Date(System.currentTimeMillis() - day * 3600000 * 24);
		return Tool.DateFormat_YYYY_MM_DD.format(reDate);
	}


}
