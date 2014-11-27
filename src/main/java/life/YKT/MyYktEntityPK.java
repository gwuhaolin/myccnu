package life.YKT;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by wuhaolin on 7/8/14.
 * :
 */
public class MyYktEntityPK implements Serializable {
    private String time;

    @Column(name = "time", nullable = false, insertable = true, updatable = true, length = 30)
    @Id
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String location;

    @Column(name = "location", nullable = false, insertable = true, updatable = true, length = 30)
    @Id
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private int type;

    @Column(name = "type", nullable = false, insertable = true, updatable = true)
    @Id
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private String xh;

    @Column(name = "xh", nullable = false, insertable = true, updatable = true, length = 15)
    @Id
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

        MyYktEntityPK that = (MyYktEntityPK) o;

        if (type != that.type) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + type;
        result = 31 * result + (xh != null ? xh.hashCode() : 0);
        return result;
    }
}
