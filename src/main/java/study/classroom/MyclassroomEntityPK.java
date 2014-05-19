package study.classroom;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/3/31
 * Time: 20:56
 */
public class MyclassroomEntityPK implements Serializable {
	private String jiHaoLou;

	@Column(name = "JiHaoLou", nullable = false, insertable = true, updatable = true, length = 10)
	@Id
	public String getJiHaoLou() {
		return jiHaoLou;
	}

	public void setJiHaoLou(String jiHaoLou) {
		this.jiHaoLou = jiHaoLou;
	}

	private int xinQiJi;

	@Column(name = "XinQiJi", nullable = false, insertable = true, updatable = true)
	@Id
	public int getXinQiJi() {
		return xinQiJi;
	}

	public void setXinQiJi(int xinQiJi) {
		this.xinQiJi = xinQiJi;
	}

	private int diJiJieKe;

	@Column(name = "DiJiJieKe", nullable = false, insertable = true, updatable = true)
	@Id
	public int getDiJiJieKe() {
		return diJiJieKe;
	}

	public void setDiJiJieKe(int diJiJieKe) {
		this.diJiJieKe = diJiJieKe;
	}

	private String classroom;

	@Column(name = "Classroom", nullable = false, insertable = true, updatable = true, length = 20)
	@Id
	public String getClassroom() {
		return classroom;
	}

	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MyclassroomEntityPK that = (MyclassroomEntityPK) o;

		if (diJiJieKe != that.diJiJieKe) return false;
		if (xinQiJi != that.xinQiJi) return false;
		if (classroom != null ? !classroom.equals(that.classroom) : that.classroom != null) return false;
		return !(jiHaoLou != null ? !jiHaoLou.equals(that.jiHaoLou) : that.jiHaoLou != null);

	}

	@Override
	public int hashCode() {
		int result = jiHaoLou != null ? jiHaoLou.hashCode() : 0;
		result = 31 * result + xinQiJi;
		result = 31 * result + diJiJieKe;
		result = 31 * result + (classroom != null ? classroom.hashCode() : 0);
		return result;
	}
}
