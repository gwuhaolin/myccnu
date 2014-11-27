/**
 * Author: WuHaoLin
 * Date: 2014/5/15
 * Time: 19:46
 */

package play.shop;

import tool.Tool;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/5/15
 * Time: 19:46
 */
@Entity
@Table(name = "shopitems", schema = "", catalog = "weixin")
public class ShopItemsEntity {
    private int id;
    private String name;
    private Float price;
    private String des;
    private String picUrl = ManageShopItem.DEFAULT_PIC_URL;
    private String date = Tool.time_YYYY_MM_DD_HH_MM_NOW();
    private String xh;
    private Integer seeCount = 0;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = true, insertable = true, updatable = true, precision = 0)
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Basic
    @Column(name = "des", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Basic
    @Column(name = "picURL", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Basic
    @Column(name = "date", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "XH", nullable = true, insertable = true, updatable = true, length = 255)
    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    @Basic
    @Column(name = "seeCount", nullable = true, insertable = true, updatable = true)
    public Integer getSeeCount() {
        return seeCount;
    }

    public void setSeeCount(Integer seeCount) {
        this.seeCount = seeCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopItemsEntity that = (ShopItemsEntity) o;

        if (id != that.id) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (des != null ? !des.equals(that.des) : that.des != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (picUrl != null ? !picUrl.equals(that.picUrl) : that.picUrl != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (seeCount != null ? !seeCount.equals(that.seeCount) : that.seeCount != null) return false;
        return !(xh != null ? !xh.equals(that.xh) : that.xh != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (des != null ? des.hashCode() : 0);
        result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (xh != null ? xh.hashCode() : 0);
        result = 31 * result + (seeCount != null ? seeCount.hashCode() : 0);
        return result;
    }
}
