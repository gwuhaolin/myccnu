package study.score;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.ccnu.academy.ManageAcademy;

import java.util.List;

/**
 * Created by wuhaolin on 8/30/14.
 * :管理同学的平均学分成绩
 */
public class ManageMyPjxfScore {

  /**
   * 所有的任选课的编号(所有的任选课都会被排除在计算平均学分成绩里)
   */
  public static final String RenXuanKeClassNumber[] = {

  };

  /**
   * 获得学号为xh的同学的平均学分成绩
   * @param xh 学号
   * @return 成绩
   */
  public static MyPjxfScoreEntity get(String xh) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyPjxfScoreEntity where xh=?");
    query.setString(0, xh);
    MyPjxfScoreEntity re = (MyPjxfScoreEntity) query.uniqueResult();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 获得所有的一个学院的同学的一个年级的所有同学的平均学分成绩
   * 安装分数排序
   * @param xh 同学的学号
   * @return 所有的成绩
   */
  public static List<MyPjxfScoreEntity> list(String xh) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyPjxfScoreEntity where academy=? and xh like ? order by score desc ");
    query.setInteger(0, ManageAcademy.isWhichAcademy(xh));
    query.setString(1, xh.substring(0, 4) + "%");
    List<MyPjxfScoreEntity> re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }


  /**
   * 从数据库中取出该同学的成绩然后计算出一个同学的平均学分成绩
   * 然后把新计算出的结果更新到数据库中
   *
   * @param xh 同学的学号
   */
  public static void update(String xh) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyScoreEntity where xh=?");
    query.setString(0, xh);
    List<MyScoreEntity> allScore = query.list();
    MyPjxfScoreEntity newOne = new MyPjxfScoreEntity();
    newOne.setXh(xh);
    newOne.setAcademy(ManageAcademy.isWhichAcademy(xh));
    float scoreSum = 0, xuefenSum = 0;
    for (MyScoreEntity one : allScore) {
      //所有的非任选的成绩才计入
      if (!isRenXuan(one.getClassNo())) {
        scoreSum += one.getSumScore();
        xuefenSum += one.getXuefen();
      }
    }
    newOne.setScore(scoreSum / xuefenSum);
    HibernateUtil.addOrUpdateEntity(newOne);
  }

  /**
   * 判断是否是任选课
   *
   * @param classNumber 要判断的课的编号
   * @return 是否是任选课
   */
  private static boolean isRenXuan(String classNumber) {
    for (String one : RenXuanKeClassNumber) {
      if (classNumber.equals(one)) {
        return true;
      }
    }
    return false;
  }
}
