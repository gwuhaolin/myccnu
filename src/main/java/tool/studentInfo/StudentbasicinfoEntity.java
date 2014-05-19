/**
 * Author: WuHaoLin
 * Date: 2014/5/15
 * Time: 15:16
 */

package tool.studentInfo;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/15
 * Time: 15:16
 */
@Entity
@Table(name = "studentbasicinfo", schema = "", catalog = "weixin")
public class StudentBasicInfoEntity {
	private String xh;
	private String major;
	private Byte sex;
	private String phoneNumber;
	private String academy;
	private String name;
	private String qq;
	private String dormAddress;

	@Id
	@javax.persistence.Column(name = "XH", nullable = false, insertable = true, updatable = true, length = 20)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Basic
	@Column(name = "Major", nullable = true, insertable = true, updatable = true, length = 40)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Basic
	@Column(name = "Sex", nullable = true, insertable = true, updatable = true)
	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	@Basic
	@Column(name = "PhoneNumber", nullable = true, insertable = true, updatable = true, length = 20)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Basic
	@Column(name = "Academy", nullable = true, insertable = true, updatable = true, length = 30)
	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	@Basic
	@Column(name = "Name", nullable = true, insertable = true, updatable = true, length = 15)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	@Column(name = "DormAddress", nullable = true, insertable = true, updatable = true, length = 40)
	public String getDormAddress() {
		return dormAddress;
	}

	public void setDormAddress(String dormAddress) {
		this.dormAddress = dormAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StudentBasicInfoEntity that = (StudentBasicInfoEntity) o;

		if (academy != null ? !academy.equals(that.academy) : that.academy != null) return false;
		if (dormAddress != null ? !dormAddress.equals(that.dormAddress) : that.dormAddress != null) return false;
		if (major != null ? !major.equals(that.major) : that.major != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
		if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
		if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
		return !(xh != null ? !xh.equals(that.xh) : that.xh != null);

	}

	@Override
	public int hashCode() {
		int result = xh != null ? xh.hashCode() : 0;
		result = 31 * result + (major != null ? major.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
		result = 31 * result + (academy != null ? academy.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (qq != null ? qq.hashCode() : 0);
		result = 31 * result + (dormAddress != null ? dormAddress.hashCode() : 0);
		return result;
	}
}
