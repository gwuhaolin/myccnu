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
	 * 后台管理密码
	 */
	final String ManagePassword = "SHOP";

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
	@Path("/addOne")
	public int addOne(@QueryParam("name") String name,
	                  @QueryParam("price") float price,
	                  @QueryParam("des") String des,
	                  @QueryParam("picUrl") String picUrl,
	                  @CookieParam("XH") String XH
	) {
		if (name == null || price == 0) {
			return -1;//还有信息没有填
		} else if (XH == null) {
			return -2;//没有绑定
		}
		if (picUrl == null) {
			picUrl = ManageShopItem.DEFAULT_PIC_URL;
		}
		try {
			ShopItemsEntity one = new ShopItemsEntity();
			one.setName(name);
			one.setPrice(price);
			one.setDes(des);
			one.setPicUrl(picUrl);
			one.setXh(XH);
			ManageShopItem.add(one);
			return one.getId();//成功,返回获得的Id
		} catch (Exception e) {
			return -3;//发生异常
		}
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
			@CookieParam("XH") String XH) {
		if (name == null || price == 0) {
			return false;
		}
		if (picUrl == null) {
			picUrl = ManageShopItem.DEFAULT_PIC_URL;
		}
		ShopItemsEntity one = new ShopItemsEntity();
		one.setId(id);
		one.setName(name);
		one.setPrice(price);
		one.setDes(des);
		one.setPicUrl(picUrl);
		one.setXh(XH);
		return ManageShopItem.update(one);
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
		return ManageShopItem.remove(id);
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
		return ManageShopItem.get(id);
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
			re = ManageShopItem.get_Page(from);
		} else if (cmd.equals("hot")) {
			re = ManageShopItem.get_page_hot(from);
		} else if (cmd.equals("my")) {
			re = ManageShopItem.get_page_XH(from, XH);
		} else if (cmd.equals("search")) {
			String want = request.getParameter("want");
			re = ManageShopItem.search_page(from, want);
		}else if (cmd.equals("xh")){
			re=ManageShopItem.get_page_XH(from,request.getParameter("xh"));
		}
		return re;
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/likeIt")
	public int likeIt(@QueryParam("id") int id) {
		return ManageShopItem.likeIt(id);
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getSearchTag")
	public List<ShopSearchTagEntity> getSearchTag() {
		return ManageShopItem.getSearchTag();
	}

}
