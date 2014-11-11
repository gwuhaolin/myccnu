package play.movie;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.R;

import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/11/14
 * Time: 10:09 PM
 */
public class ManageMovie {

  public static final String Target_HDQNJC = "HDQNJC";
  public static final String Target_LTDYC = "LTDYC";
  public static final String Target_iWANT = "iWANT";
  private static final String Password_HDQNJC = "HDQNJC";
  private static final String Password_LTDYC = "LTDYC";
  private static final String Password_iWANT = "iWANT";
  public static final String Cmd_add = "add";
  public static final String Cmd_change = "change";
  public static final String Cmd_delete = "delete";

  /**
   * 向数据库中添加一电影
   *
   * @param movieEntity
   */
  public static void add(MyMovieEntity movieEntity) {
    HibernateUtil.addEntity(movieEntity);
  }

  /**
   * 改变一电影的内容
   *
   * @param movieEntity
   * @return
   */
  public static void change(MyMovieEntity movieEntity) {
    HibernateUtil.updateEntity(movieEntity);
  }

  /**
   * 用唯一表示电影的Id获得一电影
   *
   * @param id
   * @return 如果不存在该Id的movie就返回null
   */
  public static MyMovieEntity get(int id) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyMovieEntity as movies where movies.id=?");
    query.setInteger(0, id);
    MyMovieEntity re = (MyMovieEntity) query.uniqueResult();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 获得目标为target的所有电影
   *
   * @param target
   * @return
   */
  public static List<MyMovieEntity> get(String target) {
    if (target.equals(Target_HDQNJC) || target.equals(Target_LTDYC) || target.equals(Target_iWANT)) {
      Session session = HibernateUtil.getSession();
      Query query = session.createQuery("from MyMovieEntity as movie where movie.target=?");
      query.setMaxResults(15);
      query.setString(0, target);
      List<MyMovieEntity> re = query.list();
      HibernateUtil.closeSession(session);
      return re;
    } else {
      return null;
    }
  }

  /**
   * 用于分页查询
   *
   * @param from 从这个开始
   * @return
   */
  public static List<MyMovieEntity> get_page(int from, String target) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyMovieEntity as movie where movie.target=? order by id desc ");
    query.setString(0, target);
    query.setFirstResult(from);
    query.setMaxResults(R.ChangeCount);
    List<MyMovieEntity> re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 把这个电影从数据库中删除
   *
   * @param movieEntity
   */
  public static void remove(MyMovieEntity movieEntity) {
    HibernateUtil.removeEntity(movieEntity);
  }

  /**
   * 把这个电影从数据库中删除
   *
   * @param id
   */
  public static void remove(int id) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("DELETE MyMovieEntity WHERE id = " + id);
    query.executeUpdate();
    HibernateUtil.closeSession(session);
  }

  /**
   * 检验管理员身份合法性
   *
   * @param password
   * @param target
   * @return 如果验证合法返回true否则返回false
   */
  public static boolean ManagePasswordisOk(String password, String target) {
    if (target.equalsIgnoreCase(Target_HDQNJC)) {
      return password.equals(Password_HDQNJC);
    } else if (target.equalsIgnoreCase(Target_LTDYC)) {
      return password.equals(Password_LTDYC);
    } else if (target.equals(Target_iWANT)) {
      return password.equals(Password_iWANT);
    } else {
      return false;
    }
  }

  public static String targetToChineseString(String target) {
    String re = "校园电影";
    if (target.equals(ManageMovie.Target_HDQNJC)) {
      re = "华大青年剧场";
    } else if (target.equals(ManageMovie.Target_LTDYC)) {
      re = "露天电影场";
    } else if (target.equals(ManageMovie.Target_iWANT)) {
      re = "同学们分享的电影";
    }
    return re;
  }

}
