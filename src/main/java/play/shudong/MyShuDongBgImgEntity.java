/**
 * Author: WuHaoLin
 * Date: 2014/5/10
 * Time: 15:06
 */

package play.shudong;

import tool.Tool;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/10
 * Time: 15:06
 */
@Entity
@Table(name = "myshudongbgimg", schema = "", catalog = "weixin")
public class MyShuDongBgImgEntity {
	private int id;
	private String picUrl;
	private String author="";
	private String des="";
	private String xh="";
	private String date= Tool.time_YYYY_MM_DD_HH_MM_NOW();
	private Integer likeCount=0;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "picUrl", nullable = false, insertable = true, updatable = true, length = 100)
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Basic
	@Column(name = "author", nullable = true, insertable = true, updatable = true, length = 30)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
	@Column(name = "XH", nullable = true, insertable = true, updatable = true, length = 15)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Basic
	@Column(name = "date", nullable = true, insertable = true, updatable = true, length = 25)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Basic
	@Column(name = "likeCount", nullable = true, insertable = true, updatable = true)
	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MyShuDongBgImgEntity that = (MyShuDongBgImgEntity) o;

		if (id != that.id) return false;
		if (author != null ? !author.equals(that.author) : that.author != null) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		if (des != null ? !des.equals(that.des) : that.des != null) return false;
		if (likeCount != null ? !likeCount.equals(that.likeCount) : that.likeCount != null) return false;
		if (picUrl != null ? !picUrl.equals(that.picUrl) : that.picUrl != null) return false;
		if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
		result = 31 * result + (author != null ? author.hashCode() : 0);
		result = 31 * result + (des != null ? des.hashCode() : 0);
		result = 31 * result + (xh != null ? xh.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (likeCount != null ? likeCount.hashCode() : 0);
		return result;
	}
}
