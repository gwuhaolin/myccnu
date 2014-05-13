/**
 * Author: WuHaoLin
 * Date: 2014/5/13
 * Time: 10:48
 */

package play.shop;

import org.glassfish.jersey.server.JSONP;
import tool.R;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * RESTful接口,数据提供层
 */
@Path("/shop")
@Produces({"application/javascript"})
public class ServiceShop {

	/**
	 * HttpServletRequest请求
	 */
	@Context
	HttpServletRequest request;

	/**
	 * 添加一个物品
	 *
	 * @param name       物品发名称
	 * @param price      价格
	 * @param des        描述
	 * @param picUrl     展示图片URL
	 * @param tag        标签
	 * @param ownerName  所有者姓名
	 * @param ownerPhone 联系电话
	 * @param ownerXH    所有者学号
	 * @param ownerQQ    所有者QQ
	 * @return 自动加上Id和当前系统发布时间
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/add")
	public ShopItemsEntity addOne(@QueryParam("name") String name,
	                              @QueryParam("price") float price,
	                              @QueryParam("des") String des,
	                              @QueryParam("picUrl") String picUrl,
	                              @QueryParam("tag") int tag,
	                              @QueryParam("ownerName") String ownerName,
	                              @QueryParam("ownerPhone") String ownerPhone,
	                              @QueryParam("ownerXH") String ownerXH,
	                              @CookieParam("ownerQQ") String ownerQQ
	) {
		ShopItemsEntity one = new ShopItemsEntity();
		one.setName(name);
		one.setPrice(price);
		one.setDes(des);
		one.setPicUrl(picUrl);
		one.setTag(tag);
		one.setOwnerName(ownerName);
		one.setOwnerPhone(ownerPhone);
		one.setOwnerQq(ownerQQ);
		one.setOwnerXh(ownerXH);
		return ManageShop.add(one);
	}

	/**
	 * 修改一个物品的信息
	 *
	 * @param id
	 * @param name
	 * @param price
	 * @param des
	 * @param picUrl
	 * @param tag
	 * @param ownerName
	 * @param ownerPhone
	 * @param ownerXH
	 * @param ownerQQ
	 * @return 成功与否
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/update")
	public boolean update(
			@QueryParam("id") int id,
			@QueryParam("name") String name,
			@QueryParam("price") float price,
			@QueryParam("des") String des,
			@QueryParam("picUrl") String picUrl,
			@QueryParam("tag") int tag,
			@QueryParam("ownerName") String ownerName,
			@QueryParam("ownerPhone") String ownerPhone,
			@QueryParam("ownerXH") String ownerXH,
			@CookieParam("ownerQQ") String ownerQQ) {
		ShopItemsEntity one = new ShopItemsEntity();
		one.setId(id);
		one.setName(name);
		one.setPrice(price);
		one.setDes(des);
		one.setPicUrl(picUrl);
		one.setTag(tag);
		one.setOwnerName(ownerName);
		one.setOwnerPhone(ownerPhone);
		one.setOwnerQq(ownerQQ);
		one.setOwnerXh(ownerXH);
		return ManageShop.update(one);
	}

	/**
	 * 删除一个物品
	 *
	 * @param id 物品的Id
	 * @return 成功与否
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/remove")
	public boolean remove(@QueryParam("id") int id) {
		return ManageShop.remove(id);
	}

	/**
	 * 获得一个物品的所有信息
	 *
	 * @param id 物品的Id
	 * @return
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getOne")
	public ShopItemsEntity getOne(@QueryParam("id") int id) {
		return ManageShop.get(id);
	}

	/**
	 * 获得多条物品的信息(分页浏览)
	 *
	 * @param from 第一条从哪里开始
	 * @param cmd  按照什么方式来排序
	 *             =new 按照发布时间
	 *             =hot 按照受关注程度
	 *             =my 按照查询者的学号返回该学号所发的所有
	 * @param XH   查询者的学号
	 * @return 5条物品的信息
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getPage")
	public List<ShopItemsEntity> getPage(@QueryParam("from") int from,
	                                     @QueryParam("cmd") String cmd,
	                                     @CookieParam("XH") String XH
	) {
		List<ShopItemsEntity> re = null;
		if (cmd.equals("new")) {
			re = ManageShop.get_Page(from);
		} else if (cmd.equals("hot")) {
			re = ManageShop.get_page_hot(from);
		} else if (cmd.equals("my")) {
			re = ManageShop.get_page_XH(from, XH);
		} else if (cmd.equals("tag")) {
			int tag=Integer.parseInt(request.getParameter("tag"));
			re = ManageShop.get_Page_tag(from, tag);
		}
		return re;
	}

	/**
	 * 关键字搜索
	 *
	 * @param from 第一条从哪里开始
	 * @param want 关键字
	 * @return
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/search")
	public List<ShopItemsEntity> search(@QueryParam("from") int from,
	                                    @QueryParam("want") String want) {
		return ManageShop.search_page(from, want);
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/likeIt")
	public int likeIt(@QueryParam("id") int id) {
		return ManageShop.likeIt(id);
	}

}
