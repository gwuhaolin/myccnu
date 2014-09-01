package tool.ccnu.academy;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.ccnu.student.ManageStudents;
import tool.ccnu.student.StudentsEntity;

import java.util.List;

/**
 * Created by wuhaolin on 7/6/14.
 * :
 */
public class ManageAcademy {

  /**
   * 用id获得一个学院的信息
   *
   * @param id 学院的id
   * @return 一个学院, 如果该学院的id不存在返回null
   */
  public static AcademyEntity get(int id) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from AcademyEntity where id=?");
    query.setInteger(0, id);
    Object re = query.uniqueResult();
    HibernateUtil.closeSession(session);
    if (re != null) {
      return (AcademyEntity) re;
    } else {
      return null;
    }
  }

  /**
   * 用学院的姓名获得
   *
   * @param name 学院的名称
   * @return 一个学院
   */
  public static AcademyEntity get(String name) {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from AcademyEntity where academyName=?");
    query.setString(0, name);
    Object re = query.uniqueResult();
    HibernateUtil.closeSession(session);
    if (re != null) {
      return (AcademyEntity) re;
    } else {
      return null;
    }
  }

  /**
   * 获得数据库中所有的学院
   *
   * @return 所有的学院
   */
  public static List<AcademyEntity> getAll() {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from AcademyEntity");
    List re = query.list();
    HibernateUtil.closeSession(session);
    return re;
  }

  /**
   * 判断该学号的同学是哪个院的
   *
   * @param xh 学号
   * @return 院系的id, 如果不知道该学号的同学是哪个院就返回0
   */
  public static int isWhichAcademy(String xh) {
    StudentsEntity studentsEntity = ManageStudents.get(xh);
    AcademyEntity academyEntity = studentsEntity.getAcademyByAcademy();
    if (academyEntity == null) {
      return 0;
    }
    return academyEntity.getId();
  }
}
