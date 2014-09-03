package tool.ccnu.student.detailInfo;

import org.glassfish.jersey.server.JSONP;
import tool.R;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by wuhaolin on 9/3/14.
 * :
 */
@Path("/studentAllInfo")
@Produces({"application/javascript"})
public class ServiceStudentAllInfo {

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/downloadAndStoreToSQLFromJWCwhereInfoNull")
	public int ded(){
		return ManageStudentAllInfo.downloadAndStoreToSQLFromJWCwhereInfoNull();
	}
}
