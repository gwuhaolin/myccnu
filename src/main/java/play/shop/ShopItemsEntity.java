/**
 * Author: WuHaoLin
 * Date: 2014/5/13
 * Time: 11:01
 */

package play.shop;

import tool.Tool;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/13
 * Time: 11:01
 */

/**
 * javaBean 代表一个物品
 */
@Entity
@Table(name = "shopitems", schema = "", catalog = "weixin")
public class ShopItemsEntity {
	private int id;
	private String name;
	private Float price;
	private String des;
	private String picUrl;
	private String date= Tool.time_YYYY_MM_DD_HH_MM_NOW();
	private Integer tag=0;
	private String ownerName;
	private String ownerXh;
	private String ownerPhone;
	private String ownerQq;
	private Integer seeCount=0;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "price", nullable = true, insertable = true, updatable = true, precision = 0)
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Basic
	@Column(name = "des", nullable = true, insertable = true, updatable = true, length = 65535)
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Basic
	@Column(name = "picURL", nullable = true, insertable = true, updatable = true, length = 255)
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Basic
	@Column(name = "date", nullable = true, insertable = true, updatable = true, length = 255)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Basic
	@Column(name = "tag", nullable = true, insertable = true, updatable = true)
	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	@Basic
	@Column(name = "ownerName", nullable = true, insertable = true, updatable = true, length = 255)
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Basic
	@Column(name = "ownerXH", nullable = true, insertable = true, updatable = true, length = 255)
	public String getOwnerXh() {
		return ownerXh;
	}

	public void setOwnerXh(String ownerXh) {
		this.ownerXh = ownerXh;
	}

	@Basic
	@Column(name = "ownerPhone", nullable = true, insertable = true, updatable = true, length = 255)
	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	@Basic
	@Column(name = "ownerQQ", nullable = true, insertable = true, updatable = true, length = 255)
	public String getOwnerQq() {
		return ownerQq;
	}

	public void setOwnerQq(String ownerQq) {
		this.ownerQq = ownerQq;
	}

	@Basic
	@Column(name = "seeCount", nullable = true, insertable = true, updatable = true)
	public Integer getSeeCount() {
		return seeCount;
	}

	public void setSeeCount(Integer seeCount) {
		this.seeCount = seeCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ShopItemsEntity that = (ShopItemsEntity) o;

		if (id != that.id) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		if (des != null ? !des.equals(that.des) : that.des != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (ownerName != null ? !ownerName.equals(that.ownerName) : that.ownerName != null) return false;
		if (ownerPhone != null ? !ownerPhone.equals(that.ownerPhone) : that.ownerPhone != null) return false;
		if (ownerQq != null ? !ownerQq.equals(that.ownerQq) : that.ownerQq != null) return false;
		if (ownerXh != null ? !ownerXh.equals(that.ownerXh) : that.ownerXh != null) return false;
		if (picUrl != null ? !picUrl.equals(that.picUrl) : that.picUrl != null) return false;
		if (price != null ? !price.equals(that.price) : that.price != null) return false;
		if (seeCount != null ? !seeCount.equals(that.seeCount) : that.seeCount != null) return false;
		if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (des != null ? des.hashCode() : 0);
		result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (tag != null ? tag.hashCode() : 0);
		result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
		result = 31 * result + (ownerXh != null ? ownerXh.hashCode() : 0);
		result = 31 * result + (ownerPhone != null ? ownerPhone.hashCode() : 0);
		result = 31 * result + (ownerQq != null ? ownerQq.hashCode() : 0);
		result = 31 * result + (seeCount != null ? seeCount.hashCode() : 0);
		return result;
	}
}
