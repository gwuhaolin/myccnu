package study.book;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tool.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 14-1-29
 * Time: 上午10:06
 */

/**
 * 查看我以借书
 */
public class MyLib {
	public static final String URL_Login = "http://202.114.34.15/reader/redr_verify.php";
	public static final String URL_MyLibBooks = "http://202.114.34.15/reader/book_lst.php";
	public static final String CookieName = "PHPSESSID";


	/**
	 * 发生网络异常时抛出异常
	 *
	 * @param XH
	 * @param MM
	 * @return
	 * @throws Exception
	 */
	public static String getCookie(String XH, String MM) throws Exception {
		Connection connection = Jsoup.connect(URL_Login);
		connection.userAgent(R.USER_AGENT);
		connection.data("number", XH);
		connection.data("passwd", MM);
		connection.data("select", "cert_no");
		connection.post();
		return connection.response().cookie(CookieName);
	}

	/**
	 * 用帐号密码去图书馆获得我的书
	 *
	 * @param XH
	 * @param MM
	 * @return 如果密码错误或者没有借书返回null
	 */
	public static List<MyLibBook> get(String XH, String MM) {
		try {
			String cookie = getCookie(XH, MM);
			Connection connection = Jsoup.connect(URL_MyLibBooks);
			connection.userAgent(R.USER_AGENT);
			connection.cookie(CookieName, cookie);
			Document document = connection.get();
//			System.out.println(document);
			Elements trs = document.getElementsByClass("table_line").first().getElementsByTag("tr");
			List<MyLibBook> re = new ArrayList<MyLibBook>();
			re.add(new MyLibBook().setTitle(cookie));
			for (int i = 1; i < trs.size(); i++) {
				MyLibBook myLibBook = new MyLibBook();
				myLibBook.setIndex(i);
				Elements tds = trs.get(i).getElementsByTag("td");
				Element theA = tds.get(1).getElementsByTag("a").first();
				myLibBook.setTitle(theA.text());
				myLibBook.setBookInfoURL("http://202.114.34.15/reader/" + theA.attr("href"));
				myLibBook.setGetTime(tds.get(2).text());
				myLibBook.setBackTime(tds.get(3).text());
				myLibBook.setXJJavaStriptFunction(tds.last().getElementsByTag("input").attr("onclick"));
				re.add(myLibBook);
			}
			return re;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 续借书
	 *
	 * @param barcode
	 * @param check
	 * @return
	 * @throws Exception
	 */
	public static String renew(String barcode, String check, String time, String cookie) {
//		System.out.println("B="+barcode+";C="+check+";Cookie="+cookie);
		Connection connection = Jsoup.connect("http://202.114.34.15/reader/ajax_renew.php");
		connection.userAgent(R.USER_AGENT);
		connection.cookie(CookieName, cookie);
		connection.data("bar_code", barcode);
		connection.data("check", check);
		connection.data("time", time);
		try {
			return connection.get().toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "网络异常";
		}
	}

	/**
	 * 判断该密码是否正确
	 *
	 * @param XH
	 * @param MM
	 * @return
	 */
	public static boolean passwordIsOk(String XH, String MM) {
		try {
			String cookie = getCookie(XH, MM);
			Connection connection = Jsoup.connect(URL_MyLibBooks);
			connection.userAgent(R.USER_AGENT);
			connection.cookie(CookieName, cookie);
			Document document = connection.get();
			String location = document.location();
			return location.equals("http://202.114.34.15/reader/book_lst.php");
		} catch (Exception e) {
			return false;
		}
	}

	public static class MyLibBook implements Comparable<MyLibBook> {
		String title;
		String getTime;
		String backTime;
		String XJJavaStriptFunction;
		String BookInfoURL;
		int index;

		public String getTitle() {
			return title;
		}

		public MyLibBook setTitle(String title) {
			this.title = title;
			return this;
		}

		public String getGetTime() {
			return getTime;
		}

		public void setGetTime(String getTime) {
			this.getTime = getTime;
		}

		public String getBackTime() {
			return backTime;
		}

		public void setBackTime(String backTime) {
			this.backTime = backTime;
		}

		public String getXJJavaStriptFunction() {
			return XJJavaStriptFunction;
		}

		public void setXJJavaStriptFunction(String XJJavaStriptFunction) {
			this.XJJavaStriptFunction = XJJavaStriptFunction;
		}

		@Override
		public int compareTo(MyLibBook o) {
			return this.backTime.compareTo(o.getBackTime());
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getBookInfoURL() {
			return BookInfoURL;
		}

		public void setBookInfoURL(String bookInfoURL) {
			BookInfoURL = bookInfoURL;
		}
	}

	/**
	 * 向图书馆荐购该图书
	 *
	 * @param bookName 书名
	 * @param who      荐购人
	 * @param press    出版社
	 * @param lan      语种
	 * @param reson    荐购原因
	 * @param email    荐购人邮件
	 * @param cookie   cookie值验证身份用
	 * @return
	 */
	public static boolean JianGouBook(String bookName, String press, String lan, String XH, String MM) {
		try {
			Connection connection = Jsoup.connect("http://202.114.34.15/asord/asord_redr.php");
			String cookie = getCookie(XH, MM);
			connection.cookie(CookieName, cookie);
			connection.userAgent(R.USER_AGENT);
			connection.data("click_type","commit");
			connection.data("title", bookName);
			connection.data("a_name","学号:"+XH);
			connection.data("b_pub", press);
			connection.data("b_date","");
			connection.data("b_type", lan);//C=chinese,U=
			connection.data("b_remark","我没有在我校图书馆找到这本书,但是这本书很有价值哦!希望老师给大家带来这本书.");
			Document document= connection.get();
			if (document.getElementById("err").text().contains("成功")){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		try {
//			System.out.println(renew("T112611891","EAC99789",new Date().toString(),"j73ce4krcfpd968pekvfp6fhf7"));
//			System.out.println(get_page_XH("2012210817", "930820"));
//			System.out.println(passwordIsOk("2012210817", "9130820"));
//			System.out.println(JianGouBook("name", "华中师范大学出版社", "C", "2012210817", "930820"));
			get("2012210817","930820");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
