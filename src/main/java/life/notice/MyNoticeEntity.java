package life.notice;

import tool.Tool;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 3/7/14
 * Time: 1:28 PM
 */
@Entity
@Table(name = "mynotice", schema = "", catalog = "weixin")
public class MyNoticeEntity {
	private int id;
	private String title;
	private String date;
	private String orgUrl;
	private String content;
	private String fromSite;
	private Integer isOk=ManageNotice.IsOK_YES;//用于标准人工筛选后的信息删除

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "title", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Basic
	@Column(name = "date", nullable = true, insertable = true, updatable = true, length = 15, precision = 0)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Basic
	@Column(name = "orgUrl", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getOrgUrl() {
		return orgUrl;
	}

	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
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
	@Column(name = "fromSite", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
	public String getFromSite() {
		return fromSite;
	}

	public void setFromSite(String fromSite) {
		this.fromSite = fromSite;
	}

	@Basic
	@Column(name = "isOK", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
	public Integer getIsOk() {
		return isOk;
	}

	public void setIsOk(Integer isOk) {
		this.isOk = isOk;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MyNoticeEntity that = (MyNoticeEntity) o;

		if (id != that.id) return false;
		if (content != null ? !content.equals(that.content) : that.content != null) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		if (fromSite != null ? !fromSite.equals(that.fromSite) : that.fromSite != null) return false;
		if (isOk != null ? !isOk.equals(that.isOk) : that.isOk != null) return false;
		if (orgUrl != null ? !orgUrl.equals(that.orgUrl) : that.orgUrl != null) return false;
		return !(title != null ? !title.equals(that.title) : that.title != null);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (orgUrl != null ? orgUrl.hashCode() : 0);
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + (fromSite != null ? fromSite.hashCode() : 0);
		result = 31 * result + (isOk != null ? isOk.hashCode() : 0);
		return result;
	}


	public MyNoticeEntity(String title, String date, String orgUrl, String content, String fromSite) {
		this.title = title;
		this.date = date;
		this.orgUrl = orgUrl;
		this.content = content;
		this.fromSite = fromSite;
	}

	public MyNoticeEntity() {
	}

	/**
	 * 显示文章的标题和发布时间
	 * @return
	 */
	public String toString(){
		return this.title;
	}

	public String baseUrl(){
		String ss[]= this.orgUrl.split("/");
		return ss[0]+"//"+ss[2];
	}

	/**
	 * 判断是否是今天的消息
	 * @return
	 */
	public boolean taday(){
		return this.date.equals(Tool.time_YYYY_MM_DD());
	}
}
