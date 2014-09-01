package tool.feedback;

import org.glassfish.jersey.server.JSONP;
import tool.R;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by wuhaolin on 9/1/14.
 * :
 */
@Path("/feedback")
@Produces({"application/javascript"})
public class ServiceFeedback {

  /**
   * 获得多条树洞(分页的方式获得)按照发布时间排序
   *
   * @param begin 起始位置
   * @return 5条树洞
   */
  @JSONP(queryParam = R.JSONP_CALLBACK)
  @GET
  @Path("/getPage")
  public List<MyFeedbackEntity> getSomeByPage(
    @QueryParam("begin") int begin) {
    return ManageFeedback.get_page(begin);
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
  public MyFeedbackEntity addOne(
    @DefaultValue("") @QueryParam("say") String con,
    @CookieParam("XH") String XH
  ) {
    MyFeedbackEntity MyFeedbackEntity = new MyFeedbackEntity(con, XH);
    ManageFeedback.add(MyFeedbackEntity);
    return MyFeedbackEntity;
  }

}
