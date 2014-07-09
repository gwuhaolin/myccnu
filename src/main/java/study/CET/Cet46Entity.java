package study.CET;

import javax.persistence.*;

/**
 * Created by wuhaolin on 7/7/14.
 * :
 */
@Entity
@Table(name = "cet46", schema = "", catalog = "weixin")
@IdClass(Cet46EntityPK.class)
public class Cet46Entity implements Comparable<Cet46Entity> {
	private Float sumScore;
	private String listening;
	private String reading;
	private String compre;
	private String essay;
	private String grade;
	private String name;
	private String xh;
	private String date;

	@Basic
	@Column(name = "sumScore", nullable = true, insertable = true, updatable = true, precision = 0)
	public Float getSumScore() {
		return sumScore;
	}

	public void setSumScore(Float sumScore) {
		this.sumScore = sumScore;
	}

	@Basic
	@Column(name = "listening", nullable = true, insertable = true, updatable = true, length = 255)
	public String getListening() {
		return listening;
	}

	public void setListening(String listening) {
		this.listening = listening;
	}

	@Basic
	@Column(name = "reading", nullable = true, insertable = true, updatable = true, length = 255)
	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	@Basic
	@Column(name = "compre", nullable = true, insertable = true, updatable = true, length = 255)
	public String getCompre() {
		return compre;
	}

	public void setCompre(String compre) {
		this.compre = compre;
	}

	@Basic
	@Column(name = "essay", nullable = true, insertable = true, updatable = true, length = 255)
	public String getEssay() {
		return essay;
	}

	public void setEssay(String essay) {
		this.essay = essay;
	}

	@Id
	@Column(name = "grade", nullable = false, insertable = true, updatable = true, length = 10)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Basic
	@Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@Column(name = "xh", nullable = false, insertable = true, updatable = true, length = 15)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Basic
	@Column(name = "date", nullable = true, insertable = true, updatable = true, length = 255)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Cet46Entity that = (Cet46Entity) o;

		if (compre != null ? !compre.equals(that.compre) : that.compre != null) return false;
		if (date != null ? !date.equals(that.date) : that.date != null) return false;
		if (essay != null ? !essay.equals(that.essay) : that.essay != null) return false;
		if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
		if (listening != null ? !listening.equals(that.listening) : that.listening != null) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (reading != null ? !reading.equals(that.reading) : that.reading != null) return false;
		if (sumScore != null ? !sumScore.equals(that.sumScore) : that.sumScore != null) return false;
		if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = sumScore != null ? sumScore.hashCode() : 0;
		result = 31 * result + (listening != null ? listening.hashCode() : 0);
		result = 31 * result + (reading != null ? reading.hashCode() : 0);
		result = 31 * result + (compre != null ? compre.hashCode() : 0);
		result = 31 * result + (essay != null ? essay.hashCode() : 0);
		result = 31 * result + (grade != null ? grade.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (xh != null ? xh.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}


	public boolean pass() {
		return this.sumScore >= 425;
	}

	public String sayToYou() {
//		String re="That's your <em>CET"+this.garde+"</em> score in <em>"+this.date+"</em>,";
		String re = "这是你在" + em(this.date) + "学期的" + em("CET" + this.grade) + "的成绩.";
		if (this.sumScore > 600) {//学霸
			re += em("学霸") + ",膜拜啊!快点截屏分享炫耀下吧.";
		} else if (this.sumScore > 500) {//一般
//			re+= "一般般";
		} else if (this.sumScore >= 425) {//低分飘过
			re += "<em>低分飘过</em>的你是不是心里窃喜呢?快截屏分享给你的小伙伴们看看吧.";
		} else if (this.sumScore > 400) {//可惜了差点
			re += "<em>就差那么一点</em>啊!快去一吐为快,求安慰吧.";
		} else if (this.sumScore > 350) {//没过!
			re += "明年还是一条好汉!";
		} else {//学杂啊
			re += "<em>学杂</em>!你还是快回家种田算了";
		}
		return re;
	}

	private static String em(String str) {
		return "<em>" + str + "</em>";
	}

	/**
	 * 获得排名
	 */
	public int rank() {
		double bfb;
		if (sumScore > 500) {
			bfb = calc((sumScore - 500) / 70);
		} else {
			bfb = 1 - calc((500 - sumScore) / 70);
		}
		bfb = 1 - bfb;
		return (int) Math.floor(bfb * 4100);
	}

	/**
	 * 正态分布函数
	 *
	 * @param u
	 * @return
	 */
	private static double calc(double u) {
		double y = Math.abs(u);
		double y2 = y * y;
		double z = Math.exp(-0.5 * y2) * 0.398942280401432678;
		double p = 0;
		int k = 28;
		double s = -1;
		double fj = k;

		if (y > 3) {
			//当y>3时
			for (int i = 1; i <= k; i++) {
				p = fj / (y + p);
				fj = fj - 1.0;
			}
			p = z / (y + p);
		} else {
			//当y<3时
			for (int i = 1; i <= k; i++) {
				p = fj * y2 / (2.0 * fj + 1.0 + s * p);
				s = -s;
				fj = fj - 1.0;
			}
			p = 0.5 - z * y / (1 - p);
		}
		if (u > 0) p = 1.0 - p;
		return p;
	}

	public Cet46Entity(Float sumScore, String listening, String reading, String compre, String essay, String garde) {
		this.sumScore = sumScore;
		this.listening = listening;
		this.reading = reading;
		this.compre = compre;
		this.essay = essay;
		this.grade = garde;
	}

	public Cet46Entity() {
	}

	@Override
	/**
	 * 优先按照考试时间来排序,其次按照6,4级
	 */
	public int compareTo(Cet46Entity o) {
		return this.date.compareTo(o.date) * 2 + this.grade.compareTo(o.getGrade());
	}
}
