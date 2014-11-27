package tool.feedback;

import tool.Tool;

import javax.persistence.*;

/**
 * Created by wuhaolin on 9/1/14.
 * :
 */
@Entity
@Table(name = "myfeedback", schema = "", catalog = "weixin")
public class MyFeedbackEntity {
    private int id;
    private String date = Tool.time_YYYY_MM_DD_HH_MM_NOW();
    private String xh;
    private String content;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = true, insertable = true, updatable = true, length = 25)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "XH", nullable = true, insertable = true, updatable = true, length = 10)
    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    @Basic
    @Column(name = "content", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyFeedbackEntity that = (MyFeedbackEntity) o;

        if (id != that.id) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (xh != null ? xh.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    /**
     * 构造一条反馈
     *
     * @param con 反馈的内容
     * @param xh  提交反馈人的学号
     */
    public MyFeedbackEntity(String con, String xh) {
        this.content = con;
        this.xh = xh;
    }

    public MyFeedbackEntity() {
    }
}
