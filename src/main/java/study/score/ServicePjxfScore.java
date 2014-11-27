package study.score;

import org.glassfish.jersey.server.JSONP;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.HibernateUtil;
import tool.R;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashSet;
import java.util.List;

/**
 * Created by wuhaolin on 8/30/14.
 * :
 */
@Path("/pjxfScore")
@Produces({"application/javascript"})
public class ServicePjxfScore {

    private static final Logger log = LoggerFactory.getLogger(ServicePjxfScore.class);


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
            log.info("成功更新" + one + "的平均学分成绩");
            ManageMyPjxfScore.update(one);
        }
    }

}
