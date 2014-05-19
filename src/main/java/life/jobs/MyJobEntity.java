package life.jobs;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/25/14
 * Time: 6:36 PM
 */
@Entity
@Table(name = "myjob", schema = "", catalog = "weixin")
public class MyJobEntity {
	private int id;
	private String name;
	private String jobInfo;//工作内容
	private String money;
	private String jobTime;
	private String jobLocation;
	private String otherInfo;
	private String manager;
	private Integer target;//兼职还是家教	public static final int TARGET_PartTimeJob=1; public static final int TARGET_PrivateTeacher=2;

	@Id
	@Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Basic
	@Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Basic
	@Column(name = "jobInfo", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getJobInfo() {
		return jobInfo;
	}

	public void setJobInfo(String jobInfo) {
		this.jobInfo = jobInfo;
	}

	@Basic
	@Column(name = "money", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Basic
	@Column(name = "jobTime", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getJobTime() {
		return jobTime;
	}

	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}

	@Basic
	@Column(name = "jobLocation", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	@Basic
	@Column(name = "otherInfo", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	@Basic
	@Column(name = "manager", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Basic
	@Column(name = "target", nullable = true, insertable = true, updatable = true, length = 10, precision = 0)
	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MyJobEntity that = (MyJobEntity) o;

		if (id != that.id) return false;
		if (jobInfo != null ? !jobInfo.equals(that.jobInfo) : that.jobInfo != null) return false;
		if (jobLocation != null ? !jobLocation.equals(that.jobLocation) : that.jobLocation != null) return false;
		if (jobTime != null ? !jobTime.equals(that.jobTime) : that.jobTime != null) return false;
		if (manager != null ? !manager.equals(that.manager) : that.manager != null) return false;
		if (money != null ? !money.equals(that.money) : that.money != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (otherInfo != null ? !otherInfo.equals(that.otherInfo) : that.otherInfo != null) return false;
		return !(target != null ? !target.equals(that.target) : that.target != null);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (jobInfo != null ? jobInfo.hashCode() : 0);
		result = 31 * result + (money != null ? money.hashCode() : 0);
		result = 31 * result + (jobTime != null ? jobTime.hashCode() : 0);
		result = 31 * result + (jobLocation != null ? jobLocation.hashCode() : 0);
		result = 31 * result + (otherInfo != null ? otherInfo.hashCode() : 0);
		result = 31 * result + (manager != null ? manager.hashCode() : 0);
		result = 31 * result + (target != null ? target.hashCode() : 0);
		return result;
	}

	public MyJobEntity(String name, String jobInfo, String money, String jobTime, String jobLocation, String otherInfo, String manager, Integer target) {
		this.name = name;
		this.jobInfo = jobInfo;
		this.money = money;
		this.jobTime = jobTime;
		this.jobLocation = jobLocation;
		this.otherInfo = otherInfo;
		this.manager = manager;
		this.target = target;
	}

	public MyJobEntity() {
	}
}
