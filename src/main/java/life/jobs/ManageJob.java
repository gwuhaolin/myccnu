package life.jobs;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tool.AutoUpdate;
import tool.HibernateUtil;
import tool.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/25/14
 * Time: 6:27 PM
 */
public class ManageJob {

  public static final int TARGET_PartTimeJob = 1;
  public static final int TARGET_PrivateTeacher = 2;
  private static final String ManagePassword = "JOBS";
  public static final String CMD_Change = "change";
  public static final String CMD_Add = "add";
  public static final String CMD_Delete = "delete";

  static {
    update();
    AutoUpdate.start();
  }

  /**
   * 验证管理员帐号密码是否正确
   *
   * @param password
   * @return
   */
  public static boolean managePasswordIsOK(String password) {
    return password.equals(ManagePassword);
  }

  /**
   * 向数据库中添加一个工作
   *
   * @param jobEntity
   */
  public static void add(MyJobEntity jobEntity) {
    HibernateUtil.addEntity(jobEntity);
  }

  /**
   * 向数据库中添加很多工作
   */
  public static void add(List<MyJobEntity> jobEntitys) {
    for (MyJobEntity jobEntity : jobEntitys) {
      HibernateUtil.addEntity(jobEntity);
    }
  }

  /**
   * 向数据库中添加工作要求内容不能一样
   */
  private static void add_NotSame(List<MyJobEntity> jobEntitys) {
    for (MyJobEntity jobEntity : jobEntitys) {
      if (!DBhasThisOne(jobEntity)) {
        HibernateUtil.addEntity(jobEntity);
      }
    }
  }

  /**
   * 数据库中是否有内容一样的记录
   *
   * @return 如果有返回true
   */
  private static boolean DBhasThisOne(MyJobEntity jobEntity) {
    if (jobEntity == null) {
      return true;
    }
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyJobEntity as job where  job.jobInfo=? and job.target=?");
    query.setString(0, jobEntity.getJobInfo());
    query.setInteger(1, jobEntity.getTarget());
//		query.setInteger(2,jobEntity.getTarget());
//		query.setString(3,jobEntity.getJobLocation());
//		query.setString(4,jobEntity.getJobTime());
    int reSize = query.list().size();
    HibernateUtil.closeSession(session);
    return reSize >= 1;
  }

  /**
   * 从数据库中获得一个
   *
   * @param id
   * @return
   */
  public static MyJobEntity get(int id) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyJobEntity as job where job.id=?");
    query.setInteger(0, id);
    MyJobEntity re = (MyJobEntity) query.uniqueResult();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 用于分页查询,关键字搜索
   *
   * @param from 从这个开始
   * @return
   */
  public static List<MyJobEntity> search_page(int from, String want) {
    System.out.println("from:" + from + " want:" + want);
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyJobEntity where jobInfo like ? or name like ? order by id desc ");
    query.setString(0, "%" + want + "%");
    query.setString(1, "%" + want + "%");
    query.setFirstResult(from);
    query.setMaxResults(R.ChangeCount);
    List<MyJobEntity> re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 用于分页查询,按照发布时间排序
   *
   * @param from 从这个开始
   * @return
   */
  public static List<MyJobEntity> get_page(int from, int target) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyJobEntity where target=? order by id desc ");
    query.setInteger(0, target);
    query.setFirstResult(from);
    query.setMaxResults(R.ChangeCount);
    List<MyJobEntity> re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 用于分页查询,按照id排序
   *
   * @param from 从这个开始
   * @return
   */
  public static List<MyJobEntity> get_OrderByID(int from, int target) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyJobEntity where target=? order by id desc ");
    query.setInteger(0, target);
    query.setFirstResult(from);
    query.setMaxResults(R.ChangeCount);
    List<MyJobEntity> re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 数据库中删除一个job
   *
   * @param id
   */
  public static void delete(int id) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("delete MyJobEntity where id=?");
    query.setInteger(0, id);
    query.executeUpdate();
    HibernateUtil.closeSession(session);
  }

