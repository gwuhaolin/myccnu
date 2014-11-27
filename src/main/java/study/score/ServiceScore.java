package study.score;

import org.glassfish.jersey.server.JSONP;
import tool.R;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by wuhaolin on 6/30/14.
 * :
 */
@Path("/score")
@Produces({"application/javascript"})
public class ServiceScore {

    @JSONP(queryParam = R.JSONP_CALLBACK)
    @GET
    @Path("/get")
    public List<MyScoreEntity> get_XH(@QueryParam("XH") String xh) {
        return ManageScore.get_XH(xh);
    }

    @JSONP(queryParam = R.JSONP_CALLBACK)
    @GET
    @Path("/get_ClassId")
    public List<MyScoreEntity> get_classId(@QueryParam("classId") String classId) {
        return ManageScore.get_ClassNo(classId);
    }

    /**
     * 对于数据库中所有已经知道了帐号密码的同学,主动去教务处抓取成绩的数据并把成功查询到的保存到数据库
     */
    @JSONP(queryParam = R.JSONP_CALLBACK)
    @GET
    @Path("/updateStudentsScore")
    public int updateStudentsScore() {
        return ManageScore.updateAllStudentsScore();
    }


}
