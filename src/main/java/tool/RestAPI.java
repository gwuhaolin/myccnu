package tool; /**
 * Author: WuHaoLin
 * Date: 2014/5/6
 * Time: 22:34
 */

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import play.shop.ServiceShop;
import play.shudong.ServiceQiNiu;
import play.shudong.ServiceShuDong;
import play.shudong.ServiceShuDongBgImg;
import play.vote.ServiceVote;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/6
 * Time: 22:34
 */
public class RestAPI extends ResourceConfig {

	public RestAPI(){

		//加载Resource
		register(ServiceShuDong.class);
		register(ServiceShuDongBgImg.class);
		register(ServiceVote.class);
		register(ServiceQiNiu.class);
		register(ServiceShop.class);

		//注册数据转换器
		register(JacksonJsonProvider.class);

		// Logging.
		register(LoggingFilter.class);

	}
}
