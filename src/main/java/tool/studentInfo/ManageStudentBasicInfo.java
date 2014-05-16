/**
 * Author: WuHaoLin
 * Date: 2014/5/15
 * Time: 15:21
 */

package tool.studentInfo;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/15
 * Time: 15:21
 */
public class ManageStudentBasicInfo {

	/**
	 * 获得学号为XH的同学
	 *
	 * @param XH
	 * @return
	 */
	public static StudentBasicInfoEntity get_XH(String XH) {
		if (XH==null){
			return null;
		}
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentBasicInfoEntity where xh=?");
		query.setString(0, XH);
		StudentBasicInfoEntity re = (StudentBasicInfoEntity) query.uniqueResult();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得QQ为QQ的同学
	 *
	 * @param QQ
	 * @return
	 */
	public static StudentBasicInfoEntity get_QQ(String QQ) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentBasicInfoEntity where qq=?");
		query.setString(0, QQ);
		StudentBasicInfoEntity re = (StudentBasicInfoEntity) query.uniqueResult();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得姓名包含keyword的同学
	 *
	 * @param keyWord
	 * @return
	 */
	public static List<StudentBasicInfoEntity> get_Name(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentBasicInfoEntity where name like '%" + keyWord + "%'");
		List<StudentBasicInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得姓名包含keyword的同学
	 *
	 * @param keyWord
	 * @return
	 */
	public static List<StudentBasicInfoEntity> get_Academy(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentBasicInfoEntity where academy  like '%" + keyWord + "%'");
		List<StudentBasicInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}


	public static List<StudentBasicInfoEntity> get_Major(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentBasicInfoEntity where major like '%" + keyWord + "%'");
		List<StudentBasicInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	public static List<StudentBasicInfoEntity> get_DormAddress(String keyWord) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentBasicInfoEntity where dormAddress like '%" + keyWord + "%'");
		List<StudentBasicInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 通过sql条件语句查询
	 * @param sqlCondition sql条件语句
	 * @return
	 */
	public static List<StudentBasicInfoEntity> query(String sqlCondition) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from StudentBasicInfoEntity where " + sqlCondition);
		List<StudentBasicInfoEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

}
