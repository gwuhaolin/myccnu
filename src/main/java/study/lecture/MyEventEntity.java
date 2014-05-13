package study.lecture;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/25/14
 * Time: 10:59 PM
 */
@Entity
@Table(name = "myevent", schema = "", catalog = "weixin")
public class MyEventEntity {
	private int id;
	private String name;
	private String manager;
	private String runDate;
	private String runLocation;
	private String intro;
	private String otherInfo;
	private Integer target;//是讲座还是学院活动 public static final int TARGET_Lecture =1; public static final int TARGET_School=2;

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
	@Column(name = "manager", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	@Basic
	@Column(name = "runDate", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}

	@Basic
	@Column(name = "runLocation", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
	public String getRunLocation() {
		return runLocation;
	}

	public void setRunLocation(String runLocation) {
		this.runLocation = runLocation;
	}

	@Basic
	@Column(name = "intro", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

		MyEventEntity that = (MyEventEntity) o;

		if (id != that.id) return false;
		if (intro != null ? !intro.equals(that.intro) : that.intro != null) return false;
		if (manager != null ? !manager.equals(that.manager) : that.manager != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (otherInfo != null ? !otherInfo.equals(that.otherInfo) : that.otherInfo != null) return false;
		if (runDate != null ? !runDate.equals(that.runDate) : that.runDate != null) return false;
		if (runLocation != null ? !runLocation.equals(that.runLocation) : that.runLocation != null) return false;
		if (target != null ? !target.equals(that.target) : that.target != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (manager != null ? manager.hashCode() : 0);
		result = 31 * result + (runDate != null ? runDate.hashCode() : 0);
		result = 31 * result + (runLocation != null ? runLocation.hashCode() : 0);
		result = 31 * result + (intro != null ? intro.hashCode() : 0);
		result = 31 * result + (otherInfo != null ? otherInfo.hashCode() : 0);
		result = 31 * result + (target != null ? target.hashCode() : 0);
		return result;
	}

	public MyEventEntity(String name, String manager, String runDate, String runLocation, String intro, String otherInfo, Integer target) {
		this.name = name;
		this.manager = manager;
		this.runDate = runDate;
		this.runLocation = runLocation;
		this.intro = intro;
		this.otherInfo = otherInfo;
		this.target = target;
	}

	public MyEventEntity() {
	}
}
