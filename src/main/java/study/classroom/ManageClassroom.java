package study.classroom;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/3/29
 * Time: 19:06
 */
public class ManageClassroom {
	public static final String[] JIHAOLOU = {"9", "8", "7"};

	/**
	 * 按照给的条件查询出空余教室结果
	 *
	 */
	public static List<OneQueryResult> query(int XinQiJi, String JiHaoLou) {
		//当他们的值为空时设置为默认值
		if (XinQiJi<1){
			XinQiJi=1;
		}
		if (JiHaoLou == null) {
			JiHaoLou = JIHAOLOU[0];
		}
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyClassroomEntity where xinQiJi=? and jiHaoLou=? order by diJiJieKe");
		query.setInteger(0, XinQiJi);
		query.setString(1, JiHaoLou);
		List<MyClassroomEntity> all = query.list();
		HibernateUtil.closeSession(session);

		OneQueryResult re[] = new OneQueryResult[7];
		for (int i = 0; i < re.length; i++) {
			re[i] = new OneQueryResult(i + 1);
		}

		for (MyClassroomEntity myclassroomEntity : all) {
			re[myclassroomEntity.getDiJiJieKe() - 1].addOneClassroom(myclassroomEntity);
		}

		List<OneQueryResult> resultList = new ArrayList<OneQueryResult>();
		for (OneQueryResult oneQueryResult : re) {
			if (oneQueryResult.getAllClassRoom().size() > 0) {
				resultList.add(oneQueryResult);
			}
		}
		return resultList;
	}

	public static boolean add(String jiHaoLou, String XinQiJi, String DiJiJie, String classroom) {
		try {
			MyClassroomEntity newone = new MyClassroomEntity(jiHaoLou, Integer.parseInt(XinQiJi), Integer.parseInt(DiJiJie), classroom);
			HibernateUtil.addEntity(newone);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delete(String jiHaoLou, String XinQiJi, String DiJiJie, String classroom) {
		try {
			MyClassroomEntity newone = new MyClassroomEntity(jiHaoLou, Integer.parseInt(XinQiJi), Integer.parseInt(DiJiJie), classroom);
			HibernateUtil.removeEntity(newone);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<MyClassroomEntity> all(){
		Session session=HibernateUtil.getSession();
		List<MyClassroomEntity> re= session.createQuery("from MyClassroomEntity ").list();
		HibernateUtil.closeSession(session);
		return re;
	}

}
