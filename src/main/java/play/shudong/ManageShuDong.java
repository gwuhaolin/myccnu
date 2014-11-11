package play.shudong;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/23/14
 * Time: 12:03 PM
 */
public class ManageShuDong {

  public static final String CMD_Change = "change";//修改树洞
  public static final String CMD_Delete = "delete";//删除树洞
  public static final String CMD_Add = "add";//添加树洞
  private static final String PASSWORD = "SHUDONG";//密码

  /**
   * 判断密码是否正确
   *
   * @param pass
   * @return
   */
  public static boolean passwordIsOk(String pass) {
    return pass.equals(PASSWORD);
  }

  /**
   * 用id去数据库中获得一条树洞
   *
   * @param id
   * @return
   */
  public static MyShuDongEntity get(int id) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyShuDongEntity as shudong where shudong.id=?");
    query.setInteger(0, id);
    MyShuDongEntity re = (MyShuDongEntity) query.uniqueResult();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 获得学号为XH的同学发的树洞
   *
   * @param from
   * @param XH
   * @return
   */
  public static List<MyShuDongEntity> get_page_XH(int from, String XH) {
    if (XH == null || XH.length() < 2) {
      return new LinkedList<MyShuDongEntity>();
    }
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyShuDongEntity where xh=? order by id desc ");
    query.setString(0, XH);
    query.setFirstResult(from);
    query.setMaxResults(R.ChangeCount);
    List<MyShuDongEntity> re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 用发布时间排序来获得用于分页查询
   *
   * @param from 从这个开始
   * @return
   */
  public static List<MyShuDongEntity> get_page(int from) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyShuDongEntity order by id desc ");
    query.setFirstResult(from);
    query.setMaxResults(R.ChangeCount);
    List<MyShuDongEntity> re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 用热度排序来获得用于分页查询
   *
   * @param from 从这个开始
   * @return
   */
  public static List<MyShuDongEntity> get_page_hot(int from) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyShuDongEntity order by seeCount desc ");
    query.setFirstResult(from);
    query.setMaxResults(R.ChangeCount);
    List<MyShuDongEntity> re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 根据 关键字搜索 来查询物品
   *
   * @param from 从这里开始
   * @param want 关键字
   * @return
   */
  public static List<MyShuDongEntity> search_page(int from, String want) {
    if (want == null) {
      return null;
    }
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyShuDongEntity where content like ? order by id desc ");
    query.setString(0, "%" + want + "%");
    query.setFirstResult(from);
    query.setMaxResults(R.ChangeCount);
    List<MyShuDongEntity> re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }


  /**
   * 点赞
   *
   * @param id
   * @return 赞后的已经赞数
   */
  public static int likeThis(int id) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyShuDongEntity as shudong where shudong.id=?");
    query.setInteger(0, id);
    MyShuDongEntity re = (MyShuDongEntity) query.uniqueResult();
    int reInt = re.getSeeCount() + 1;
    re.setSeeCount(reInt);
    session.save(re);
    HibernateUtil.closeSession(session);
    return reInt;
  }

  /**
   * 向数据库中添加一条树洞,树洞的发布时间就是id排序
   *
   * @param shuDongEntity
   */
  public static void add(MyShuDongEntity shuDongEntity) {
    HibernateUtil.addEntity(shuDongEntity);
  }

  /**
   * 修改一个树洞
   *
   * @param shuDongEntity
   */
  public static boolean change(MyShuDongEntity shuDongEntity) {
    try {
      HibernateUtil.updateEntity(shuDongEntity);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * 删除一条树洞,如果不存在就不管了
   *
   * @param id
   * @return 如果删除成功, 返回true, 失败返回false
   */
  public static boolean delete(int id) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("DELETE MyShuDongEntity WHERE id = " + id);
    int result = query.executeUpdate();
    HibernateUtil.closeSession(session);
    return result > 0;
  }

}
