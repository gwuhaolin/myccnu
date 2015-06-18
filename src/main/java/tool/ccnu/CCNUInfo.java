package tool.ccnu;

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
 * Created by wuhaolin on 7/7/14.
 * :去信息门户抓取信息
 */
public class CCNUInfo {

    private static final Logger log = LoggerFactory.getLogger(CCNUInfo.class);

    /**
     * 获得同学的详细信息
     *
     * @param XH 学号
     * @param MM 密码
     * @return 返回信息的HTML文档, 如果获取失败就返回null
     */
    public static Document spiderStudentInfo(String XH, String MM) throws NetworkException, ValidateException {
        Connection connection = Jsoup.connect("http://portal.ccnu.edu.cn/roamingAction.do?appId=HSXG");
        Map<String, String> cookiesFromPortal = CCNUPortal.getCookie(XH, MM);
        connection.userAgent(R.USER_AGENT);
        connection.cookies(cookiesFromPortal);
        connection.timeout(R.ConnectTimeout);
        connection.ignoreHttpErrors(true);
        connection.followRedirects(false);
        try {
            connection.get();
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new NetworkException("信息门户异常");
        }
        String newURL = connection.response().header("Location");
        try {
            connection = Jsoup.connect(newURL);
            connection.userAgent(R.USER_AGENT);
            connection.ignoreHttpErrors(true);
            connection.followRedirects(false);
            connection.get();
        } catch (Exception e) {
            log.error(XH + Arrays.toString(e.getStackTrace()));
            throw new NetworkException("学工部信息系统异常");
        }
        String JsessionIdValue = connection.response().cookie("JSESSIONID");
        Document re = null;
        if (JsessionIdValue != null) {
            connection = Jsoup.connect("http://202.114.32.143/ccnuxg/xg/studentInfo.do?method=getStudentInfo");
            try {
                connection.cookie("JSESSIONID", JsessionIdValue);
                re = connection.post();
            } catch (IOException e) {
                log.error(Arrays.toString(e.getStackTrace()));
            }
        }
        return re;
    }

    /**
     * 获得老师的详细信息
     * TODO 连接超时
     *
     * @param XH 信息门户的账号
     * @param MM 信息门户的密码
     * @return 返回信息的HTML文档
     */
    public static Document spiderTeacherInfo(String XH, String MM) throws Exception {
        Connection connection = Jsoup.connect("http://portal.ccnu.edu.cn/roamingAction.do?appId=RSXT");
        Map<String, String> cookiesFromPortal = CCNUPortal.getCookie(XH, MM);
        connection.userAgent(R.USER_AGENT);
        connection.cookies(cookiesFromPortal);
        connection.timeout(R.ConnectTimeout);
        connection.ignoreHttpErrors(true);
        connection.followRedirects(false);
        try {
            connection.get();
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new NetworkException("信息门户异常");
        }
        String newURL = connection.response().header("Location");
        try {
            connection = Jsoup.connect(newURL);
            connection.userAgent(R.USER_AGENT);
            connection.ignoreHttpErrors(true);
            connection.followRedirects(false);
            connection.get();
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new NetworkException("学工部信息系统异常");
        }
        String JsessionIdValue = connection.response().cookie("JSESSIONID");
        Document re = null;
        connection = Jsoup.connect("http://202.114.32.145/ccnurs/rskEmployeeInput.do?method=modifySelfInfo&init=no&send=yes");
        connection.cookie("JSESSIONID", JsessionIdValue);
        connection.timeout(R.ConnectTimeout * 2);
        try {
            re = connection.post();
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return re;
    }

}
