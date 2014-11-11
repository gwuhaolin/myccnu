package study.CET;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by wuhaolin on 7/7/14.
 * :
 */
public class Cet46EntityPK implements Serializable {
  private String grade;
  private String xh;
  private String date;

  @Column(name = "grade", nullable = false, insertable = true, updatable = true, length = 10)
  @Id
  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  @Column(name = "xh", nullable = false, insertable = true, updatable = true, length = 15)
  @Id
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

    Cet46EntityPK that = (Cet46EntityPK) o;

    if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
    if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = grade != null ? grade.hashCode() : 0;
    result = 31 * result + (xh != null ? xh.hashCode() : 0);
    return result;
  }

  @Column(name = "date", nullable = false, insertable = true, updatable = true, length = 255)
  @Basic
  @Id
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
