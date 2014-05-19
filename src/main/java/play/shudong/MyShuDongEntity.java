package play.shudong;

import tool.Tool;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 3/7/14
 * Time: 1:58 PM
 */
@Entity
@Table(name = "myshudong", schema = "", catalog = "weixin")
public class MyShuDongEntity {
	private int id;
	private int seeCount=0;
	private String content;
	private String date=Tool.time_YYYY_MM_DD_HH_MM_NOW();
	private String xh;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "seeCount", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	public int getSeeCount() {
		return seeCount;
	}

	public void setSeeCount(int seeCount) {
		this.seeCount = seeCount;
	}

	@Basic
	@Column(name = "content", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Basic
	@Column(name = "date", nullable = true, insertable = true, updatable = true, length = 25, precision = 0)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Basic
	@Column(name = "XH", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MyShuDongEntity that = (MyShuDongEntity) o;

		if (id != that.id) return false;
		if (seeCount != that.seeCount) return false;
		if (content != null ? !content.equals(that.content) : that.content != null) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		return !(xh != null ? !xh.equals(that.xh) : that.xh != null);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + seeCount;
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (xh != null ? xh.hashCode() : 0);
		return result;
	}

	public MyShuDongEntity(String content, String xh) {
		this.content = content;
		this.date = Tool.time_YYYY_MM_DD_HH_MM_NOW();
		this.xh = xh;
	}

	public MyShuDongEntity() {
	}
}
