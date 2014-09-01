package study.score;

import org.glassfish.jersey.server.JSONP;
import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.R;

import javax.ws.rs.*;
import java.util.HashSet;
import java.util.List;

/**
 * Created by wuhaolin on 8/30/14.
 * :
 */
@Path("/pjxfScore")
@Produces({"application/javascript"})
public class ServicePjxfScore {

  @JSONP(queryParam = R.JSONP_CALLBACK)
  @GET
  @Path("/list")
  public List<MyPjxfScoreEntity> get(@CookieParam("XH") String xh) {
    return ManageMyPjxfScore.list(xh);
  }


  /**
   * 调用该接口会把成绩库里的所有的同学的平均学分成绩计算一边
   */
  @JSONP(queryParam = R.JSONP_CALLBACK)
  @GET
  @Path("/updateAll")
  public void updateAll() {
    Session session = HibernateUtil.getSession();
    Query query = session.createQuery("from MyScoreEntity ");
    List<MyScoreEntity> list = query.list();
    HibernateUtil.closeSession(session);
    HashSet<String> set = new HashSet<>();
    for (MyScoreEntity one : list) {
      set.add(one.getXh());
    }
    for (String one : set) {
      ManageMyPjxfScore.update(one);
    }
  }

}
