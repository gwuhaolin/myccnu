/**
 * Author: WuHaoLin
 * Date: 2014/5/9
 * Time: 18:29
 */

package play.vote;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/9
 * Time: 18:29
 */
@Entity
@Table(name = "mycampaigner", schema = "", catalog = "weixin")
public class MyCampaignerEntity {
  private int id;
  private String name;
  private String des;
  private String picUrl;
  private Integer voteSum = 0;
  private String other1;
  private String other2;
  private String other3;

  @Id
  @GenericGenerator(name = "idGenerator", strategy = "increment")
  @GeneratedValue(generator = "idGenerator")
  @Column(name = "id", nullable = false, insertable = true, updatable = true)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Basic
  @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 50)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "des", nullable = true, insertable = true, updatable = true, length = 65535)
  public String getDes() {
    return des;
  }

  public void setDes(String des) {
    this.des = des;
  }

  @Basic
  @Column(name = "picURL", nullable = true, insertable = true, updatable = true, length = 100)
  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    if (picUrl == null || picUrl.length() < 10) {
      picUrl = ManageVote.voteBasicInfo.DefaultPicUrl;
    }
    this.picUrl = picUrl;
  }

  @Basic
  @Column(name = "voteSum", nullable = true, insertable = true, updatable = true)
  public Integer getVoteSum() {
    return voteSum;
  }

  public void setVoteSum(Integer voteSum) {
    this.voteSum = voteSum;
  }

  @Basic
  @Column(name = "other1", nullable = true, insertable = true, updatable = true, length = 50)
  public String getOther1() {
    return other1;
  }

  public void setOther1(String other1) {
    this.other1 = other1;
  }

  @Basic
  @Column(name = "other2", nullable = true, insertable = true, updatable = true, length = 100)
  public String getOther2() {
    return other2;
  }

  public void setOther2(String other2) {
    this.other2 = other2;
  }

  @Basic
  @Column(name = "other3", nullable = true, insertable = true, updatable = true, length = 65535)
  public String getOther3() {
    return other3;
  }

  public void setOther3(String other3) {
    this.other3 = other3;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    MyCampaignerEntity that = (MyCampaignerEntity) o;

    if (id != that.id) return false;
    if (des != null ? !des.equals(that.des) : that.des != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (other1 != null ? !other1.equals(that.other1) : that.other1 != null) return false;
    if (other2 != null ? !other2.equals(that.other2) : that.other2 != null) return false;
    if (other3 != null ? !other3.equals(that.other3) : that.other3 != null) return false;
    if (picUrl != null ? !picUrl.equals(that.picUrl) : that.picUrl != null) return false;
    return !(voteSum != null ? !voteSum.equals(that.voteSum) : that.voteSum != null);

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (des != null ? des.hashCode() : 0);
    result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
    result = 31 * result + (voteSum != null ? voteSum.hashCode() : 0);
    result = 31 * result + (other1 != null ? other1.hashCode() : 0);
    result = 31 * result + (other2 != null ? other2.hashCode() : 0);
    result = 31 * result + (other3 != null ? other3.hashCode() : 0);
    return result;
  }
}
