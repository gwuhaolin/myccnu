package life.repairComputer;

import tool.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 14-2-13
 * Time: 下午5:13
 */
public class ManageRepair {

  public static final String Org_BenTen = "BT";
  public static final String Org_XinGuang = "XG";

  /**
   * 有问题同学请求一个维修，然后处理该维修（发送维修信息到维修人员，存储到数据库）
   * 给工作最少的维修人员添加此工作
   *
   * @param RepairComputerJobsEntity
   * @return 维修队员的ID
   */
  public static RepairComputerMansEntity addJob(String org) {
    Session session = HibernateUtil.getSession();
    Query query;
    if (org.equals(Org_BenTen) || org.equals(Org_XinGuang)) {
      query = session.createQuery("from RepairComputerMansEntity where org=? order by jobsCount");
      query.setString(0, org);
    } else {
      query = session.createQuery("from RepairComputerMansEntity order by jobsCount");
    }
    query.setMaxResults(1);
    RepairComputerMansEntity repairComputerMansEntity = (RepairComputerMansEntity) query.uniqueResult();
    repairComputerMansEntity.setJobsCount(repairComputerMansEntity.getJobsCount() + 1);
    session.update(repairComputerMansEntity);
    HibernateUtil.closeSession(session);
    return repairComputerMansEntity;
  }

  public static void main(String[] args) {

  }

}
