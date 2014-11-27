/**
 * Author: WuHaoLin
 * Date: 2014/5/13
 * Time: 10:03
 */

package play.shop;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.R;
import tool.ServiceQiNiu;

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
class ManageShopItem {

    /**
     * 当物品的图片URL为空时的默认URL
     */
    public static final String DEFAULT_PIC_URL = "http://myccnushop.qiniudn.com/default.png";

    /**
     * 向数据库中添加一个物品,且自动为其分类
     *
     * @param shopItemsEntity
     * @return
     */
    public static boolean add(ShopItemsEntity shopItemsEntity) {
        return HibernateUtil.addEntity(shopItemsEntity);
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
     * 同时删除了七牛云存储上的文件
     *
     * @param id 物品的Id
     * @return 成功与否
     */
    public static boolean remove(int id) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from ShopItemsEntity where id=?");
        query.setInteger(0, id);
        ShopItemsEntity one = (ShopItemsEntity) query.uniqueResult();
        String picUrl = one.getPicUrl();
        if (picUrl != null && !picUrl.equals(DEFAULT_PIC_URL)) {
            ServiceQiNiu.removeOne(one.getPicUrl());
        }
        session.delete(one);
        HibernateUtil.closeSession(session);
        return true;
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
        query.setMaxResults(R.ChangeCount);
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
        query.setMaxResults(R.ChangeCount);
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
        Query query = session.createQuery("from ShopItemsEntity where xh=? order by id desc ");
        query.setString(0, XH);
        query.setFirstResult(from);
        query.setMaxResults(R.ChangeCount);
        List<ShopItemsEntity> re = query.list();
        HibernateUtil.closeSession(session);
        return re;
    }

    /**
     * 根据 关键字搜索 来查询物品
     * 获得多条物品的信息,用于分页查询
     * 搜索值,如果有多个就以逗号隔开,搜索结果为所有的值相加
     *
     * @param from 从这里开始
     * @param want 关键字
     * @return
     */
    public static List<ShopItemsEntity> search_page(int from, String want) {
        String[] all = want.split(",");
        String sql = "from ShopItemsEntity as item where ";
        for (int i = 0; i < all.length - 1; i++) {
            String one = all[i];
            sql += " name like '%" + one + "%' or des like '%" + one + "%' or ";
        }
        sql += " name like '%" + all[all.length - 1] + "%' or des like '%" + all[all.length - 1] + "%'";
        sql += " order by id desc";
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery(sql);
        query.setFirstResult(from);
        query.setMaxResults(R.ChangeCount);
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
     * 获得所有的搜索标签
     * 按照权值排序
     *
     * @return
     */
    public static List<ShopSearchTagEntity> getSearchTag() {
        Session session = HibernateUtil.getSession();
        return session.createQuery("from ShopSearchTagEntity order by weight desc ").list();
    }

}
