package tool.ccnu.academy;

import javax.persistence.*;

/**
 * Created by wuhaolin on 7/6/14.
 * :学校所有的院系
 */
@Entity
@Table(name = "academy", schema = "", catalog = "weixin")
public class AcademyEntity {
	private String academyName;
	private int id;

	@Basic
	@Column(name = "AcademyName", nullable = true, insertable = true, updatable = true, length = 255)
	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AcademyEntity that = (AcademyEntity) o;

		if (id != that.id) return false;
		if (academyName != null ? !academyName.equals(that.academyName) : that.academyName != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = academyName != null ? academyName.hashCode() : 0;
		result = 31 * result + id;
		return result;
	}

}
