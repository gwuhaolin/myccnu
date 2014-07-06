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
	public List<MyScoreEntity> get(@CookieParam("XH") String xh, @CookieParam("MM") String mm) {
		return ManageScore.get(xh, mm);
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/get_XH")
	public List<MyScoreEntity> get_XH(@QueryParam("XH") String xh) {
		return ManageScore.get_XH(xh);
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/get_ClassId")
	public List<MyScoreEntity> get_classId(@QueryParam("classId") String classId) {
		return ManageScore.get_ClassNo(classId);
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/updateStudentsScore")
	public int updateStudentsScore() {
		return ManageScore.updateAllStudentsScore();
	}


}
