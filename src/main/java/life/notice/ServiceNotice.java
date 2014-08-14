package life.notice;

import org.glassfish.jersey.server.JSONP;
import tool.R;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by wuhaolin on 8/8/14.
 * :
 */
@Path("/notice")
@Produces("application/javascript")
public class ServiceNotice {

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/list")
	public MyNoticeEntity[] list(@QueryParam("from") int from, @QueryParam("want") String want) {
		List<MyNoticeEntity> re;
		if (want != null) {
			re = ManageNotice.search_page(from, want);
		} else {
			re = ManageNotice.get_page(from);
		}
		return ManageNotice.simpleList(re);
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/one")
	public MyNoticeEntity one(@QueryParam("id") int id) {
		return ManageNotice.get(id);
	}
}
