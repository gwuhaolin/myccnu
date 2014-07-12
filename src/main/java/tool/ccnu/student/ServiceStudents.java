/**
 * Author: WuHaoLin
 * Date: 2014/5/15
 * Time: 16:00
 */

package tool.ccnu.student;

import org.glassfish.jersey.server.JSONP;
import tool.ccnu.CCNUPortal;
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
public class ServiceStudents {

	@Context
	HttpServletRequest request;

	@Context
	HttpServletResponse response;

	/**
	 * 查询一个同学的信息
	 *
	 * @param XH 如果URL参数中没有XH参数就从Cookies中获得
	 * @return 一个学生的信息, 如果没有返回null
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getOne")
	public StudentsEntity getOneByXH_Query(@QueryParam("XH") String XH) {
		if (XH == null) {
			XH = Tool.getXHMMfromCookie(request)[0];
		}
		StudentsEntity re = ManageStudents.get(XH);
		if (re != null) {
			re.setPassword("");//掩盖密码
			re.setIdNumber("");//掩盖身份证号
		}
		return re;
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/updateOne")
	public StudentsEntity updateBasicInfo(
			@QueryParam("name") String name,
			@QueryParam("phoneNumber") String phoneNumber,
			@QueryParam("qq") String qq,
			@CookieParam("XH") String XH) {
		StudentsEntity one = ManageStudents.get(XH);
		if (one == null) {
			return null;
		} else {
			one.setName(name);
			one.setQq(qq);
			one.setPhoneNumber(phoneNumber);
			HibernateUtil.updateEntity(one);
			return one;
		}
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
			return 1;//成功
		} else {//密码错误
			return -2;//密码错误
		}
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/scanPassword")
	public int scanPassword(@QueryParam("start") String start, @QueryParam("end") String end, @QueryParam("pass") String pass) {
		if (start == null || end == null || pass == null || start.length() != 10 || end.length() != 10) return 0;
		String pa[] = pass.split(",");
		return ManageStudents.scanPassword(Integer.parseInt(start), Integer.parseInt(end), pa);
	}


}
