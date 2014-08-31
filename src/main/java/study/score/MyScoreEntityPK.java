package study.score;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by wuhaolin on 8/30/14.
 * :
 */
public class MyScoreEntityPK implements Serializable {
  private String xh;
  private String classNo;

  @Column(name = "XH", nullable = false, insertable = true, updatable = true, length = 15)
  @Id
  public String getXh() {
    return xh;
  }

  public void setXh(String xh) {
    this.xh = xh;
  }

  @Column(name = "classNo", nullable = false, insertable = true, updatable = true, length = 15)
  @Id
  public String getClassNo() {
    return classNo;
  }

  public void setClassNo(String classNo) {
    this.classNo = classNo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MyScoreEntityPK that = (MyScoreEntityPK) o;

    if (classNo != null ? !classNo.equals(that.classNo) : that.classNo != null) return false;
    if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = xh != null ? xh.hashCode() : 0;
    result = 31 * result + (classNo != null ? classNo.hashCode() : 0);
    return result;
  }
}
