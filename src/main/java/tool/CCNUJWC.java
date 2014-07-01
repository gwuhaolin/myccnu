package tool;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 1/18/14
 * Time: 11:22 AM
 */

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * 教务处网站相关操作
 */
public class CCNUJWC {


    /**
     * 查课表
     */
    public static final String URL_KB = "http://jwc.ccnu.edu.cn/stuClass.aspx";


    /**
     * 查最新的课表
     *
     * @param XH 学号
     * @param MM 密码
     * @return 课表的HTML
     */
    public static String getKB(String XH, String MM) throws Exception {
        Map<String, String> cookies = getCookie(XH, MM);
        Connection connection = Jsoup.connect(URL_KB);
	    connection.userAgent(R.USER_AGENT);
	    connection.timeout(R.ConnectTimeout);
        connection.cookies(cookies);
        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            throw new Exception("学校教务处服务器繁忙");
        }
        return document.getElementById("GridView1").toString();
    }


    /**
     * 获得ASP.NET_SessionId Cookie
     *
     * @param XH
     * @param MM
     */
    public static Map<String, String> getCookie(String XH, String MM) throws Exception {
        Connection connection = Jsoup.connect("http://portal.ccnu.edu.cn/roamingAction.do?appId=JWCCX");
        connection.timeout(R.ConnectTimeout);
	    connection.userAgent(R.USER_AGENT);
	    connection.cookies(CCNUPortal.getCookie(XH, MM)).get();
        return connection.response().cookies();
    }

}
