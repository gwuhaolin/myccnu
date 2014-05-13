package play.joinUs;

import tool.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 3/2/14
 * Time: 8:15 PM
 */
public class ManageJoinUs {

	/**
	 * 学号为XH的同学要加入我们
	 * @param XH
	 * @return
	 */
	public static void join(String XH){
		JoinusEntity joinusEntity=new JoinusEntity();
		joinusEntity.setXh(XH);
		HibernateUtil.addEntity(joinusEntity);
	}

	/**
	 * 获得所有的报名者
	 * @return
	 */
	public static List<JoinusEntity> getAll(){
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery("from JoinusEntity ");
		List<JoinusEntity> re=query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

}
