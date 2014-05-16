/**
 * Author: WuHaoLin
 * Date: 2014/5/15
 * Time: 15:16
 */

package tool.studentInfo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/15
 * Time: 15:16
 */
@Entity
@javax.persistence.Table(name = "studentinfo", schema = "", catalog = "weixin")
public class StudentInfoEntity {

	public StudentInfoEntity() {
	}

	private String xh;

	public StudentInfoEntity(String xh) {
		this.xh=xh;
	}

	@Id
	@javax.persistence.Column(name = "XH", nullable = false, insertable = true, updatable = true, length = 20)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	private String openId;

	@Basic
	@javax.persistence.Column(name = "OpenID", nullable = true, insertable = true, updatable = true, length = 64)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	private String weight;

	@Basic
	@javax.persistence.Column(name = "Weight", nullable = true, insertable = true, updatable = true, length = 5)
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	private String highSchool;

	@Basic
	@javax.persistence.Column(name = "HighSchool", nullable = true, insertable = true, updatable = true, length = 40)
	public String getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}

	private String dormAddress;

	@Basic
	@javax.persistence.Column(name = "DormAddress", nullable = true, insertable = true, updatable = true, length = 40)
	public String getDormAddress() {
		return dormAddress;
	}

	public void setDormAddress(String dormAddress) {
		this.dormAddress = dormAddress;
	}

	private String nativePlace;

	@Basic
	@javax.persistence.Column(name = "NativePlace", nullable = true, insertable = true, updatable = true, length = 50)
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	private String idNumber;

	@Basic
	@javax.persistence.Column(name = "IdNumber", nullable = true, insertable = true, updatable = true, length = 20)
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	private String password;

	@Basic
	@javax.persistence.Column(name = "Password", nullable = true, insertable = true, updatable = true, length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String nation;

	@Basic
	@javax.persistence.Column(name = "Nation", nullable = true, insertable = true, updatable = true, length = 50)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	private String familyAddress;

	@Basic
	@javax.persistence.Column(name = "FamilyAddress", nullable = true, insertable = true, updatable = true, length = 50)
	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	private String major;

	@Basic
	@javax.persistence.Column(name = "Major", nullable = true, insertable = true, updatable = true, length = 40)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	private Byte sex;

	@Basic
	@javax.persistence.Column(name = "Sex", nullable = true, insertable = true, updatable = true)
	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	private String familyPhoneNumber;

	@Basic
	@javax.persistence.Column(name = "FamilyPhoneNumber", nullable = true, insertable = true, updatable = true, length = 20)
	public String getFamilyPhoneNumber() {
		return familyPhoneNumber;
	}

	public void setFamilyPhoneNumber(String familyPhoneNumber) {
		this.familyPhoneNumber = familyPhoneNumber;
	}

	private String phoneNumber;

	@Basic
	@javax.persistence.Column(name = "PhoneNumber", nullable = true, insertable = true, updatable = true, length = 20)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private String academy;

	@Basic
	@javax.persistence.Column(name = "Academy", nullable = true, insertable = true, updatable = true, length = 30)
	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	private String height;

	@Basic
	@javax.persistence.Column(name = "Height", nullable = true, insertable = true, updatable = true, length = 5)
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	private String formerName;

	@Basic
	@javax.persistence.Column(name = "FormerName", nullable = true, insertable = true, updatable = true, length = 15)
	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}

	private String name;

	@Basic
	@javax.persistence.Column(name = "Name", nullable = true, insertable = true, updatable = true, length = 15)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String educationInfo;

	@Basic
	@javax.persistence.Column(name = "EducationInfo", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getEducationInfo() {
		return educationInfo;
	}

	public void setEducationInfo(String educationInfo) {
		this.educationInfo = educationInfo;
	}

	private String familyInfo;

	@Basic
	@javax.persistence.Column(name = "FamilyInfo", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getFamilyInfo() {
		return familyInfo;
	}

	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}

	private String highSchoolPerformance;

	@Basic
	@javax.persistence.Column(name = "HighSchoolPerformance", nullable = true, insertable = true, updatable = true, length = 2147483647)
	public String getHighSchoolPerformance() {
		return highSchoolPerformance;
	}

	public void setHighSchoolPerformance(String highSchoolPerformance) {
		this.highSchoolPerformance = highSchoolPerformance;
	}

	private String birthday;

	@Basic
	@javax.persistence.Column(name = "Birthday", nullable = true, insertable = true, updatable = true, length = 8)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	private String qq;

	@Basic
	@javax.persistence.Column(name = "QQ", nullable = true, insertable = true, updatable = true, length = 20)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		StudentInfoEntity that = (StudentInfoEntity) o;

		if (academy != null ? !academy.equals(that.academy) : that.academy != null) return false;
		if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
		if (dormAddress != null ? !dormAddress.equals(that.dormAddress) : that.dormAddress != null) return false;
		if (educationInfo != null ? !educationInfo.equals(that.educationInfo) : that.educationInfo != null)
			return false;
		if (familyAddress != null ? !familyAddress.equals(that.familyAddress) : that.familyAddress != null)
			return false;
		if (familyInfo != null ? !familyInfo.equals(that.familyInfo) : that.familyInfo != null) return false;
		if (familyPhoneNumber != null ? !familyPhoneNumber.equals(that.familyPhoneNumber) : that.familyPhoneNumber != null)
			return false;
		if (formerName != null ? !formerName.equals(that.formerName) : that.formerName != null) return false;
		if (height != null ? !height.equals(that.height) : that.height != null) return false;
		if (highSchool != null ? !highSchool.equals(that.highSchool) : that.highSchool != null) return false;
		if (highSchoolPerformance != null ? !highSchoolPerformance.equals(that.highSchoolPerformance) : that.highSchoolPerformance != null)
			return false;
		if (idNumber != null ? !idNumber.equals(that.idNumber) : that.idNumber != null) return false;
		if (major != null ? !major.equals(that.major) : that.major != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (nation != null ? !nation.equals(that.nation) : that.nation != null) return false;
		if (nativePlace != null ? !nativePlace.equals(that.nativePlace) : that.nativePlace != null) return false;
		if (openId != null ? !openId.equals(that.openId) : that.openId != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
		if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
		if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
		if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
		if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = xh != null ? xh.hashCode() : 0;
		result = 31 * result + (openId != null ? openId.hashCode() : 0);
		result = 31 * result + (weight != null ? weight.hashCode() : 0);
		result = 31 * result + (highSchool != null ? highSchool.hashCode() : 0);
		result = 31 * result + (dormAddress != null ? dormAddress.hashCode() : 0);
		result = 31 * result + (nativePlace != null ? nativePlace.hashCode() : 0);
		result = 31 * result + (idNumber != null ? idNumber.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (nation != null ? nation.hashCode() : 0);
		result = 31 * result + (familyAddress != null ? familyAddress.hashCode() : 0);
		result = 31 * result + (major != null ? major.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (familyPhoneNumber != null ? familyPhoneNumber.hashCode() : 0);
		result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
		result = 31 * result + (academy != null ? academy.hashCode() : 0);
		result = 31 * result + (height != null ? height.hashCode() : 0);
		result = 31 * result + (formerName != null ? formerName.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (educationInfo != null ? educationInfo.hashCode() : 0);
		result = 31 * result + (familyInfo != null ? familyInfo.hashCode() : 0);
		result = 31 * result + (highSchoolPerformance != null ? highSchoolPerformance.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (qq != null ? qq.hashCode() : 0);
		return result;
	}
}
