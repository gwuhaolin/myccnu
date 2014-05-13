package tool.studentInfo;

import tool.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/26/14
 * Time: 12:30 PM
 */
public class ManageStudentInfo {

	private static final String ManagePassword="wonder";

	/**
	 * 获得学号为XH的同学
	 * @param XH
	 * @return
	 */
	public static MainEntity get_XH(String XH){
		Session session= HibernateUtil.getSession();
		Query query= session.createQuery("from MainEntity where studentId=?");
		query.setString(0,XH);
		MainEntity re=(MainEntity)query.uniqueResult();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得姓名包含keyword的同学
	 * @param keyWord
	 * @return
	 */
	public static List<MainEntity> get_Name(String keyWord){
		Session session= HibernateUtil.getSession();
		Query query= session.createQuery("from MainEntity where name like '%"+keyWord+"%'");
		List<MainEntity> re=query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得生日包含keyword的同学
	 * @param keyWord
	 * @return
	 */
	public static List<MainEntity> get_Birthday(String keyWord){
		Session session= HibernateUtil.getSession();
		Query query= session.createQuery("from MainEntity where birthday like '%"+keyWord+"%'");
		List<MainEntity> re=query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得高中包含keyword的同学
	 * @param keyWord
	 * @return
	 */
	public static List<MainEntity> get_HighSchool(String keyWord){
		Session session= HibernateUtil.getSession();
		Query query= session.createQuery("from MainEntity where highSchool like '%"+keyWord+"%'");
		List<MainEntity> re=query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得家庭住址中包含keyword的同学
	 * @param keyWord
	 * @return
	 */
	public static List<MainEntity> get_Address(String keyWord){
		Session session= HibernateUtil.getSession();
		Query query= session.createQuery("from MainEntity where familyAddress like '%"+keyWord+"%'");
		List<MainEntity> re=query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得学习经历中包含keyword的同学
	 * @param keyWord
	 * @return
	 */
	public static List<MainEntity> get_Education(String keyWord){
		Session session= HibernateUtil.getSession();
		Query query= session.createQuery("from MainEntity where educationInfo like '%"+keyWord+"%'");
		List<MainEntity> re=query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 检索信息中包含keyword
	 * @param keyWord
	 * @return
	 */
	public static Set<MainEntity> get(String keyWord){
		Set<MainEntity> re=new HashSet<MainEntity>();
		re.addAll(get_Name(keyWord));
		re.addAll(get_Birthday(keyWord));
		re.addAll(get_HighSchool(keyWord));
		re.addAll(get_Address(keyWord));
		re.addAll(get_Education(keyWord));
		return re;
	}

	/**
	 * 管理员获得同学信息
	 * @param request
	 * @return
	 */
//	public static MainEntity get(HttpServletRequest request){
//		try {
//			String MM=request.getParameter("MM");
//			if (MM!=null && MM.equals(ManagePassword)){
//				String XH=request.getParameter("XH");
//				if (XH!=null && XH.length()==10){
//					return get_XH(XH);
//				}
//			}
//		}catch (Exception e){
//		}
//		return null;
//	}

	public static void main(String[] args) {
//		System.out.println(get_XH("2012210817"));
		System.out.println(get_Name("周桢津"));
//		System.out.println(get_Birthday("____1005"));
//		System.out.println(get_HighSchool("攀枝花"));
//		System.out.println(get_Address("攀枝花"));
//		System.out.println(get_Education("攀枝花"));
//		System.out.println(get_XH("2012210008"));
//		delete_XH("2012210817");
//		Tool.setXHMMtoSQL("2012210817", "930820");

	}
}
