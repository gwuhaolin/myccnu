package study.CET;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 1/18/14
 * Time: 2:27 PM
 */

import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.HibernateUtil;
import tool.R;
import tool.ccnu.student.StudentsEntity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 华中师范大学全国英语等级考试(CET)查询
 */
public class ManageCET {

	private static final Logger log = LoggerFactory.getLogger(ManageCET.class);

	/**
	 * 华师的查询成绩网址
	 */
	private static final String URL_Query = "http://cet.tinyin.net/scshow.asp";

	/**
	 * 要查询的CET考试的时间,用于从学校抓取数据时提交的数据
	 * 会按照顺序依次抓取,把优先的写在前面
	 */
	private static final String[] DATE = {"201406", "201312"};

	/**
	 * 查询时的所有的等级
	 * 会按照顺序依次抓取,把优先的写在前面
	 */
	private static final String[] GRADE = {"6", "4"};

	/**
	 * 从学校的网站抓取成绩
	 *
	 * @param grade    46等级
	 * @param IdNumber 身份证
	 * @param date     考试时间,在DATE里的一个
	 * @return 如果查询成功正常返回, 否则返回null
	 */
	public static Cet46Entity spider(String grade, String IdNumber, String date) {
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
			pa.append("&idn=").append(IdNumber);//身份证

			connection.getOutputStream().write(pa.toString().getBytes());
			String html;
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
			String essay = all.get(4).text();
			log.info("成功抓取{}的CET成绩={}", IdNumber, sum);
			return new Cet46Entity(sum, listen, read, essay, grade);
		} catch (Exception e) {
			log.error("抓取CET失败" + IdNumber);
//			log.error(Arrays.toString(e.getStackTrace()));
			return null;
		}
	}

	/**
	 * 去学校抓取
	 * 调用这个函数的原因是网页数据库中没有这个同学的信息,所以直接去用身份证号抓取
	 * 去学校网站按照DATE[]里的开始时间依次去直接抓取数据,知道成功抓到一个为止,并且抓到的会保存到数据库
	 *
	 * @param grade    4还是6级
	 * @param idNumber 身份证号码
	 * @return 如果查询成功就返回一个成绩,
	 */
	public static Cet46Entity scan(String grade, String idNumber) {
		for (String date : DATE) {
			Cet46Entity one = spider(grade, idNumber, date);
			if (one != null) {
				HibernateUtil.addEntity(one);
				return one;
			}
		}
		return null;
	}

	/**
	 * 根据学号去数据库里获得身份证密码,根据年级自动判断应该查询哪次的成绩
	 * 如果数据库中没有该学号的信息,就直接返回null
	 * 根据该同学的年级和今年是哪年自动判断查询那次的成绩
	 */
	public static Cet46Entity get(String XH) {
		//用学号去学生信息数据库中获取数据用于查询
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentsEntity as main where main.xh=?");
		query.setString(0, XH);
		Object o = query.uniqueResult();
		HibernateUtil.closeSession(session);
		StudentsEntity studentsEntity;
		if (o != null) {
			studentsEntity = (StudentsEntity) o;
		} else {//数据库中没有该同学的信息
			return null;
		}
		String name = studentsEntity.getName();
		String IdCard = studentsEntity.getIdNumber();//身份证号
		List<Cet46Entity> re = new LinkedList<>();
		for (String date : DATE) {
			for (String grade : GRADE) {
				Cet46Entity cet46Entity = spider(grade, IdCard, date);
				if (cet46Entity != null) {
					cet46Entity.setXh(XH);
					cet46Entity.setName(name);
					cet46Entity.setDate(date);
					re.add(cet46Entity);
				}
			}
		}
		if (re.size() > 0) {//如果查询成功
			Collections.sort(re);
			HibernateUtil.addOrUpdateEntitys(re);//把所有抓取到的都保存到数据库
		} else {//没有抓取到任何一次成绩
			re = get_XH_fromSQL(XH);
		}
		if (re.size() > 0) {
			return re.get(0);//返回第一个,即他最关系的那个
		} else {
			return null;
		}
	}

	/**
	 * 从全国官网抓取
	 * 从全国官方抓取成绩
	 *
	 * @param KH   考号
	 * @param name 姓名
	 * @return 成绩, 如果失败返回null
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
			Cet46Entity re = new Cet46Entity(sum, listen, read, essay, " ");
			HibernateUtil.addEntity(re);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 去数据库里获取
	 * 从数据库中去获取学号为XH同学的成绩
	 * 按照最优程序来排序
	 *
	 * @param xh 学号
	 * @return 成绩, 如果没有记录就返回0个
	 */
	private static List<Cet46Entity> get_XH_fromSQL(String xh) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from Cet46Entity where xh=?");
		query.setString(0, xh);
		List<Cet46Entity> re = query.list();
		Collections.sort(re);
		return re;
	}

}
