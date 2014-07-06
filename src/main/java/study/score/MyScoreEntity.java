package study.score;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;

/**
 * Created by wuhaolin on 7/3/14.
 * :
 */
@Entity
@Table(name = "MyScore", schema = "", catalog = "weixin")
@IdClass(MyScoreEntityPK.class)
public class MyScoreEntity implements Comparable<MyScoreEntity>{
	private String xh;
	private String classNo;
	private String className;
	private Float sumScore;
	private Float qimoScore;
	private Float pinshiScore;
	private Float xuefen;

	@Id
	@Column(name = "XH", nullable = false, insertable = true, updatable = true, length = 15)
	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	@Id
	@Column(name = "classNo", nullable = false, insertable = true, updatable = true, length = 15)
	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	@Basic
	@Column(name = "className", nullable = true, insertable = true, updatable = true, length = 30)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Basic
	@Column(name = "sumScore", nullable = true, insertable = true, updatable = true, precision = 0)
	public Float getSumScore() {
		return sumScore;
	}

	public void setSumScore(Float sumScore) {
		this.sumScore = sumScore;
	}

	@Basic
	@Column(name = "qimoScore", nullable = true, insertable = true, updatable = true, precision = 0)
	public Float getQimoScore() {
		return qimoScore;
	}

	public void setQimoScore(Float qimoScore) {
		this.qimoScore = qimoScore;
	}

	@Basic
	@Column(name = "pinshiScore", nullable = true, insertable = true, updatable = true, precision = 0)
	public Float getPinshiScore() {
		return pinshiScore;
	}

	public void setPinshiScore(Float pinshiScore) {
		this.pinshiScore = pinshiScore;
	}

	@Basic
	@Column(name = "xuefen", nullable = true, insertable = true, updatable = true, precision = 0)
	public Float getXuefen() {
		return xuefen;
	}

	public void setXuefen(Float xuefen) {
		this.xuefen = xuefen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MyScoreEntity that = (MyScoreEntity) o;

		if (className != null ? !className.equals(that.className) : that.className != null) return false;
		if (classNo != null ? !classNo.equals(that.classNo) : that.classNo != null) return false;
		if (pinshiScore != null ? !pinshiScore.equals(that.pinshiScore) : that.pinshiScore != null) return false;
		if (qimoScore != null ? !qimoScore.equals(that.qimoScore) : that.qimoScore != null) return false;
		if (sumScore != null ? !sumScore.equals(that.sumScore) : that.sumScore != null) return false;
		if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;
		if (xuefen != null ? !xuefen.equals(that.xuefen) : that.xuefen != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = xh != null ? xh.hashCode() : 0;
		result = 31 * result + (classNo != null ? classNo.hashCode() : 0);
		result = 31 * result + (className != null ? className.hashCode() : 0);
		result = 31 * result + (sumScore != null ? sumScore.hashCode() : 0);
		result = 31 * result + (qimoScore != null ? qimoScore.hashCode() : 0);
		result = 31 * result + (pinshiScore != null ? pinshiScore.hashCode() : 0);
		result = 31 * result + (xuefen != null ? xuefen.hashCode() : 0);
		return result;
	}


	@Override
	public int compareTo(MyScoreEntity o) {
		if (this.sumScore > o.sumScore) {
			return 1;
		} else {
			return -1;
		}
	}

	public MyScoreEntity() {
	}

	/**
	 * 把HTML里的tr解析成一门成绩的详细信息
	 *
	 * @param tr
	 */
	public MyScoreEntity(Element tr) {
		Elements tds = tr.children();
		this.className = tds.get(1).text();
		this.classNo = tds.get(2).text();
		this.sumScore = Float.parseFloat(tds.get(7).text());
		this.pinshiScore = Float.parseFloat(tds.get(5).text());
		this.qimoScore = Float.parseFloat(tds.get(6).text());
		this.xuefen = Float.parseFloat(tds.get(8).text());
	}
}
