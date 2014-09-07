package study.score;

import javax.persistence.*;

/**
 * Created by wuhaolin on 8/30/14.
 * :一个同学的平均学分成绩
 */
@Entity
@Table(name = "mypjxfscore", schema = "", catalog = "weixin")
public class MyPjxfScoreEntity {
  private String xh;
  private float score;
  private int academy;

  @Id
  @Column(name = "xh", nullable = false, insertable = true, updatable = true, length = 10)
  public String getXh() {
    return xh;
  }

  public void setXh(String xh) {
    this.xh = xh;
  }

  @Basic
  @Column(name = "score", nullable = false, insertable = true, updatable = true, precision = 0)
  public float getScore() {
    return score;
  }

  public void setScore(float score) {
    this.score = score;
  }

  @Basic
  @Column(name = "academy", nullable = false, insertable = true, updatable = true)
  public int getAcademy() {
    return academy;
  }

  public void setAcademy(int academy) {
    this.academy = academy;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MyPjxfScoreEntity that = (MyPjxfScoreEntity) o;

    if (academy != that.academy) return false;
    if (Float.compare(that.score, score) != 0) return false;
    if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = xh != null ? xh.hashCode() : 0;
    result = 31 * result + (score != +0.0f ? Float.floatToIntBits(score) : 0);
    result = 31 * result + academy;
    return result;
  }
}
