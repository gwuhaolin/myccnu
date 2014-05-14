/**
 * Author: WuHaoLin
 * Date: 2014/5/10
 * Time: 20:49
 */

package tool;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.rs.PutPolicy;
import org.glassfish.jersey.server.JSONP;
import org.json.JSONException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/10
 * Time: 20:49
 */
@Path("/qiniu/")
@Produces({ "application/javascript"})
public class ServiceQiNiu {
	static {
		//配置七牛
		Config.ACCESS_KEY = "m6OXWa6rNQMvPg9OyN_cFEaAR_uI6s03PJMGD5OM";
		Config.SECRET_KEY = "OI0ako8butjkUEP81BPEFDpkDehxdVqL8e0FWJhG";
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getToken")
	public static String getUpToken(@QueryParam("bucket") String bucket){
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		PutPolicy putPolicy = new PutPolicy(bucket);
		String uptoken=null;
		try {
			uptoken= "'"+putPolicy.token(mac)+"'";
		} catch (AuthException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return uptoken;
	}

}
