package tool.studentInfo;

import tool.CCNUPortal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
* Created with Intellij IDEA.
* User: WuHaoLin
* Date: 2/1/14
* Time: 6:26 PM
*/
@Entity
@Table(name = "main", schema = "", catalog = "ccnu")
public class MainEntity{
	public static final HashMap<String, String> allkey = new HashMap<String, String>();
	static {
		allkey.put("学号", "StudentId");
		allkey.put("密码", "Password");
		allkey.put("姓名","Name");
		allkey.put("曾用名", "FormerName");
		allkey.put("身份证号", "IdNumber");
		allkey.put("学院", "Academy");
		allkey.put("专业", "Major");
		allkey.put("民族", "Nation");
		allkey.put("籍贯", "NativePlace");
		allkey.put("毕业学校", "HighSchool");
		allkey.put("现家庭住址", "FamilyAddress");
		allkey.put("家庭电话", "FamilyPhoneNumber");
		allkey.put("宿舍地址", "DormAddress");
		allkey.put("身高", "Height");
		allkey.put("体重", "Weight");
		allkey.put("手机号", "PhoneNumber");
	}
	private String openId;
	private String weight;
	private String highSchool;
	private String highSchoolPerformance;
	private String dormAddress;
	private String nativePlace;
	private String IdNumber;
	private String password;
	private String nation;
	private String familyAddress;
	private String major;
	private String studentId;
	private Byte sex;
	private String familyPhoneNumber;
	private String phoneNumber;
	private String academy;
	private String height;
	private String formerName;
	private String name;
	private String educationInfo;
	private String familyInfo;
	private String birthday;

	/**
	 * 用告诉的这三个参数去抓取网页,然后构造一个
	 * @param XH 学号
	 * @param MM 密码
	 * @param openId 微信的
	 * @return
	 * @throws java.io.IOException
	 */
	public static MainEntity make(String XH,String MM,String openId) throws Exception {
		Document document= CCNUPortal.getStudentInfo(XH,MM);
		MainEntity re=parse(document);
		re.setOpenId(openId);
		return re;
	}

	/**
	 * 通过openID去数据库里查询该同学的信息
	 * @param openId
	 * @return
	 */
	public static MainEntity get(String openId){
		return null;//TODO
	}

	public static MainEntity parse(String html) throws Exception {
		Document document = Jsoup.parse(html);
		return parse(document);
	}

	public static MainEntity parse(File file, String charset) throws Exception {
		Document document = Jsoup.parse(file, charset);
		return parse(document);
	}

