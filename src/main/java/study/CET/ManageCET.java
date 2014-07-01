package study.CET;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 1/18/14
 * Time: 2:27 PM
 */

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tool.HibernateUtil;
import tool.R;
import tool.studentInfo.StudentInfoEntity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 华中师范大学全国英语等级考试(CET)查询
 */
public class ManageCET {

	/**
	 * 查询成绩
	 */
	public static final String URL_Query = "http://cet.tinyin.net/scshow.asp";

	/**
	 * 要查询的CET考试的时间
	 */
	public static final String DATE[] = {"201312", "201306", "201212", "201206", "201112", "201106"};

	/**
	 * 查询成绩
	 *
	 * @param grade  等级
	 * @param IDcard 身份证
	 * @param date   考试时间
	 * @return 如果查询成功正常返回, 否则返回null
	 */
	public static Cet46Entity get(String grade, String IDcard, String date) {
		try {
			//http获得数据
			URL url = new URL(URL_Query);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);

			connection.setRequestProperty("Referer", "http://cet.tinyin.net/scqry.asp");

			//构造参数
			StringBuilder pa = new StringBuilder();
			pa.append("dt=").append(date);//只查询这次的46级结果
			pa.append("&lang=").append(URLEncoder.encode("英语", "GB2312"));//只查询英语
			pa.append("&gd=").append(grade);//等级
			pa.append("&idn=").append(IDcard);//身份证

			connection.getOutputStream().write(pa.toString().getBytes());
			String html = null;
			try {
				html = IOUtils.toString(connection.getInputStream(), "GB2312");
			} catch (IOException e) {
				html = IOUtils.toString(connection.getErrorStream(), "GB2312");
			}

			//解析数据
			Document document = Jsoup.parse(html);
			Elements all = document.getElementsByTag("p").first().getElementsByTag("font");
			float sum = Float.parseFloat(all.get(0).text());
			String listen = all.get(1).text();
			String read = all.get(2).text();
			String com = all.get(3).text();
			String essay = all.get(4).text();
			return new Cet46Entity(sum, listen, read, com, essay, grade);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询最近一次考试的成绩
	 *
	 * @param grade  等级
	 * @param IDcard 身份证
	 * @return 如果查询成功正常返回, 否则返回null
	 */
	public static Cet46Entity get(String grade, String IDcard) {
		return get(grade, IDcard, DATE[0]);
	}

	/**
	 * 用学号去数据库里获得身份证密码,根据年级去获得成绩
	 *
	 * @param XH
	 * @return 如果查询失败或没有成绩就返回null
	 */
	public static Cet46Entity get(String XH) {
		StudentInfoEntity one;
		try {
			Session session = HibernateUtil.getSession();
			Object o = session.createQuery("from StudentInfoEntity as main where main.xh=?").setString(0, XH).uniqueResult();
			HibernateUtil.closeSession(session);
			if (o == null) {
				return null;
			} else {
				one = (StudentInfoEntity) o;
			}
		} catch (Exception e) {
			return null;
		}
		String name = one.getName();
		String IdCard = one.getIdNumber();
		String grade = XH.substring(0, 4);
		Cet46Entity re = null;
		if (grade.compareTo("2012") == 0) {//2012级,查4级
			for (String date : DATE) {
				re = get("4", IdCard, date);
				if (re != null) {
					re.setDate(date);
					break;
				}
			}
		} else if (grade.compareTo("2012") < 0) {//2011,2010级;先查6级再四级
			for (String date : DATE) {
				re = get("6", IdCard, date);
				if (re != null) {
					re.setDate(date);
					break;
				}
			}
			if (re == null) {
				for (String date : DATE) {
					re = get("4", IdCard, date);
					if (re != null) {
						re.setDate(date);
						break;
					}
				}
			}
		} else {//2013级,没有参加考试
			return null;
		}
		if (re != null) {
			re.setXh(XH);
			re.setName(name);
//			HibernateUtil.addEntity(re);
		}
		return re;
	}

	/**
	 * 从全国官方获得
	 *
	 * @param KH
	 * @param name
	 * @return
	 */
	public static Cet46Entity get_KH(String KH, String name) {
		System.out.println(KH + name);
		try {
			Connection connection = Jsoup.connect("http://www.chsi.com.cn/cet/query");
			connection.userAgent(R.USER_AGENT);
			connection.data("zkzh", KH);
			connection.data("xm", name);
			connection.header("Referer", "http://www.chsi.com.cn/cet/");

			Document document = connection.get();

			Element the = document.getElementsByClass("cetTable").first().getElementsByTag("tr").get(5);
			String all[] = the.text().split(" ");
			float sum = Float.parseFloat(all[1]);
			String listen = all[3];
			String read = all[5];
			String essay = all[7];
			return new Cet46Entity(sum, listen, read, " ", essay, " ");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
