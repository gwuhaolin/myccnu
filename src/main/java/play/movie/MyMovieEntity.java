package play.movie;


import tool.Tool;
import org.hibernate.annotations.*;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/12/14
 * Time: 10:22 PM
 */
@Entity
//@Table(name = "mymovie", schema = "", catalog = "ccnu")
public class MyMovieEntity {
	private int id;
	private String name;
	private String picUrl;
	private String des;
	private String pay;
	private String other;
	private String date;
	private String target;

	public MyMovieEntity(String name, String picUrl, String des, String pay, String other, String date, String target) {
		this.name = name;
		this.picUrl = picUrl;
		this.des = des;
		this.pay = pay;
		this.other = other;
		this.date = date;
		this.target = target;
	}

	public MyMovieEntity() {
	}

	@Id
	@Column(name = "Id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "Name", nullable = false, insertable = true, updatable = true, length = 55, precision = 0)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "PicUrl", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	@Basic
	@Column(name = "Des", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	@Basic
	@Column(name = "Pay", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Basic
	@Column(name = "Other", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Basic
	@Column(name = "date", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Basic
	@Column(name = "Target", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MyMovieEntity that = (MyMovieEntity) o;

		if (id != that.id) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		if (des != null ? !des.equals(that.des) : that.des != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (other != null ? !other.equals(that.other) : that.other != null) return false;
		if (pay != null ? !pay.equals(that.pay) : that.pay != null) return false;
		if (picUrl != null ? !picUrl.equals(that.picUrl) : that.picUrl != null) return false;
		if (target != null ? !target.equals(that.target) : that.target != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
		result = 31 * result + (des != null ? des.hashCode() : 0);
		result = 31 * result + (pay != null ? pay.hashCode() : 0);
		result = 31 * result + (other != null ? other.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (target != null ? target.hashCode() : 0);
		return result;
	}

	/**
	 * 判断是否是今天的消息
	 * @return
	 */
	public boolean isTaday(){
		return this.date.split(" ",2)[0].equals(Tool.time_YYYY_MM_DD());
	}
}
