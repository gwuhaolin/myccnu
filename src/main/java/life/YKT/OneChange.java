package life.YKT;

import tool.Tool;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/22/14
 * Time: 1:34 PM
 */
public class OneChange {
	String time;//发生时间
	String location;//发生地点
	String changeMoney;//改变了多少钱
	String remainMoney;//剩下多少钱

	public OneChange(String time, String location, String changeMoney, String remainMoney) {
		this.time = time;
		this.location = location;
		this.changeMoney = changeMoney;
		this.remainMoney = remainMoney;
	}

	public OneChange() {
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(String changeMoney) {
		this.changeMoney = changeMoney;
	}

	public String getRemainMoney() {
		return remainMoney;
	}

	public void setRemainMoney(String remainMoney) {
		this.remainMoney = remainMoney;
	}

	/**
	 * 判断是否是今天的消息
	 * @return
	 */
	public boolean isTaday(){
		return this.time.split(" ",2)[0].trim().equals(Tool.time_YYYY_MM_DD());
	}
}
