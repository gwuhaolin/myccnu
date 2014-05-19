package tool.studentInfo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tool.CCNUPortal;
import tool.HibernateUtil;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/26/14
 * Time: 12:30 PM
 */
public class ManageStudentInfo {
	public static final HashMap<String, String> AllKey = new HashMap<String, String>();
	static {
		AllKey.put("学号", "StudentId");
		AllKey.put("密码", "Password");
		AllKey.put("姓名", "Name");
		AllKey.put("曾用名", "FormerName");
		AllKey.put("身份证号", "IdNumber");
		AllKey.put("学院", "Academy");
		AllKey.put("专业", "Major");
		AllKey.put("民族", "Nation");
		AllKey.put("籍贯", "NativePlace");
		AllKey.put("毕业学校", "HighSchool");
		AllKey.put("现家庭住址", "FamilyAddress");
		AllKey.put("家庭电话", "FamilyPhoneNumber");
		AllKey.put("宿舍地址", "DormAddress");
		AllKey.put("身高", "Height");
		AllKey.put("体重", "Weight");
		AllKey.put("手机号", "PhoneNumber");
	}

	private static final String ManagePassword = "wonder";

	/**
	 * 获得学号为XH的同学
	 *
	 * @param XH
	 * @return
	 */
	public static StudentInfoEntity get_XH(String XH) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentInfoEntity where xh=?");
		query.setString(0, XH);
		StudentInfoEntity re = (StudentInfoEntity) query.uniqueResult();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得姓名包含keyword的同学
	 *
	 * @param keyWord
	 * @return
	 */
	public static List<StudentInfoEntity> get_Name(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentInfoEntity where name like '%" + keyWord + "%'");
		List<StudentInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得生日包含keyword的同学
	 *
	 * @param keyWord
	 * @return
	 */
	public static List<StudentInfoEntity> get_Birthday(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentInfoEntity where birthday like '%" + keyWord + "%'");
		List<StudentInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得高中包含keyword的同学
	 *
	 * @param keyWord
	 * @return
	 */
	public static List<StudentInfoEntity> get_HighSchool(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentInfoEntity where highSchool like '%" + keyWord + "%'");
		List<StudentInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得家庭住址中包含keyword的同学
	 *
	 * @param keyWord
	 * @return
	 */
	public static List<StudentInfoEntity> get_Address(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentInfoEntity where familyAddress like '%" + keyWord + "%'");
		List<StudentInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得学习经历中包含keyword的同学
	 *
	 * @param keyWord
	 * @return
	 */
	public static List<StudentInfoEntity> get_Education(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentInfoEntity where educationInfo like '%" + keyWord + "%'");
		List<StudentInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 检索信息中包含keyword
	 * 检索项包括:Name,Birthday,HighSchool,Address,Education
	 *
	 * @param keyWord
	 * @return
	 */
	public static List<StudentInfoEntity> get(String keyWord) {
		List<StudentInfoEntity> re = new LinkedList<StudentInfoEntity>();
		re.addAll(get_Name(keyWord));
		re.addAll(get_Birthday(keyWord));
		re.addAll(get_HighSchool(keyWord));
		re.addAll(get_Address(keyWord));
		re.addAll(get_Education(keyWord));
		return re;
	}

	/**
	 * 用学号密码去教务处抓取信息,然后保存到数据库
	 * @param XH 学号
	 * @param MM 密码
	 * @return 如果成功获取且保存到数据库就返回获得的对象,否则返回null
	 */
	public static StudentInfoEntity DownloadFromJWC(String XH,String MM){
		Document document;
		try {
			document = CCNUPortal.getStudentInfo(XH, MM);
			StudentInfoEntity re=parse(document);
			if (re!=null){
				HibernateUtil.addOrUpdateEntity(re);
				return re;
			}else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 从教务处获得的HTML网页里解析出学生信息
	 * @param document
	 * @return
	 */
	private static StudentInfoEntity parse(Document document){
		StudentInfoEntity reOne = new StudentInfoEntity();
		Class clazz = StudentInfoEntity.class;
		try {
			//基本信息
			Elements tds = document.getElementById("tdId_baseinfo").select("table").first().select("td");
			for (int i = 0; i < tds.size(); i++) {
				Element td = tds.get(i);
				String name = td.text();
				if (name.contains("：")) {
					String mName;
					if (!name.contains("(")) {
						mName = "set" + AllKey.get(name.substring(0, name.length() - 1));
					} else {
						mName = "set" + AllKey.get(name.substring(0, name.indexOf('(')));
					}
					try {
						Method method = clazz.getDeclaredMethod(mName, String.class);
						i++;
						String value =trim(tds.get(i).text());
						method.invoke(reOne, value);
					}catch (Exception e){
					}
				}
			}

			StringBuilder stringBuilder = new StringBuilder();
			//高中表现
			stringBuilder.delete(0, stringBuilder.length());
			Elements ps= document.getElementById("tdId_baseinfo").getElementsByTag("p");
			for (Element p:ps){
				stringBuilder.append(p.text());
			}
			reOne.setHighSchoolPerformance(stringBuilder.toString());

			//家庭信息
			stringBuilder.delete(0, stringBuilder.length());
			tds = document.getElementById("tdId_family").select("table").first().select("td");
			for (Element td : tds) {
				stringBuilder.append(td.text());
				stringBuilder.append(" ");
			}
			reOne.setFamilyInfo(stringBuilder.toString());

			//学习经历
			stringBuilder.delete(0, stringBuilder.length());
			tds = document.getElementById("tdId_study").select("table").first().select("td");
			for (Element td : tds) {
				stringBuilder.append(td.text());
				stringBuilder.append(" ");
			}
			reOne.setEducationInfo(stringBuilder.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
		return reOne;
	}

	/**
	 * 清除字符串的头尾的中文空格和英文空格
	 * @param string
	 * @return
	 */
	private static String trim(String string){
		char[] value=string.toCharArray();
		int len = value.length;
		int st = 0;

		while ((st < len) && ((value[st] <= ' ') || value[st]==160 )) {
			st++;
		}
		while ((st < len) && ((value[len - 1] <= ' ') || value[len-1]==160 )) {
			len--;
		}
		return ((st > 0) || (len < value.length)) ? string.substring(st, len) : string;
	}

}
