package study.classroom;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/3/31
 * Time: 20:56
 */
@Entity
@Table(name = "myclassroom", schema = "", catalog = "weixin")
//@IdClass(MyClassroomEntityPK.class)
public class MyClassroomEntity {
    private String jiHaoLou;
    private int xinQiJi;
    private int diJiJieKe;
    private String classroom;

    @Id
    @Column(name = "JiHaoLou", nullable = false, insertable = true, updatable = true, length = 10)
    public String getJiHaoLou() {
        return jiHaoLou;
    }

    public void setJiHaoLou(String jiHaoLou) {
        this.jiHaoLou = jiHaoLou;
    }

    @Id
    @Column(name = "XinQiJi", nullable = false, insertable = true, updatable = true)
    public int getXinQiJi() {
        return xinQiJi;
    }

    public void setXinQiJi(int xinQiJi) {
        this.xinQiJi = xinQiJi;
    }

    @Id
    @Column(name = "DiJiJieKe", nullable = false, insertable = true, updatable = true)
    public int getDiJiJieKe() {
        return diJiJieKe;
    }

    public void setDiJiJieKe(int diJiJieKe) {
        this.diJiJieKe = diJiJieKe;
    }

    @Id
    @Column(name = "Classroom", nullable = false, insertable = true, updatable = true, length = 20)
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

        MyClassroomEntity that = (MyClassroomEntity) o;

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

    public MyClassroomEntity(String jiHaoLou, int xinQiJi, int diJiJieKe, String classroom) {
        this.jiHaoLou = jiHaoLou;
        this.xinQiJi = xinQiJi;
        this.diJiJieKe = diJiJieKe;
        this.classroom = classroom;
    }

    public MyClassroomEntity() {
    }
}