	private static MainEntity parse(Document document){
		MainEntity reOne = new MainEntity();
		Class clazz = MainEntity.class;
		try {
			//基本信息
			Elements tds = document.getElementById("tdId_baseinfo").select("table").first().select("td");
			for (int i = 0; i < tds.size(); i++) {
				Element td = tds.get(i);
				String name = td.text();
				if (name.contains("：")) {
					String mName;
					if (!name.contains("(")) {
						mName = "set" + allkey.get(name.substring(0, name.length() - 1));
					} else {
						mName = "set" + allkey.get(name.substring(0, name.indexOf('(')));
					}
					try {
						Method method = clazz.getDeclaredMethod(mName, String.class);
						i++;
						String value =trim(tds.get(i).text());
						method.invoke(reOne, value);
					}catch (Exception e){
						continue;
					}
				}
			}

			StringBuilder stringBuilder = new StringBuilder();
			//高中表现
			stringBuilder.delete(0, stringBuilder.length());
			Elements ps= document.getElementById("tdId_baseinfo").getElementsByTag("p");
			for (Element p:ps){
				stringBuilder.append(p.text());
			}
			reOne.setHighSchoolPerformance(stringBuilder.toString());

			//家庭信息
			stringBuilder.delete(0, stringBuilder.length());
			tds = document.getElementById("tdId_family").select("table").first().select("td");
			for (Element td : tds) {
				stringBuilder.append(td.text());
				stringBuilder.append(" ");
			}
			reOne.setFamilyInfo(stringBuilder.toString());

			//学习经历
			stringBuilder.delete(0, stringBuilder.length());
			tds = document.getElementById("tdId_study").select("table").first().select("td");
			for (Element td : tds) {
				stringBuilder.append(td.text());
				stringBuilder.append(" ");
			}
			reOne.setEducationInfo(stringBuilder.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
		return reOne;
	}

	public static void main(String[] args) {
		SessionFactory sessionFactory=new Configuration()
				.configure("hibernate.cfg.xml").buildSessionFactory();
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();

		try {
			MainEntity entity=MainEntity.parse(new File("G:1.html"),"GBK");
			System.out.println(entity);
			session.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}

		transaction.commit();
		session.close();
	}

	@Basic
	@Column(name = "OpenID", nullable = true, insertable = true, updatable = true, length = 64, precision = 0)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Basic
	@Column(name = "Weight", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Basic
	@Column(name = "HighSchool", nullable = true, insertable = true, updatable = true, length = 40, precision = 0)
	public String getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}

	@Basic
	@Column(name = "HighSchoolPerformance", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getHighSchoolPerformance(){
		return this.highSchoolPerformance;
	}

	public void setHighSchoolPerformance(String highSchoolPerformance){
		this.highSchoolPerformance=highSchoolPerformance;
	}

	@Basic
	@Column(name = "DormAddress", nullable = true, insertable = true, updatable = true, length = 40, precision = 0)
	public String getDormAddress() {
		return dormAddress;
	}

	public void setDormAddress(String dormAddress) {
		this.dormAddress = dormAddress;
	}

	@Basic
	@Column(name = "NativePlace", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	@Basic
	@Column(name = "IdNumber", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
	public String getIdNumber() {
		return IdNumber;
	}

	public void setIdNumber(String iDnumber) {
		this.IdNumber = iDnumber;

		//计算出生日和性别
		try {
			if (iDnumber!=null && iDnumber.length()==18){//身份证号为18位
				setBirthday(iDnumber.substring(6,14));
				String sexString=iDnumber.substring(14,17);
				int sexInt= Integer.parseInt(sexString);
				setSex((byte)(sexInt%2));
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Basic
	@Column(name = "Password", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "Nation", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Basic
	@Column(name = "FamilyAddress", nullable = true, insertable = true, updatable = true, length = 50, precision = 0)
	public String getFamilyAddress() {
		return familyAddress;
	}

	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}

	@Basic
	@Column(name = "Major", nullable = true, insertable = true, updatable = true, length = 40, precision = 0)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Id
	@Basic
	@Column(name = "StudentID", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	@Basic
	@Column(name = "Sex", nullable = true, insertable = true, updatable = true, length = 3, precision = 0)
	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	@Basic
	@Column(name = "FamilyPhoneNumber", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
	public String getFamilyPhoneNumber() {
//		if (this.familyPhoneNumber.length()>20){
//			return familyPhoneNumber.substring(0,20);
//		}
		return familyPhoneNumber;
	}

	public void setFamilyPhoneNumber(String familyPhoneNumber) {
		this.familyPhoneNumber = familyPhoneNumber;
	}

	@Basic
	@Column(name = "PhoneNumber", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Basic
	@Column(name = "Academy", nullable = true, insertable = true, updatable = true, length = 30, precision = 0)
	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	@Basic
	@Column(name = "Height", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	@Basic
	@Column(name = "FormerName", nullable = true, insertable = true, updatable = true, length = 15, precision = 0)
	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}

	@Basic
	@Column(name = "Name", nullable = true, insertable = true, updatable = true, length = 15, precision = 0)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "EducationInfo", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getEducationInfo() {
		return educationInfo;
	}

	public void setEducationInfo(String educationInfo) {
		this.educationInfo = educationInfo;
	}

	@Basic
	@Column(name = "FamilyInfo", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getFamilyInfo() {
		return familyInfo;
	}

	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}

	@Basic
	@Column(name = "Birthday", nullable = true, insertable = true, updatable = true, length = 8, precision = 0)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MainEntity that = (MainEntity) o;

		if (academy != null ? !academy.equals(that.academy) : that.academy != null) return false;
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
		if (IdNumber != null ? !IdNumber.equals(that.IdNumber) : that.IdNumber != null) return false;
		if (major != null ? !major.equals(that.major) : that.major != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (nation != null ? !nation.equals(that.nation) : that.nation != null) return false;
		if (nativePlace != null ? !nativePlace.equals(that.nativePlace) : that.nativePlace != null) return false;
		if (openId != null ? !openId.equals(that.openId) : that.openId != null) return false;
		if (password != null ? !password.equals(that.password) : that.password != null) return false;
		if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
		if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
		if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) return false;
		if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = openId != null ? openId.hashCode() : 0;
		result = 31 * result + (weight != null ? weight.hashCode() : 0);
		result = 31 * result + (highSchool != null ? highSchool.hashCode() : 0);
		result = 31 * result + (dormAddress != null ? dormAddress.hashCode() : 0);
		result = 31 * result + (nativePlace != null ? nativePlace.hashCode() : 0);
		result = 31 * result + (IdNumber != null ? IdNumber.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (nation != null ? nation.hashCode() : 0);
		result = 31 * result + (familyAddress != null ? familyAddress.hashCode() : 0);
		result = 31 * result + (major != null ? major.hashCode() : 0);
		result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (familyPhoneNumber != null ? familyPhoneNumber.hashCode() : 0);
		result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
		result = 31 * result + (academy != null ? academy.hashCode() : 0);
		result = 31 * result + (height != null ? height.hashCode() : 0);
		result = 31 * result + (formerName != null ? formerName.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (educationInfo != null ? educationInfo.hashCode() : 0);
		result = 31 * result + (familyInfo != null ? familyInfo.hashCode() : 0);
		return result;
	}

	/**
	 * 清除字符串的头尾的中文空格和英文空格
	 * @param string
	 * @return
	 */
	private static String trim(String string){
		char[] value=string.toCharArray();
		int len = value.length;
		int st = 0;

		while ((st < len) && ((value[st] <= ' ') || value[st]==160 )) {
			st++;
		}
		while ((st < len) && ((value[len - 1] <= ' ') || value[len-1]==160 )) {
			len--;
		}
		return ((st > 0) || (len < value.length)) ? string.substring(st, len) : string;
	}

	public MainEntity(String studentId) {
		this.studentId = studentId;
	}

	public MainEntity() {
	}
}
