package tool;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.studentInfo.ManageStudentInfo;
import tool.studentInfo.StudentinfoEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/24/14
 * Time: 7:58 PM
 */
public class Tool {
	private static Calendar calendar = Calendar.getInstance();
	private static int Week_NOW = calendar.get(Calendar.DAY_OF_WEEK);
	private static final int CookiesStoreTime = 3600 * 24 * 30 * 12;//12个月
	public static final SimpleDateFormat DateFormat_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
	private static String nowDate = DateFormat_YYYY_MM_DD.format(new Date(System.currentTimeMillis()));
	public static final SimpleDateFormat DateFormat_YYYY_MM_DD_HHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static String nowDate_HHMM = DateFormat_YYYY_MM_DD_HHMM.format(new Date(System.currentTimeMillis()));

	static {
		calendar.setTime(new Date(System.currentTimeMillis()));
		Week_NOW = calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 更新当前系统时间
	 * TODO
	 */
	public static void update() {
		System.out.println("更新系统时间");
		nowDate = DateFormat_YYYY_MM_DD.format(new Date(System.currentTimeMillis()));
		nowDate_HHMM = DateFormat_YYYY_MM_DD_HHMM.format(new Date(System.currentTimeMillis()));

		//今天是星期几
		calendar.setTime(new Date(System.currentTimeMillis()));
		Week_NOW = calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获得今天的时间格式为 yyyy-mm-dd
	 *
	 * @return
	 */
	public static String time_YYYY_MM_DD() {
		return nowDate;
	}

	/**
	 * 获得今天的时间格式为 yyyy-mm-dd HH:mm 每小时更新一次
	 *
	 * @return
	 */
	public static String time_YYYY_MM_DD_HH_MM() {
		return nowDate_HHMM;
	}

	/**
	 * 获得系统现在时间 获取准确的现在系统的时间 yyyy-MM-dd HH:mm
	 *
	 * @return
	 */
	public static String time_YYYY_MM_DD_HH_MM_NOW() {
		return DateFormat_YYYY_MM_DD_HHMM.format(new Date(System.currentTimeMillis()));
	}


	/**
	 * 获得今天是星期几
	 *
	 * @return
	 */
	public static int week_NOW() {
		return Week_NOW;
	}

	/**
	 * 该请求的cookies中如果有XH就返回XH,如果没有就返回""放在第0个
	 * 该请求的cookies中如果有MM就返回MM,如果没有就返回""放在第1个
	 * 如果cookies中没有该cookies的话就返回"",而不是null
	 *
	 * @param request
	 * @return
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
			} else if (cookie.getName().equals("MM") && cookie.getValue() != null) {
				XHMM[1] = cookie.getValue();
			}
		}
		return XHMM;
	}

	/**
	 * 判读从cookies中获得的帐号密码是否符合格式并没有判断是否正确
	 *
	 * @param XHMM
	 * @return
	 */
	public static boolean XHMMisOK(String XHMM[]) {
		return XHMM.length == 2 && XHMM[0] != null && XHMM[0].length() > 0 && XHMM[1] != null && XHMM[1].length() > 0;
	}

	/**
	 * 首先去数据库中看有没有保存该帐号密码,如果保存了就看他们是不是一样
	 * 如果数据库中没有保存就去学校信息门户验证
	 *
	 * @param XHMM
	 * @return
	 */
	public static boolean XHMMisTrue(String XHMM[]) {
		try {//首先去数据库中看有没有
			Session session = HibernateUtil.getSession();
			Query query = session.createQuery("from StudentInfoEntity where xh=?");
			query.setString(0, XHMM[0]);
			StudentinfoEntity one = (StudentinfoEntity) query.uniqueResult();
			HibernateUtil.closeSession(session);
			String password = one.getPassword();
			if (password != null) {
				if (password.equals(XHMM[1])) {
					return true;
				}
			}
		} catch (Exception e) {
		}
		return CCNUPortal.XHMMisTrue(XHMM[0], XHMM[1]);
	}

	/**
	 * 把该学号密码写到cookies中保存一月(共享Cookie)
	 *
	 * @param response
	 * @param XH
	 */
	public static void setXHMMtoCookies(HttpServletResponse response, String XH, String MM) {
		Cookie cookieXH = new Cookie("XH", XH);
		cookieXH.setPath("/");
		cookieXH.setMaxAge(CookiesStoreTime);
		Cookie cookieMM = new Cookie("MM", MM);
		cookieMM.setPath("/");
		cookieMM.setMaxAge(CookiesStoreTime);
		response.addCookie(cookieXH);
		response.addCookie(cookieMM);
	}

	/**
	 * 把该学号写到cookies中保存一月(共享Cookie)
	 *
	 * @param response
	 * @param XH
	 */
	public static void setXHtoCookies(HttpServletResponse response, String XH) {
		Cookie cookieXH = new Cookie("XH", XH);
		cookieXH.setPath("/");
		cookieXH.setMaxAge(3600 * 24 * 30);
		response.addCookie(cookieXH);
	}

	/**
	 * 删除对方浏览器上的学号密码cookies
	 *
	 * @param response
	 */
	public static void deleteXHMMCookies(HttpServletResponse response) {
		Cookie cookieXH = new Cookie("XH", null);
		cookieXH.setPath("/");
		cookieXH.setMaxAge(0);
		Cookie cookieMM = new Cookie("MM", null);
		cookieMM.setPath("/");
		cookieMM.setMaxAge(0);
		response.addCookie(cookieXH);
		response.addCookie(cookieMM);
	}

	/**
	 * 把学号密码转发到登入后界面,如果转发成功就返回true,否则返回false
	 *
	 * @param pagePath
	 * @param response
	 * @param XHMM
	 */
	public static boolean sentXHHMMtoLoginPage(String pagePath, HttpServletResponse response, HttpServletRequest request) throws IOException {
		String XHMM[] = getXHMMfromCookie(request);
		String info = request.getParameter("info");
		if (XHMMisOK(XHMM) && info == null) {
			response.sendRedirect(pagePath + "?XH=" + XHMM[0] + "&MM=" + XHMM[1]);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 先从教务处下载信息
	 * 学号密码保存到数据库中
	 *
	 * @param XH
	 * @param MM
	 */
	public static void setXHMMtoSQL(String XH, String MM) {
		StudentinfoEntity studentInfoEntity = ManageStudentInfo.DownloadFromJWC(XH, MM);
		if (studentInfoEntity==null){
			studentInfoEntity=new StudentinfoEntity(XH);
			studentInfoEntity.setPassword(MM);
		}
		HibernateUtil.addOrUpdateEntity(studentInfoEntity);
	}

	/**
	 * 用于AJAX加载js函数然后依次执行一条条函数
	 * 向jsp页面输出js函数,只需要传入函数体名称和(参数,参数)不要加;号
	 *
	 * @param js
	 */
	public static void jspWriteJSForAJAX(HttpServletResponse response, String... args) {
		try {
			Writer writer = response.getWriter();
//			writer.write("<script>\n");
			for (String oneF : args) {
				writer.write("" + oneF + ";\n");
			}
//			writer.write("</script>");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用于直接向html加载js函数然后依次执行一条条函数
	 * 向jsp页面输出js函数,只需要传入函数体名称和(参数,参数)不要加;号
	 *
	 * @param js
	 */
	public static void jspWriteJSForHTML(HttpServletResponse response, String... args) {
		StringBuilder re = new StringBuilder(
				"<!DOCTYPE html>\n" +
						"<html>\n" +
						"<head>\n" +
						"\t<meta charset=\"utf-8\"/>\n" +
						"\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"/>\n" +
						"\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\">\n" +
						"\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/lib/css/semantic.min.css\">\n" +
						"\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/lib/css/main.css\">\n" +
						"\t<script src=\"/lib/js/jquery.min.js\"></script>\n" +
						"\t<script src=\"/lib/js/semantic.min.js\"></script>\n" +
						"\t<script src=\"/lib/js/main.js\"></script>\n" +
						"</head>\n" +
						"<body>\n" +
						"<script>\n"
		);
		for (String oneF : args) {
			re.append(oneF + ";\n");
		}
		re.append("</script>\n" +
				"</body>\n" +
				"</html>");
		try {
			Writer writer = response.getWriter();
			writer.write(re.toString());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用于直接向html页面中输出执行shouldBind(msg)
	 *
	 * @param response
	 * @param msg
	 */
	public static void jspWriteJSForHTML_shouldBind(HttpServletResponse response, String msg) {
		jspWriteJSForHTML(response, "shouldBind('" + msg + "')");
	}

	/**
	 * 用于直接向html页面中输出执行alertMsg(msg)
	 *
	 * @param response
	 * @param msg
	 */
	public static void jspWriteJSForHTML_alertMsg(HttpServletResponse response, String msg) {
		jspWriteJSForHTML(response, "alertMsg('" + msg + "')");
	}

	/**
	 * **生成AJAX加载更多JS 不包含<scritp></scritp>标签
	 *
	 * @param ChangeCount **分页查询时每次取出多少个
	 * @param targetURL   **目标URL
	 * @param datas       **传入的参数   如果没有就传入 "" **如果有先加一个,然后dataName:dateValue,dataName:dateValue
	 * @return
	 */
	public static String makeAJAXLoadMoreJS(String targetURL, String datas) {
		StringBuilder re = new StringBuilder(
				"function ajaxMore(btn) {\n" +
						"$(btn).addClass('loading');\n" +
						"var begin = Number($(btn).attr('begin'));\n" +
						"begin += " + R.ChangeCount + ";\n" +
						"$.ajax({\n" +
						"type: 'POST',\n" +
						"url: '" + targetURL + "',\n" +
						"data: { begin: begin" + datas + "},\n" +
						"contentType: \"application/x-www-form-urlencoded; charset=utf-8\"\n" +
						"}).done(function (data) {\n" +
						"$(btn).removeClass('loading');\n" +
						"if (data.length < 20) {\n" +
						"$(btn).text(\"没有更多了!\");\n" +
						"$(btn).addClass('disabled');\n" +
						"} else {\n" +
						"$(btn).before(data);\n" +
						"$(btn).text(\"更多\");\n" +
						"$(btn).attr('begin', begin);\n" +
						"}\n" +
						"});\n" +
						"}"
		);
		return re.toString();
	}

	/**
	 * **生成AJAX加载更多JS 不包含<scritp></scritp>标签
	 *
	 * @param ChangeCount **分页查询时每次取出多少个
	 * @param targetURL   **目标URL
	 * @param datas       **传入的参数   如果没有就传入 "" **如果有先加一个,然后dataName:dateValue,dataName:dateValue
	 * @param javastript  如果这次执行加载更多信息成功后执行会  javastript语句
	 * @return
	 */
	public static String makeAJAXLoadMoreJS_appendJS( String targetURL, String datas, String javastript) {
		StringBuilder re = new StringBuilder(
				"function ajaxMore(btn) {\n" +
						"$(btn).addClass('loading');\n" +
						"var begin = Number($(btn).attr('begin'));\n" +
						"begin += " + R.ChangeCount + ";\n" +
						"$.ajax({\n" +
						"type: 'POST',\n" +
						"url: '" + targetURL + "',\n" +
						"data: { begin: begin" + datas + "},\n" +
						"contentType: \"application/x-www-form-urlencoded; charset=utf-8\"\n" +
						"}).done(function (data) {\n" +
						"$(btn).removeClass('loading');\n" +
						"if (data.length < 20) {\n" +
						"$(btn).text(\"没有更多了!\");\n" +
						"$(btn).addClass('disabled');\n" +
						"} else {\n" +
						"$(btn).before(data);\n" +
						"$(btn).text(\"更多\");\n" +
						"$(btn).attr('begin', begin);\n" +
						javastript + "\n" +
						"}\n" +
						"});\n" +
						"}"
		);
		return re.toString();
	}

	/**
	 * 读取http请求中的Cookies的值
	 *
	 * @param request
	 * @param CookiesName Cookies的名字
	 * @return 如果不存在该名字的Cookies就返回null
	 */
	public static String getCookieValue(HttpServletRequest request, String CookiesName) {
		Cookie cookies[] = request.getCookies();
		for (Cookie one : cookies) {
			if (one.getName().equals(CookiesName)) {
				return one.getValue();
			}
		}
		return null;
	}

	/**
	 * 向http中写入一个Cookies
	 *
	 * @param response
	 * @param cookiesName
	 * @param cookiesValue
	 */
	public static void writeCookies(HttpServletResponse response, String cookiesName, String cookiesValue) {
		response.addCookie(new Cookie(cookiesName, cookiesValue));
	}

	/**
	 * 把很长的文字折叠起来,通过点击 更多 按钮显示出剩下的文字
	 *
	 * @param orgText   要被处理的文字
	 * @param maxLength 最多被显示出来的文字的数量
	 * @return 如果 最多被显示出来的文字的数量 < 要被处理的文字的长度 就返回原文字
	 */
	public static String limitTextByJSShowMore(String orgText, int maxLength) {
		if (maxLength < orgText.length()) {
			return orgText.substring(0, maxLength) + "\t\t<button class=\"ui button mini circular\" onclick='$(this).next().fadeIn();$(this).remove();'>more</button><span style=\"display: none\">" + orgText.substring(maxLength, orgText.length()) + "</span>\n";
		} else {
			return orgText;
		}
	}

	public static void main(String[] args) {
//		setXHMMtoSQL("2012210817", "930820");
		System.out.println(time_YYYY_MM_DD_HH_MM());
	}

}

