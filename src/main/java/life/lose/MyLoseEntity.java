package life.lose;

import tool.Tool;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 3/3/14
 * Time: 9:09 PM
 */
@Entity
@Table(name = "mylose", schema = "", catalog = "weixin")
public class MyLoseEntity {
    private int id;
    private Byte myType;
    private Byte myState = ManageLose.STATE_Finding;
    private String myDes;
    private String myPhone;
    private String myDate = Tool.time_YYYY_MM_DD();
    private String myLocation;
    private String myXh;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MyType", nullable = true, insertable = true, updatable = true, length = 3, precision = 0)
    public Byte getMyType() {
        return myType;
    }

    public void setMyType(Byte myType) {
        this.myType = myType;
    }

    @Basic
    @Column(name = "MyState", nullable = true, insertable = true, updatable = true, length = 3, precision = 0)
    public Byte getMyState() {
        return myState;
    }

    public void setMyState(Byte myState) {
        this.myState = myState;
    }

    @Basic
    @Column(name = "MyDes", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    public String getMyDes() {
        return myDes;
    }

    public void setMyDes(String myDes) {
        this.myDes = myDes;
    }

    @Basic
    @Column(name = "MyPhone", nullable = true, insertable = true, updatable = true, length = 25, precision = 0)
    public String getMyPhone() {
        return myPhone;
    }

    public void setMyPhone(String myPhone) {
        this.myPhone = myPhone;
    }

    @Basic
    @Column(name = "MyDate", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    public String getMyDate() {
        return myDate;
    }

    public void setMyDate(String myDate) {
        this.myDate = myDate;
    }

    @Basic
    @Column(name = "MyLocation", nullable = true, insertable = true, updatable = true, length = 255, precision = 0)
    public String getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(String myLocation) {
        this.myLocation = myLocation;
    }

    @Basic
    @Column(name = "MyXH", nullable = true, insertable = true, updatable = true, length = 20, precision = 0)
    public String getMyXh() {
        return myXh;
    }

    public void setMyXh(String myXh) {
        this.myXh = myXh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyLoseEntity that = (MyLoseEntity) o;

        if (id != that.id) return false;
        if (myDate != null ? !myDate.equals(that.myDate) : that.myDate != null) return false;
        if (myDes != null ? !myDes.equals(that.myDes) : that.myDes != null) return false;
        if (myLocation != null ? !myLocation.equals(that.myLocation) : that.myLocation != null) return false;
        if (myPhone != null ? !myPhone.equals(that.myPhone) : that.myPhone != null) return false;
        if (myState != null ? !myState.equals(that.myState) : that.myState != null) return false;
        if (myType != null ? !myType.equals(that.myType) : that.myType != null) return false;
        return !(myXh != null ? !myXh.equals(that.myXh) : that.myXh != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (myType != null ? myType.hashCode() : 0);
        result = 31 * result + (myState != null ? myState.hashCode() : 0);
        result = 31 * result + (myDes != null ? myDes.hashCode() : 0);
        result = 31 * result + (myPhone != null ? myPhone.hashCode() : 0);
        result = 31 * result + (myDate != null ? myDate.hashCode() : 0);
        result = 31 * result + (myLocation != null ? myLocation.hashCode() : 0);
        result = 31 * result + (myXh != null ? myXh.hashCode() : 0);
        return result;
    }


    public MyLoseEntity(Byte myType, String myDes, String myPhone, String myLocation, String XH) {
        this.myType = myType;
        this.myDes = myDes;
        this.myPhone = myPhone;
        this.myLocation = myLocation;
        this.myXh = XH;
    }

    public MyLoseEntity() {
    }

    public boolean today() {
        return this.myDate.equals(Tool.time_YYYY_MM_DD());
    }

    public String stateString() {
        if (this.myState == ManageLose.STATE_Finding) {
            return "正在招领中...";
        } else if (this.myState == ManageLose.STATE_Complete) {
            return "已完成招领";
        }
        return "";
    }

    public String loseOrUpdateChinese() {
        if (this.myType == ManageLose.TYPE_Lose) {
            return "掉了";
        } else if (this.myType == ManageLose.TYPE_Update) {
            return "捡到";
        }
        return "";
    }

    public String loseOrUpdateGl() {
        if (this.myType == ManageLose.TYPE_Lose) {
            return "frown red";
        } else if (this.myType == ManageLose.TYPE_Update) {
            return "smile green";
        }
        return "";
    }
}
