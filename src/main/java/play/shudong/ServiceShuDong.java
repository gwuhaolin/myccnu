package play.shudong;

import org.glassfish.jersey.server.JSONP;
import tool.R;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/2
 * Time: 15:17
 */
@Path("/shudong")
@Produces({"application/javascript"})
public class ServiceShuDong {
	@Context
	HttpServletRequest request;

	/**
	 * 通过一条树洞的ID获得树洞
	 *
	 * @param id 树洞的ID
	 * @return 一条树洞的完整内容
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getOne")
	public MyShuDongEntity getOneById(@QueryParam("id") int id) {
		return ManageShuDong.get(id);
	}

	/**
	 * 获得多条树洞(分页的方式获得)按照发布时间排序
	 *
	 * @param begin 起始位置
	 * @param cmd   树洞类型
	 * @param XH    请求者的学号
	 * @return 5条树洞
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getPage")
	public List<MyShuDongEntity> getSomeByPage(
			@QueryParam("begin") int begin
			, @QueryParam("cmd") String cmd
			, @CookieParam("XH") String XH) {
		List<MyShuDongEntity> re = null;
		if (cmd.equals("new")) {//最新树洞
			re = ManageShuDong.get_page(begin);
		} else if (cmd.equals("hot")) {//最热树洞
			re = ManageShuDong.get_page_hot(begin);
		} else if (cmd.equals("my")) {//请求者发的树洞
			re = ManageShuDong.get_page_XH(begin, XH);
		} else if (cmd.equals("search")) {//关键字搜索
			re = ManageShuDong.search_page(begin, request.getParameter("want"));
		}
		return re;
	}

	/**
	 * 添加一条树洞
	 *
	 * @param con
	 * @param XH
	 * @return
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/addOne")
	public MyShuDongEntity addOne(
			@DefaultValue("") @QueryParam("say") String con,
			@CookieParam("XH") String XH
	) {
		MyShuDongEntity myShuDongEntity = new MyShuDongEntity(con, XH);
		ManageShuDong.add(myShuDongEntity);
		return myShuDongEntity;
	}

	/**
	 * 为一条树洞点赞
	 *
	 * @param id 树洞的Id
	 * @return 树洞的新的点赞总数
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/likeIt")
	public int likeIt(@QueryParam("id") int id) {
		return ManageShuDong.likeThis(id);
	}

	/**
	 * 删除一条树洞
	 *
	 * @param id 树洞的Id
	 * @return 如果删除成功返回true
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/deleteOne")
	public boolean deleteOne(@QueryParam("id") int id) {
		return ManageShuDong.delete(id);
	}

}
