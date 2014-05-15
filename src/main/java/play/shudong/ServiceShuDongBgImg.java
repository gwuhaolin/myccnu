/**
 * Author: WuHaoLin
 * Date: 2014/5/10
 * Time: 14:23
 */

package play.shudong;

import org.glassfish.jersey.server.JSONP;
import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.R;
import tool.ServiceQiNiu;

import javax.ws.rs.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/10
 * Time: 14:23
 */
@Path("/shudongBgImg")
@Produces({"application/javascript"})
public class ServiceShuDongBgImg {

	/**
	 * 如果没有id参数就返回最新的一个
	 *
	 * @param id
	 * @return
	 */
	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getOne")
	public MyShuDongBgImgEntity getOneById(@QueryParam("id") int id) {
		return ManageShuDongBgImg.get(id);
	}


	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getNext")
	public MyShuDongBgImgEntity getNext(
			@QueryParam("id") int id
	) {
		return ManageShuDongBgImg.getNext(id);
	}


	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/getPrev")
	public MyShuDongBgImgEntity getPrev(
			@QueryParam("id") int id
	) {
		return ManageShuDongBgImg.getPrev(id);
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/addOne")
	public boolean addOne(
			@QueryParam("picUrl") String picUrl,
			@DefaultValue("") @QueryParam("author") String author,
			@DefaultValue("") @QueryParam("des") String des,
			@CookieParam("XH") String XH
	) {
		if (picUrl == null || picUrl.length() < 10) {
			return false;
		}
		MyShuDongBgImgEntity one = new MyShuDongBgImgEntity();
		one.setPicUrl(picUrl);
		one.setAuthor(author);
		one.setDes(des);
		one.setXh(XH);
		ManageShuDongBgImg.add(one);
		return true;
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/deleteOne")
	public boolean deleteOne(@QueryParam("id") int id) {
		if(id<0){
			return false;
		}
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyShuDongBgImgEntity where id=?");
		query.setInteger(0, id);
		MyShuDongBgImgEntity myShuDongEntity = (MyShuDongBgImgEntity) query.uniqueResult();
		session.delete(myShuDongEntity);
		ServiceQiNiu.removeOne(ServiceQiNiu.ShuDong_QINIU_BUCKET, myShuDongEntity.getPicUrl());
		HibernateUtil.closeSession(session);
		return true;
	}

	@JSONP(queryParam = R.JSONP_CALLBACK)
	@GET
	@Path("/likeIt")
	public int likeIt(@QueryParam("id") int id) {
		return ManageShuDongBgImg.likeThis(id);
	}
}
