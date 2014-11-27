package tool.ccnu;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 1/18/14
 * Time: 11:22 AM
 */

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.NetworkException;
import tool.R;
import tool.ValidateException;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * 教务处网站相关操作
 */
public class CCNUJWC {

    private static final Logger log = LoggerFactory.getLogger(CCNUJWC.class);


    /**
     * 查课表
     */
    private static final String URL_KB = "http://jwc.ccnu.edu.cn/stuClass.aspx";


    /**
     * 查最新的课表
     *
     * @param XH 学号
     * @param MM 密码
     * @return 课表的HTML
     * @throws tool.NetworkException
     * @throws tool.ValidateException
     */
    public static String getKB(String XH, String MM) throws NetworkException, ValidateException {
        Map<String, String> cookies = getCookie(XH, MM);
        Connection connection = Jsoup.connect(URL_KB);
        connection.userAgent(R.USER_AGENT);
        connection.timeout(R.ConnectTimeout);
        connection.cookies(cookies);
        Document document;
        try {
            document = connection.get();
        } catch (IOException e) {
            throw new NetworkException("学校教务处服务器繁忙");
        }
        return document.getElementById("GridView1").toString();
    }


    /**
     * 获得ASP.NET_SessionId Cookie
     *
     * @param XH 学号
     * @param MM 密码
     * @throws tool.NetworkException  当发生网络异常时
     * @throws tool.ValidateException 当身份验证失败时
     */
    public static Map<String, String> getCookie(String XH, String MM) throws NetworkException, ValidateException {
        Connection connection = Jsoup.connect("http://portal.ccnu.edu.cn/roamingAction.do?appId=JWCCX");
        connection.timeout(R.ConnectTimeout);
        connection.userAgent(R.USER_AGENT);
        Map<String, String> cookies = CCNUPortal.getCookie(XH, MM);
        try {
            connection.cookies(cookies).get();
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new NetworkException("教务处服务器繁忙");
        }
        Map<String, String> reCookies = connection.response().cookies();
        if (reCookies.size() < 1) {
            throw new NetworkException("学校封锁了我们的请求,等下再试试吧");
        }
        return reCookies;
    }

}
