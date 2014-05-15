/**
 * Author: WuHaoLin
 * Date: 2014/5/15
 * Time: 13:40
 */

package play.shop;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/15
 * Time: 13:40
 */
@Entity
@Table(name = "shoptags", schema = "", catalog = "weixin")
public class ShopTagsEntity implements Comparable<ShopTagsEntity>{
	private int id;
	private String name;
	private Integer sum;

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
	@Column(name = "sum", nullable = true, insertable = true, updatable = true)
	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ShopTagsEntity that = (ShopTagsEntity) o;

		if (id != that.id) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (sum != null ? !sum.equals(that.sum) : that.sum != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (sum != null ? sum.hashCode() : 0);
		return result;
	}

	@Override
	public int compareTo(ShopTagsEntity o) {
		return this.getSum()-o.getSum();
	}
}
