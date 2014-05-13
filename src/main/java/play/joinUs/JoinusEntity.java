package play.joinUs;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 3/2/14
 * Time: 8:17 PM
 */
@Entity
@Table(name = "joinus", schema = "", catalog = "weixin")
public class JoinusEntity {
	private int id;
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
	@Column(name = "XH", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
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

		JoinusEntity that = (JoinusEntity) o;

		if (id != that.id) return false;
		if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (xh != null ? xh.hashCode() : 0);
		return result;
	}
}
