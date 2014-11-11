package study.CET;

import org.glassfish.jersey.server.JSONP;
import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.R;
import tool.ccnu.student.detailInfo.StudentAllInfoEntity;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by wuhaolin on 9/6/14.
 * :
 */
@Path("/cet")
@Produces({"application/javascript"})
public class ServiceCET {

  /**
   * 对所有已知信息的同学都去把他们的CET成绩抓取并保存
   *
   * @return 成功抓取的个数
   */
  @JSONP(queryParam = R.JSONP_CALLBACK)
  @GET
  @Path("/spiderAll")
  public int spiderAll() {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from StudentAllInfoEntity where idNumber!=null order by xh desc ");
    List<StudentAllInfoEntity> allInfoEntities = query.list();
    HibernateUtil.closeSession(session);
    int count = 0;
    for (StudentAllInfoEntity one : allInfoEntities) {
      for (String grade : ManageCET.GRADE) {
        for (String date : ManageCET.DATE) {
          Cet46Entity cet46Entity = ManageCET.spider(grade, one.getIdNumber(), date);
          if (cet46Entity != null) {
            cet46Entity.setXh(one.getXh());
            cet46Entity.setName(one.getName());
            cet46Entity.setDate(date);
            try {
              HibernateUtil.addOrUpdateEntity(cet46Entity);
              count++;
            } catch (Exception ignored) {
            }
          }
        }
      }
    }
    return count;
  }

}
