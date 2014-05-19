/**
 * Author: WuHaoLin
 * Date: 2014/5/10
 * Time: 14:28
 */

package play.shudong;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.ServiceQiNiu;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/10
 * Time: 14:28
 */
public class ManageShuDongBgImg {

	/**
	 * 获得最新的一个
	 *
	 * @return
	 */
	private static MyShuDongBgImgEntity getNewest() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyShuDongBgImgEntity order by id desc ");
		return (MyShuDongBgImgEntity) query.list().get(0);
	}

	public static MyShuDongBgImgEntity get(int id) {
		if (id == 0) {
			return getNewest();
		}
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyShuDongBgImgEntity where id=?");
		query.setInteger(0, id);
		return (MyShuDongBgImgEntity) query.uniqueResult();
	}

	/**
	 * 获得该条的下一条,如果下一条不存在就返回第一条
	 *
	 * @param id
	 * @return
	 */
	public static MyShuDongBgImgEntity getNext(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyShuDongBgImgEntity where id>?");
		query.setInteger(0, id);
		List reList = query.list();
		MyShuDongBgImgEntity re;
		if (reList.size() < 1) {
			query = session.createQuery("from MyShuDongBgImgEntity where id=1");
			re = (MyShuDongBgImgEntity) query.uniqueResult();
		} else {
			re = (MyShuDongBgImgEntity) reList.get(0);
		}
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 获得该条的上一条,如果上一条不存在就返回最后一条
	 *
	 * @param id
	 * @return
	 */
	public static MyShuDongBgImgEntity getPrev(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyShuDongBgImgEntity where id<?");
		query.setInteger(0, id);
		List reList = query.list();
		MyShuDongBgImgEntity re;
		if (reList.size() < 1) {
			query = session.createQuery("from MyShuDongBgImgEntity");
			reList = query.list();
			re = (MyShuDongBgImgEntity) reList.get(reList.size() - 1);
		} else {
			re = (MyShuDongBgImgEntity) reList.get(reList.size() - 1);
		}
		HibernateUtil.closeSession(session);
		return re;
	}

	public static boolean add(MyShuDongBgImgEntity myShuDongBgImgEntity) {
		return HibernateUtil.addEntity(myShuDongBgImgEntity);
	}

	/**
	 * 删除一张树洞图片
	 * 同时删除七牛云存储的文件
	 * @param id
	 * @return
	 */
	public static boolean remove(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyShuDongBgImgEntity where id=?");
		query.setInteger(0, id);
		MyShuDongBgImgEntity myShuDongEntity = (MyShuDongBgImgEntity) query.uniqueResult();
		session.delete(myShuDongEntity);
		ServiceQiNiu.removeOne(myShuDongEntity.getPicUrl());
		HibernateUtil.closeSession(session);
		return true;
	}

	public static int likeThis(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyShuDongBgImgEntity as shudong where shudong.id=?");
		query.setInteger(0, id);
		MyShuDongBgImgEntity re = (MyShuDongBgImgEntity) query.uniqueResult();
		int reInt = re.getLikeCount() + 1;
		re.setLikeCount(reInt);
		session.save(re);
		HibernateUtil.closeSession(session);
		return reInt;
	}
}
