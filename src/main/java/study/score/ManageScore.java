package study.score;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 1/26/14
 * Time: 10:26 PM
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
import tool.studentInfo.StudentInfoEntity;

import java.io.IOException;
import java.util.*;

/**
 * 从教务处网站获得成绩
 */
public class ManageScore {

	private static final Logger log = LoggerFactory.getLogger(ManageScore.class);

	/**
	 * 查成绩
	 */
	public static final String URL_CJ = "http://jwc.ccnu.edu.cn/stuScore.aspx";

	/**
	 * 查询成绩,获得这学期的最新的成绩结果
	 * 查询结果按照总分大小排序
	 * 结果会更新的数据库
	 *
	 * @param XH 学号
	 * @param MM 密码
	 * @return 如果网络错误或学校服务器错误就从本机的数据库里获得
	 */
	public static List<MyScoreEntity> get(String XH, String MM) {
		Map<String, String> cookies;
		try {
			cookies = CCNUJWC.getCookie(XH, MM);
		} catch (NetworkException | ValidateException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			return get_XH(XH);
		}
		Connection connection = Jsoup.connect(URL_CJ);
		connection.userAgent(R.USER_AGENT);
		connection.timeout(R.ConnectTimeout);
		connection.cookies(cookies);
		Document document;
		try {
			document = connection.post();
		} catch (IOException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			return get_XH(XH);
		}
		try {
			String __VIEWSTATE = document.getElementById("__VIEWSTATE").attr("value");//奇怪的验证数据
			String __EVENTVALIDATION = document.getElementById("__EVENTVALIDATION").attr("value");//奇怪的验证数据
			String time = document.getElementById("DropDownList1").child(0).text();//获得最新的成绩所代表的时间
			//设置数据
			connection.data("DropDownList1", time);
			connection.data("DropDownList2", time);
			connection.data("__VIEWSTATE", __VIEWSTATE);
			connection.data("__EVENTVALIDATION", __EVENTVALIDATION);
			connection.data("Button1", "查询");
		} catch (IndexOutOfBoundsException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			log.error(document.toString());
			return get_XH(XH);
		}

		//获得数据
		try {
			document = connection.post();
		} catch (IOException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			log.error(document.toString());
			return get_XH(XH);
		}
		//解析数据
		try {
			Elements tr = document.getElementById("GridView1").getElementsByTag("tr");
			List<MyScoreEntity> re = new LinkedList<>();
			for (int i = 1; i < tr.size(); i++) {
				MyScoreEntity myScoreEntity = new MyScoreEntity(tr.get(i));
				myScoreEntity.setXh(XH);
				re.add(myScoreEntity);
			}
			saveOrUpdateScores(re);//保存到数据库
			Collections.sort(re);
			log.info("学号为{}的查询成绩成功", XH);
			return re;
		} catch (IndexOutOfBoundsException e) {
			log.error(Arrays.toString(e.getStackTrace()));
			log.error(document.toString());
			return get_XH(XH);
		}
	}

	/**
	 * 把获得的成绩保存到数据库,如果数据库中已经有了旧的会被覆盖
	 *
	 * @param scoreEntities 所有获得的成绩
	 */
	public static void saveOrUpdateScores(List<MyScoreEntity> scoreEntities) {
		for (MyScoreEntity myScoreEntity : scoreEntities) {
			HibernateUtil.addOrUpdateEntity(myScoreEntity);
		}
	}

	/**
	 * 获得所有学号等于xh的课程
	 *
	 * @param xh 学生的学号
	 * @return 课程
	 */
	public static List<MyScoreEntity> get_XH(String xh) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyScoreEntity where xh=?");
		query.setString(0, xh);
		List<MyScoreEntity> re = query.list();
		HibernateUtil.closeSession(session);
		Collections.sort(re);
		return re;
	}

	/**
	 * 获得所有的classID为classID的课程
	 *
	 * @param classNo 课程的id
	 * @return 所有的课程
	 */
	public static List<MyScoreEntity> get_ClassNo(String classNo) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyScoreEntity where classNo=?");
		query.setString(0, classNo);
		List<MyScoreEntity> re = query.list();
		HibernateUtil.closeSession(session);
		Collections.sort(re);
		return re;
	}

	/**
	 * 对于数据库中所有已经知道了帐号密码的同学,主动去教务处抓取数据
	 *
	 * @return 更新了的人数
	 */
	public static int updateAllStudentsScore() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentInfoEntity where password!=null and xh!=null");
		List<StudentInfoEntity> studentInfoEntities = query.list();
		HibernateUtil.closeSession(session);
		for (StudentInfoEntity studentInfoEntity : studentInfoEntities) {
			get(studentInfoEntity.getXh(), studentInfoEntity.getPassword());
		}
		return studentInfoEntities.size();
	}

}