  /**
   * 向数据库中更新一个
   *
   * @param jobEntity
   */
  public static void change(MyJobEntity jobEntity) {
    HibernateUtil.updateEntity(jobEntity);
  }

  /**
   * 扫描自动抓取兼职信息
   *
   * @return
   */
  public static List<MyJobEntity> scanPartTimeJobs() {
    List<MyJobEntity> re = new LinkedList<MyJobEntity>();
    try {
      //找出最新的一张网页
      Document document = Jsoup.connect("http://bbs.ccnu.edu.cn/index.php?/forum/5165-%E5%85%BC%E8%81%8C%E4%BF%A1%E6%81%AF/").get();
      Elements links = document.getElementById("forum_table").getElementsByClass("topic_title");
      Element theNewestLink = links.get(0);
      for (Element a : links) {
        if (a.text().matches(".*兼.*职.*")) {
          theNewestLink = a;
          break;
        }
      }

      //对该网页分析
      document = Jsoup.connect(theNewestLink.attr("href")).get();
      String content = document.getElementsByClass("entry-content").first().html();
      String[] brs = content.split("<br.*>");
      MyJobEntity newOne = null;
      for (String text : brs) {
        String two[] = text.split("：", 2);
        if (two.length < 2) {
          continue;
        }
        if (two[0].matches(".*名.*称.*")) {
          re.add(newOne);
          newOne = new MyJobEntity();
          newOne.setTarget(TARGET_PartTimeJob);
          newOne.setManager(two[1]);//公司名称
        } else if (two[0].matches(".*地(点|址).*")) {
          newOne.setJobLocation(two[1]);//工作地点
        } else if (two[0].matches(".*内.*容.*")) {
          newOne.setJobInfo(two[1]);//工作内容
        } else if (two[0].matches(".*时.*间.*")) {
          newOne.setJobTime(two[1]);//工作时间
        } else if (two[0].matches(".*酬.*金.*")) {
          newOne.setMoney(two[1]);//工作报酬
        } else if (two[0].matches(".*备.*注.*")) {
          newOne.setOtherInfo(two[1]);
        }
      }
      re.add(newOne);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return re;
  }

  /**
   * 扫描自动抓取家教信息
   *
   * @return
   */
  public static List<MyJobEntity> scanPrivateTeacher() {
    List<MyJobEntity> re = new LinkedList<MyJobEntity>();
    try {
      //找出最新的一张网页
      Document document = Jsoup.connect("http://bbs.ccnu.edu.cn/index.php?/forum/5163-%E5%8D%8E%E5%A4%A7%E8%B5%84%E5%8A%A9/").get();
      Elements links = document.getElementById("forum_table").getElementsByClass("topic_title");
      Element theNewestLink = links.get(0);
      for (Element a : links) {
        if (a.text().matches(".*家.*教.*")) {
          theNewestLink = a;
          break;
        }
      }

      //对该网页分析
      document = Jsoup.connect(theNewestLink.attr("href")).get();
      String content = document.getElementsByClass("entry-content").first().html();
      String[] brs = content.split("<br.*>");
      MyJobEntity newOne = null;
      for (String one : brs) {
        String two[] = one.split("：", 2);
        if (two.length < 2) {
          continue;
        }
        if (two[0].matches(".*编.*号.*")) {
          re.add(newOne);
          newOne = new MyJobEntity();
          newOne.setTarget(TARGET_PrivateTeacher);
          newOne.setManager(two[1]);
        } else if (two[0].matches(".*科.*目.*")) {
          newOne.setJobInfo(two[1]);//课程内容
        } else if (two[0].matches(".*要.*求.*")) {
          newOne.setName(two[1]);//详细要求
        } else if (two[0].matches(".*地(点|址).*")) {
          newOne.setJobLocation(two[1]);
        }
      }
      re.add(newOne);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return re;
  }

  /**
   * 扫描更新
   */
  public static void update() {
    System.out.println("开始扫描家教兼职信息");
    add_NotSame(scanPartTimeJobs());
    add_NotSame(scanPrivateTeacher());
    System.out.println("结束扫描家教兼职信息");
  }
}
