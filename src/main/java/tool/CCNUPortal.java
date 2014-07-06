package tool;

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
	public static final String URL_Login = "http://portal.ccnu.edu.cn/loginAction.do";

	/**
	 * 用学号密码去登录信息门户看是否正确
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
			return !document.toString().contains("错误");
		} catch (IOException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			return false;
		}
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

	/**
	 * 获得同学的详细信息
	 * TODO 获取信息失败 初步分析为 服务器屏蔽非浏览器端口
	 *
	 * @param XH
	 * @return 返回信息的HTML文档
	 * @throws java.lang.Exception
	 */
	public static Document getStudentInfo(String XH, String MM) throws Exception {
		Connection connection = Jsoup.connect("http://portal.ccnu.edu.cn/roamingAction.do?appId=HSXG");
		Map<String, String> cookiesFromPortal = getCookie(XH, MM);
		connection.userAgent(R.USER_AGENT);
		connection.cookies(cookiesFromPortal);
		connection.timeout(R.ConnectTimeout);
		connection.ignoreHttpErrors(true);
		connection.followRedirects(false);
		connection.get();
		String newURL = connection.response().header("Location");
		connection = Jsoup.connect(newURL);
		connection.header("User-Agent", "Mozilla/5.0");
		connection.header("Referer", "http://portal.ccnu.edu.cn/item.jsp?groupId=STU_JZD&groupSeq=4");
		connection.request().cookie("ys-west-panel", "o:collapsed=b%3A1");
		connection.ignoreHttpErrors(true);
//		connection.followRedirects(false);
		connection.get();
		Map<String, String> cookiesFromXGB = connection.response().cookies();
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		connection = Jsoup.connect("http://202.114.32.143/ccnuxg/xg/studentInfo.do?method=getStudentInfo");
		connection.cookies(cookiesFromXGB);
		connection.cookie("SECURE_AUTH_ROOT_COOKIE", "1771d2220bfc52209255b80905ddb0ec");
		connection.cookie("SECURE_AUTH_ROOT_COOKIE", "1771d2220bfc52209255b80905ddb0ec");
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		return connection.post();
	}

	/**
	 * 获得老师的详细信息
	 * TODO 待测试
	 *
	 * @param XH
	 * @return 返回信息的HTML文档
	 */
	public static Document getTeacherInfo(String XH, String MM) throws Exception {
		Connection connection = Jsoup.connect("http://portal.ccnu.edu.cn/roamingAction.do?appId=RSXT");
		connection.userAgent(R.USER_AGENT);
		connection.cookies(getCookie(XH, MM));
		connection.timeout(R.ConnectTimeout);
		connection.followRedirects(false);
		connection.get();
		String newUrl = connection.response().header("Location");
		connection = Jsoup.connect(newUrl);
		connection.get();
		Map<String, String> cookies = connection.response().cookies();

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		connection = Jsoup.connect("http://202.114.32.145/ccnurs/rskEmployeeInput.do?method=modifySelfInfo&init=no&send=yes");
		connection.cookies(cookies);
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		return connection.post();
	}

}
