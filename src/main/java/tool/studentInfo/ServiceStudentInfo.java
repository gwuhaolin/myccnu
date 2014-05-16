/**
 * Author: WuHaoLin
 * Date: 2014/5/15
 * Time: 16:00
 */

package tool.studentInfo;

import org.glassfish.jersey.server.JSONP;
import org.hibernate.Session;
import tool.CCNUPortal;
import tool.HibernateUtil;
import tool.R;
import tool.Tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/15
 * Time: 16:00
 */
@Path("/studentInfo")
@Produces({"application/javascript"})
public class ServiceStudentInfo {

	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	/**
	 * 查询一个同学的信息
	 *
	 * @param XH 如果URL参数中没有XH参数就从Cookies中获得
	 * @return
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getOne")
	public StudentBasicInfoEntity getOneByXH_Query(@QueryParam("XH") String XH) {
		if (XH == null) {
			XH = Tool.getXHMMfromCookie(request)[0];
		}
		return ManageStudentBasicInfo.get_XH(XH);
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/updateOne")
	public StudentBasicInfoEntity updateBasicInfo(
			@QueryParam("name") String name,
			@QueryParam("phoneNumber") String phoneNumber,
			@QueryParam("qq") String qq,
			@CookieParam("XH") String XH) {
		Session session= HibernateUtil.getSession();
		StudentBasicInfoEntity one=(StudentBasicInfoEntity) session.get(StudentBasicInfoEntity.class,XH);
		one.setName(name);
		one.setQq(qq);
		one.setPhoneNumber(phoneNumber);
		session.update(one);
		HibernateUtil.closeSession(session);
		return one;
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/bind")
	public int bind(@QueryParam("XH") String XH, @QueryParam("MM") String MM) {
		if (XH == null || MM == null) {
			return -1;//学号密码请填完整哦
		}
		if (CCNUPortal.XHMMisTrue(XH, MM)) {//密码正确
			Tool.setXHMMtoCookies(response, XH, MM);//保存帐号密码到cookies
			Tool.setXHMMtoSQL(XH, MM);//保存帐号密码到数据库
			return 1;//成功
		} else {//密码错误
			return -2;//密码错误
		}
	}

}
