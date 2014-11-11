/**
 * Author: WuHaoLin
 * Date: 2014/5/7
 * Time: 22:16
 */

package play.vote;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/7
 * Time: 22:16
 */
@Entity
@Table(name = "myvote", schema = "", catalog = "weixin")
public class MyVoteEntity {
  private String xh;

  @Id
  @Column(name = "XH", nullable = false, insertable = true, updatable = true, length = 10)
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

    MyVoteEntity that = (MyVoteEntity) o;

    return !(xh != null ? !xh.equals(that.xh) : that.xh != null);

  }

  @Override
  public int hashCode() {
    return xh != null ? xh.hashCode() : 0;
  }

  public MyVoteEntity(String xh) {
    this.xh = xh;
  }

  public MyVoteEntity() {
  }
}
