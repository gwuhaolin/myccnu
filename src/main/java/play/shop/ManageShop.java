/**
 * Author: WuHaoLin
 * Date: 2014/5/13
 * Time: 10:03
 */

package play.shop;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/13
 * Time: 10:03
 */

/**
 * 逻辑业务层,操作数据库
 */
public class ManageShop {

	/**
	 * 分页浏览时每次出现几条
	 */
	public static final int ChangeCount = 5;

	/**
	 * 后台管理密码
	 */
	public static final String ManagePassword = "SHOP";


	/**
	 * 向数据库中添加一个物品,且自动为其分类
	 *
	 * @param shopItemsEntity
	 * @return
	 */
	public static ShopItemsEntity add(ShopItemsEntity shopItemsEntity) {
		shopItemsEntity.setTag(analyseTag(shopItemsEntity));
		HibernateUtil.addEntity(shopItemsEntity);
		return shopItemsEntity;
	}

	/**
	 * 从数据库中活动一个商品的信息
	 *
	 * @param id 商品的Id
	 * @return
	 */
	public static ShopItemsEntity get(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from ShopItemsEntity  where id=?");
		query.setInteger(0, id);
		ShopItemsEntity re = (ShopItemsEntity) query.uniqueResult();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 向数据库中更新一个物品的信息
	 *
	 * @param one
	 * @return 成功与否
	 */
	public static boolean update(ShopItemsEntity one) {
		return HibernateUtil.updateEntity(one);
	}

	/**
	 * 向数据库中删除一个物品的信息
	 *
	 * @param id 物品的Id
	 * @return 成功与否
	 */
	public static boolean remove(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("delete from ShopItemsEntity where id=?");
		query.setInteger(0, id);
		int re = query.executeUpdate();
		HibernateUtil.closeSession(session);
		return re > 0;
	}

	/**
	 * 根据 标签 来查询物品
	 * 获得多条物品的信息,用于分页查询
	 *
	 * @param from  从这里开始
	 * @param tagID 所属标签的Id
	 * @return
	 */
	public static List<ShopItemsEntity> get_Page_tag(int from, int tagID) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from ShopItemsEntity where tag=? order by id desc ");
		query.setInteger(0, tagID);
		query.setFirstResult(from);
		query.setMaxResults(ChangeCount);
		List<ShopItemsEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 根据 发布时间 来查询物品
	 * 获得多条物品的信息,用于分页查询
	 *
	 * @param from 从这里开始
	 * @return
	 */
	public static List<ShopItemsEntity> get_Page(int from) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from ShopItemsEntity order by id desc ");
		query.setFirstResult(from);
		query.setMaxResults(ChangeCount);
		List<ShopItemsEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 根据 受关注程度 来查询物品
	 * 获得多条物品的信息,用于分页查询
	 *
	 * @param from 从这里开始
	 * @return
	 */
	public static List<ShopItemsEntity> get_page_hot(int from) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from ShopItemsEntity order by seeCount desc ");
		query.setFirstResult(from);
		query.setMaxResults(ChangeCount);
		List<ShopItemsEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 根据 查询者的学号,返回对应学号所发布的物品 来查询物品
	 * 获得多条物品的信息,用于分页查询
	 *
	 * @param from 从这里开始
	 * @return
	 */
	public static List<ShopItemsEntity> get_page_XH(int from, String XH) {
		if (XH == null || XH.length() < 2) {
			return null;
		}
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from ShopItemsEntity where ownerXh=? order by id desc ");
		query.setString(0, XH);
		query.setFirstResult(from);
		query.setMaxResults(ChangeCount);
		List<ShopItemsEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 根据 关键字搜索 来查询物品
	 * 获得多条物品的信息,用于分页查询
	 *
	 * @param from 从这里开始
	 * @param want 关键字
	 * @return
	 */
	public static List<ShopItemsEntity> search_page(int from, String want) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from ShopItemsEntity as item where name like ? or des like ? order by id desc");
		query.setString(0, "%" + want + "%");
		query.setString(1, "%" + want + "%");
		query.setFirstResult(from);
		query.setMaxResults(ChangeCount);
		List<ShopItemsEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 浏览者参看了该物品的详细信息时调用,使该物品的受关注数++
	 *
	 * @param id 物品的Id
	 * @return 该物品的新的受关注数
	 */
	public static int likeIt(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from ShopItemsEntity where id=?");
		query.setInteger(0, id);
		ShopItemsEntity one = (ShopItemsEntity) query.uniqueResult();
		one.setSeeCount(one.getSeeCount() + 1);
		session.update(one);
		HibernateUtil.closeSession(session);
		return one.getSeeCount();
	}

	/**
	 * 获得所有标签
	 *
	 * @return
	 */
	public static List<ShopTagsEntity> getTags() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from ShopTagsEntity");
		List<ShopTagsEntity> re = query.list();
		Collections.sort(re);
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 添加一个标签
	 *
	 * @param tagName 标签的名称
	 * @return 标签的ID
	 */
	public static int addTag(String tagName) {
		ShopTagsEntity shopTagsEntity = new ShopTagsEntity();
		shopTagsEntity.setName(tagName);
		HibernateUtil.addEntity(shopTagsEntity);
		return shopTagsEntity.getId();
	}

	/**
	 * 根据物品的名称和描述自动分析所属标签
	 * @return 所属标签
	 */
	private static int analyseTag(ShopItemsEntity shopItemsEntity) {
		return 0;
	}
}
