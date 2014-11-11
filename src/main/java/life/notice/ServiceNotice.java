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

  /**
   * 提供多个文章列表
   *
   * @param from 从哪里开始
   * @param want 如果是想要分页查询就传入null,如果是要关键字搜索就传入关键字
   * @return 从数据库里获得到的结果
   */
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

  /**
   * 用一篇文章的id去获得一篇文章的所有信息
   *
   * @param id 文章的id
   * @return 所有信息
   */
  @JSONP(queryParam = R.JSONP_CALLBACK)
  @GET
  @Path("/one")
  public MyNoticeEntity one(@QueryParam("id") int id) {
    return ManageNotice.get(id);
  }
}
