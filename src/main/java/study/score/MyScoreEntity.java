package study.score;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;

/**
 * Created by wuhaolin on 3/3/15.
 * :
 */
@Entity
@Table(name = "myscore", schema = "", catalog = "weixin")
@IdClass(MyScoreEntityPK.class)
public class MyScoreEntity implements Comparable<MyScoreEntity>{
    private String xh;
    private String classNo;
    private String className;
    private Float sumScore;
    private Float qimoScore;
    private Float pinshiScore;
    private Float xuefen;
    private String term;
    private Integer classNumber;
    private String classFlag;
    private String zhufu;

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

    @Basic
    @Column(name = "term", nullable = true, insertable = true, updatable = true, length = 10)
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    @Basic
    @Column(name = "classNumber", nullable = true, insertable = true, updatable = true)
    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    @Basic
    @Column(name = "classFlag", nullable = true, insertable = true, updatable = true, length = 5)
    public String getClassFlag() {
        return classFlag;
    }

    public void setClassFlag(String classFlag) {
        this.classFlag = classFlag;
    }

    @Basic
    @Column(name = "zhufu", nullable = true, insertable = true, updatable = true, length = 1)
    public String getZhufu() {
        return zhufu;
    }

    public void setZhufu(String zhufu) {
        this.zhufu = zhufu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyScoreEntity that = (MyScoreEntity) o;

        if (classFlag != null ? !classFlag.equals(that.classFlag) : that.classFlag != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (classNo != null ? !classNo.equals(that.classNo) : that.classNo != null) return false;
        if (classNumber != null ? !classNumber.equals(that.classNumber) : that.classNumber != null) return false;
        if (pinshiScore != null ? !pinshiScore.equals(that.pinshiScore) : that.pinshiScore != null) return false;
        if (qimoScore != null ? !qimoScore.equals(that.qimoScore) : that.qimoScore != null) return false;
        if (sumScore != null ? !sumScore.equals(that.sumScore) : that.sumScore != null) return false;
        if (term != null ? !term.equals(that.term) : that.term != null) return false;
        if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;
        if (xuefen != null ? !xuefen.equals(that.xuefen) : that.xuefen != null) return false;
        if (zhufu != null ? !zhufu.equals(that.zhufu) : that.zhufu != null) return false;

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
        result = 31 * result + (term != null ? term.hashCode() : 0);
        result = 31 * result + (classNumber != null ? classNumber.hashCode() : 0);
        result = 31 * result + (classFlag != null ? classFlag.hashCode() : 0);
        result = 31 * result + (zhufu != null ? zhufu.hashCode() : 0);
        return result;
    }

    public MyScoreEntity() {
    }


    /**
     * 把HTML里的tr解析成一门成绩的详细信息
     *
     * @param tr 一门成绩
     */
    public MyScoreEntity(Element tr) {
        Elements tds = tr.children();
        this.term = tds.get(0).text();
        this.className = tds.get(1).text();
        this.classNo = tds.get(2).text();
        this.classNumber = Integer.parseInt(tds.get(3).text());
        this.classFlag = tds.get(4).text();
        this.pinshiScore = Float.parseFloat(tds.get(5).text());
        this.qimoScore = Float.parseFloat(tds.get(6).text());
        this.sumScore = Float.parseFloat(tds.get(7).text());
        this.xuefen = Float.parseFloat(tds.get(8).text());
        this.zhufu = tds.get(9).text();
    }

    /*
    优先按照学期排序再是总分
     */
    @Override
    public int compareTo(MyScoreEntity o) {
        int termCompareResult = 0;
        if (o.term != null && this.term != null) {
            termCompareResult = this.term.compareTo(o.term);
        }
        if (termCompareResult != 0) {
            return termCompareResult;
        } else {
            if ((this.getSumScore() - o.getSumScore()) > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
