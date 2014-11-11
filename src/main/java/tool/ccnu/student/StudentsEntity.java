package tool.ccnu.student;


import tool.ccnu.academy.AcademyEntity;

import javax.persistence.*;

/**
 * Created by wuhaolin on 7/6/14.
 * :以这个为信息中心
 */
@Entity
@Table(name = "students", schema = "", catalog = "weixin")
public class StudentsEntity {
  private String xh;
  private String name;
  private Integer sex;
  private String password;
  private String phoneNumber;
  private String qq;
  private String dormitory;
  private String idNumber;
  private AcademyEntity academyByAcademy;

  @Id
  @Column(name = "xh", nullable = false, insertable = true, updatable = true, length = 10)
  public String getXh() {
    return xh;
  }

  public void setXh(String xh) {
    this.xh = xh;
  }

  @Basic
  @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 15)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Basic
  @Column(name = "sex", nullable = true, insertable = true, updatable = true)
  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  @Basic
  @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 20)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Basic
  @Column(name = "phoneNumber", nullable = true, insertable = true, updatable = true, length = 20)
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Basic
  @Column(name = "QQ", nullable = true, insertable = true, updatable = true, length = 20)
  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  @Basic
  @Column(name = "dormitory", nullable = true, insertable = true, updatable = true, length = 20)
  public String getDormitory() {
    return dormitory;
  }

  public void setDormitory(String dormitory) {
    this.dormitory = dormitory;
  }

  @Basic
  @Column(name = "idNumber", nullable = true, insertable = true, updatable = true, length = 20)
  public String getIdNumber() {
    return idNumber;
  }

  public void setIdNumber(String idNumber) {
    this.idNumber = idNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    StudentsEntity that = (StudentsEntity) o;

    if (dormitory != null ? !dormitory.equals(that.dormitory) : that.dormitory != null) return false;
    if (idNumber != null ? !idNumber.equals(that.idNumber) : that.idNumber != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (password != null ? !password.equals(that.password) : that.password != null) return false;
    if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
    if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
    if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
    if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = xh != null ? xh.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (sex != null ? sex.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
    result = 31 * result + (qq != null ? qq.hashCode() : 0);
    result = 31 * result + (dormitory != null ? dormitory.hashCode() : 0);
    result = 31 * result + (idNumber != null ? idNumber.hashCode() : 0);
    return result;
  }

  @ManyToOne
  @JoinColumn(name = "academy", referencedColumnName = "id")
  public AcademyEntity getAcademyByAcademy() {
    return academyByAcademy;
  }

  public void setAcademyByAcademy(AcademyEntity academyByAcademy) {
    this.academyByAcademy = academyByAcademy;
  }

  public StudentsEntity() {
  }

  public StudentsEntity(String xh, String name, Integer sex, String password, String phoneNumber, String qq, String dormitory, String idNumber, AcademyEntity academyByAcademy) {
    this.xh = xh;
    this.name = name;
    this.sex = sex;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.qq = qq;
    this.dormitory = dormitory;
    this.idNumber = idNumber;
    this.academyByAcademy = academyByAcademy;
  }

}
