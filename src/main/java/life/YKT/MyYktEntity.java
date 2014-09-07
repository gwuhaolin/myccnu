package life.YKT;

import tool.Tool;

import javax.persistence.*;

/**
 * Created by wuhaolin on 7/8/14.
 * :
 */
@Entity
@Table(name = "myykt", schema = "", catalog = "weixin")
@IdClass(MyYktEntityPK.class)
public class MyYktEntity {
	private String time;//发生时间
	private String location;//发生地点
	private String changeMoney;//改变了多少钱
	private String remainMoney;//剩下多少钱
	private Integer type;//数据类型,区分是一卡通状态0,消费明细1,补助2还是刷卡考勤3
	private String xh;//所属者的学号

	@Id
	@Column(name = "time", nullable = false, insertable = true, updatable = true, length = 30)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
		if (this.time==null){
			this.time="";
		}
	}

	@Id
	@Column(name = "location", nullable = false, insertable = true, updatable = true, length = 30)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		if (this.location==null){
			this.location="";
		}
	}

	@Basic
	@Column(name = "changeMoney", nullable = true, insertable = true, updatable = true, length = 10)
	public String getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}

	@Basic
	@Column(name = "remainMoney", nullable = true, insertable = true, updatable = true, length = 10)
	public String getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(String remainMoney) {
		this.remainMoney = remainMoney;
	}

	@Id
	@Column(name = "type", nullable = false, insertable = true, updatable = true)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Id
	@Column(name = "xh", nullable = false, insertable = true, updatable = true, length = 15)
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

		MyYktEntity that = (MyYktEntity) o;

		if (type != that.type) return false;
		if (changeMoney != null ? !changeMoney.equals(that.changeMoney) : that.changeMoney != null) return false;
		if (location != null ? !location.equals(that.location) : that.location != null) return false;
		if (remainMoney != null ? !remainMoney.equals(that.remainMoney) : that.remainMoney != null) return false;
		if (time != null ? !time.equals(that.time) : that.time != null) return false;
		if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = time != null ? time.hashCode() : 0;
		result = 31 * result + (location != null ? location.hashCode() : 0);
		result = 31 * result + (changeMoney != null ? changeMoney.hashCode() : 0);
		result = 31 * result + (remainMoney != null ? remainMoney.hashCode() : 0);
		result = 31 * result + type;
		result = 31 * result + (xh != null ? xh.hashCode() : 0);
		return result;
	}

	/**
	 * 判断是否是今天的消息
	 */
	public boolean today() {
		return this.time.split(" ", 2)[0].trim().equals(Tool.time_YYYY_MM_DD());
	}
}
