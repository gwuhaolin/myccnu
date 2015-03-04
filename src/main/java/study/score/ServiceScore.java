package study.score;

import org.glassfish.jersey.server.JSONP;
import tool.NetworkException;
import tool.R;
import tool.ValidateException;

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
        return ManageScore.query_XH(xh);
    }

    /**
     * 去抓取一个同学的最新的成绩
     *
     * @param XH 存储在cookies里的学号
     * @param MM 存储在cookie里的密码
     * @return -1=账号密码错误  0=抓取失败,教务处网站出问题 1=抓取成功
     */
    @JSONP(queryParam = R.JSONP_CALLBACK)
    @GET
    @Path("/update")
    public int update(@CookieParam("XH") String XH, @CookieParam("MM") String MM) {
        try {
            ManageScore.spider(XH, MM);
            return 1;
        } catch (NetworkException e) {
            return 0;
        } catch (ValidateException e) {
            return -1;
        }
    }

    @JSONP(queryParam = R.JSONP_CALLBACK)
    @GET
    @Path("/get_ClassId")
    public List<MyScoreEntity> get_classId(@QueryParam("classId") String classId) {
        return ManageScore.query_ClassNo(classId);
    }

    @JSONP(queryParam = R.JSONP_CALLBACK)
    @GET
    @Path("/updateStudentsScore")
    public int updateStudentsScore(){
        return ManageScore.updateAllStudentsScore();
    }

}
