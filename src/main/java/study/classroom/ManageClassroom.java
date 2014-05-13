package study.classroom;

import tool.HibernateUtil;
import tool.Tool;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/3/29
 * Time: 19:06
 */
public class ManageClassroom {
	public static String JIHAOLOU[] = {"9", "8", "7"};


	/**
	 * 按照给的条件查询出空余教室结果
	 *
	 * @param location
	 * @param time
	 * @return
	 */
	public static List<OneQueryResult> query(String XinQiJi, String JiHaoLou) {
		int XinQiJi_int;
		//当他们的值为空时设置为默认值
		try {
			XinQiJi_int = Integer.parseInt(XinQiJi);
		} catch (Exception e) {
			XinQiJi_int = Tool.week_NOW();
		}
		if (JiHaoLou == null) {
			JiHaoLou = JIHAOLOU[0];
		}
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyclassroomEntity where xinQiJi=? and jiHaoLou=? order by diJiJieKe");
		query.setInteger(0, XinQiJi_int);
		query.setString(1, JiHaoLou);
		List<MyclassroomEntity> all = query.list();
		HibernateUtil.closeSession(session);

		OneQueryResult re[] = new OneQueryResult[7];
		for (int i = 0; i < re.length; i++) {
			re[i] = new OneQueryResult(i + 1);
		}

		for (MyclassroomEntity myclassroomEntity : all) {
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
			MyclassroomEntity newone = new MyclassroomEntity(jiHaoLou, Integer.parseInt(XinQiJi), Integer.parseInt(DiJiJie), classroom);
			HibernateUtil.addEntity(newone);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delete(String jiHaoLou, String XinQiJi, String DiJiJie, String classroom) {
		try {
			MyclassroomEntity newone = new MyclassroomEntity(jiHaoLou, Integer.parseInt(XinQiJi), Integer.parseInt(DiJiJie), classroom);
			HibernateUtil.removeEntity(newone);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static List<MyclassroomEntity> all(){
		Session session=HibernateUtil.getSession();
		List<MyclassroomEntity> re= session.createQuery("from MyclassroomEntity ").list();
		HibernateUtil.closeSession(session);
		return re;
	}


	public static void main(String[] args) {
		System.out.println(query(null, null));
	}

}
