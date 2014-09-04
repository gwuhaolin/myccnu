package tool.ccnu.student;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.HibernateUtil;
import tool.ccnu.student.detailInfo.ManageStudentAllInfo;

import java.util.List;

/**
 * Created by wuhaolin on 7/6/14.
 * :管理学生的信息
 * TODO 其实所有功能都应该围绕学生信息来展开,这才是核心
 */
public class ManageStudents {

	private static final Logger log = LoggerFactory.getLogger(ManageStudents.class);

	/**
	 * 获得学号为xh的同学
	 *
	 * @param xh 学号
	 * @return 学生的信息, 如果数据库中不存在就返回null
	 */
	public static StudentsEntity get(String xh) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentsEntity where xh=?");
		query.setString(0, xh);
		Object re = query.uniqueResult();
		HibernateUtil.closeSession(session);
		if (re != null) {
			return (StudentsEntity) re;
		} else {
			return null;
		}
	}

	/**
	 * 用身份证号码去获得一个同学的信息
	 *
	 * @param idNumber 身份证号码
	 * @return 信息, 如果数据库中不存在就返回null
	 */
	public static StudentsEntity get_IdNumber(String idNumber) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentsEntity where idNumber=?");
		query.setString(0, idNumber);
		Object re = query.uniqueResult();
		HibernateUtil.closeSession(session);
		if (re != null) {
			return (StudentsEntity) re;
		} else {
			return null;
		}
	}

	/**
	 * 用身份证号码去获得一个同学的信息
	 *
	 * @param phoneNumber 身份证号码
	 * @return 信息, 如果数据库中不存在就返回null
	 */
	public static StudentsEntity get_phoneNumber(String phoneNumber) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentsEntity where phoneNumber=?");
		query.setString(0, phoneNumber);
		Object re = query.uniqueResult();
		HibernateUtil.closeSession(session);
		if (re != null) {
			return (StudentsEntity) re;
		} else {
			return null;
		}
	}

	/**
	 * 用身份证号码去获得一个同学的信息
	 *
	 * @param QQ QQ号
	 * @return 信息, 如果数据库中不存在就返回null
	 */
	public static StudentsEntity get_QQ(String QQ) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentsEntity where qq=?");
		query.setString(0, QQ);
		Object re = query.uniqueResult();
		HibernateUtil.closeSession(session);
		if (re != null) {
			return (StudentsEntity) re;
		} else {
			return null;
		}
	}


	/**
	 * 获得所有姓名为name的同学
	 *
	 * @param name 姓名
	 * @return 信息
	 */
	public static List<StudentsEntity> get_Name(String name) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentsEntity where name=?");
		query.setString(0, name);
		List re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得一个院的所有的学生
	 *
	 * @param academyId 院的id
	 * @return 学生
	 */
	public static List<StudentsEntity> get_Academy(int academyId) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentsEntity where academyByAcademy=?");
		query.setInteger(0, academyId);
		List re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 更新学号为xh的同学的密码到数据库
	 * 还会去教务处抓取该同学的信息保存的数据库
	 * 如果数据库中还不存在这个同学的信息就先把账号密码保存到数据库
	 *
	 * @param xh       学号
	 * @param password 新的密码
	 */
	public static void update_PasswordToSQL(String xh, String password) {
		StudentsEntity studentsEntity = ManageStudentAllInfo.downloadAndStoreToSQLFromJWC(xh, password);
		if (studentsEntity == null) {
			studentsEntity = new StudentsEntity();
		}
		studentsEntity.setXh(xh);
		studentsEntity.setPassword(password);
		HibernateUtil.addOrUpdateEntity(studentsEntity);
	}

}
