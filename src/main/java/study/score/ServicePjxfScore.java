package study.score;

import org.glassfish.jersey.server.JSONP;
import tool.R;

import javax.ws.rs.*;
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

}
