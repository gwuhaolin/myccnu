package tool.ccnu.student;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;

import java.util.List;

/**
 * Created by wuhaolin on 7/6/14.
 * :管理学生的信息
 * TODO 其实所有功能都应该围绕学生信息来展开,这才是核心
 */
public class ManageStudents {

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
	 * 不会重新去数据库抓取信息只更新密码
	 * TODO 需要异步执行去信息门户抓取信息然后解析然后保存都数据库
	 *
	 * @param xh       学号
	 * @param password 新的密码
	 * @return 是否更新成功
	 */
	public static boolean update_PasswordToSQL(String xh, String password) {
		StudentsEntity studentsEntity = get(xh);
		studentsEntity.setPassword(password);
		return HibernateUtil.updateEntity(studentsEntity);
	}


}
