package tool.ccnu.student.detailInfo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.HibernateUtil;
import tool.NetworkException;
import tool.Tool;
import tool.ValidateException;
import tool.ccnu.CCNUInfo;
import tool.ccnu.academy.ManageAcademy;
import tool.ccnu.student.StudentsEntity;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/26/14
 * Time: 12:30 PM
 * 用于从信息门户抓取分析信息,和应用程序里使用的信息不一样
 */
public class ManageStudentAllInfo {

	private static final Logger log = LoggerFactory.getLogger(CCNUInfo.class);

	public static final HashMap<String, String> AllKey = new HashMap<>();

	//html文档中属性的名称和StudentAllInfoEntity中的set的名称方法对应
	static {
		AllKey.put("学号", "Xh");
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

	/**
	 * 获得学号为XH的同学
	 */
	public static StudentAllInfoEntity get_XH(String XH) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentAllInfoEntity where xh=?");
		query.setString(0, XH);
		StudentAllInfoEntity re = (StudentAllInfoEntity) query.uniqueResult();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得姓名包含keyword的同学
	 */
	public static List<StudentAllInfoEntity> get_Name(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentAllInfoEntity where name like '%" + keyWord + "%'");
		List<StudentAllInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得生日包含keyword的同学
	 */
	public static List<StudentAllInfoEntity> get_Birthday(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentAllInfoEntity where birthday like '%" + keyWord + "%'");
		List<StudentAllInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得高中包含keyword的同学
	 */
	public static List<StudentAllInfoEntity> get_HighSchool(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentAllInfoEntity where highSchool like '%" + keyWord + "%'");
		List<StudentAllInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得家庭住址中包含keyword的同学
	 */
	public static List<StudentAllInfoEntity> get_Address(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentAllInfoEntity where familyAddress like '%" + keyWord + "%'");
		List<StudentAllInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得学习经历中包含keyword的同学
	 */
	public static List<StudentAllInfoEntity> get_Education(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentAllInfoEntity where educationInfo like '%" + keyWord + "%'");
		List<StudentAllInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 检索信息中包含keyword
	 * 检索项包括:Name,Birthday,HighSchool,Address,Education
	 */
	public static List<StudentAllInfoEntity> get(String keyWord) {
		List<StudentAllInfoEntity> re = new LinkedList<>();
		re.addAll(get_Name(keyWord));
		re.addAll(get_Birthday(keyWord));
		re.addAll(get_HighSchool(keyWord));
		re.addAll(get_Address(keyWord));
		re.addAll(get_Education(keyWord));
		return re;
	}

	/**
	 * 用学号密码去教务处抓取信息,然后保存到数据库
	 * 可能会抓取失败就会直接返回null不会保存到数据库
	 *
	 * @param XH 学号
	 * @param MM 密码
	 * @return 如果成功获取且保存到数据库就返回获得的对象, 否则返回null
	 */
	public static StudentAllInfoEntity DownloadAndStoreToSQLFromJWC(String XH, String MM) {
		Document document = null;
		try {
			document = CCNUInfo.spiderStudentInfo(XH, MM);
		} catch (NetworkException | ValidateException e) {
			log.error(Arrays.toString(e.getStackTrace()));
		}
		StudentAllInfoEntity re = parse(document);
		if (re != null) {
			StudentsEntity studentsEntity = studentsAllInfoEntityToStudentsEntity(re);//转换为简易版用于应用程序
			Session session = HibernateUtil.getSession();
			session.saveOrUpdate(re);
			session.saveOrUpdate(studentsEntity);
			HibernateUtil.closeSession(session);
			return re;
		} else {
			return null;
		}
	}

	/**
	 * 把抓取到的详细的信息转换为应用程序使用的信息,但是不会保存到数据库库,只是完成转换
	 *
	 * @param studentAllInfoEntity 抓取到的详细的信息
	 * @return 简化后的信息
	 */
	public static StudentsEntity studentsAllInfoEntityToStudentsEntity(StudentAllInfoEntity studentAllInfoEntity) {
		StudentsEntity one = new StudentsEntity();
		one.setPassword(studentAllInfoEntity.getPassword());
		one.setXh(studentAllInfoEntity.getXh());
		one.setQq(studentAllInfoEntity.getQq());
		one.setAcademyByAcademy(ManageAcademy.get(studentAllInfoEntity.getAcademy()));
		one.setDormitory(studentAllInfoEntity.getDormAddress());
		one.setIdNumber(studentAllInfoEntity.getIdNumber());
		one.setPhoneNumber(studentAllInfoEntity.getPhoneNumber());
		if (studentAllInfoEntity.getSex() != null) {
			one.setSex((int) studentAllInfoEntity.getSex());
		}
		one.setName(studentAllInfoEntity.getName());
		return one;
	}

	/**
	 * 从教务处获得的HTML网页里解析出学生信息
	 * TODO 需要做到把抓取到的老师的html文档分析为一个StudentAllInfoEntity,以方便格式统一
	 */
	public static StudentAllInfoEntity parse(Document document) {
		StudentAllInfoEntity reOne = new StudentAllInfoEntity();
		Class clazz = StudentAllInfoEntity.class;
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
						String value = Tool.trim(tds.get(i).text());
						method.invoke(reOne, value);
					} catch (Exception e) {
					}
				}
			}

			StringBuilder stringBuilder = new StringBuilder();
			//高中表现
			stringBuilder.delete(0, stringBuilder.length());
			Elements ps = document.getElementById("tdId_baseinfo").getElementsByTag("p");
			for (Element p : ps) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reOne;
	}


}
