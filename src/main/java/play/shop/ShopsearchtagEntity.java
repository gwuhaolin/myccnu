/**
 * Author: WuHaoLin
 * Date: 2014/5/17
 * Time: 11:03
 */

package play.shop;

import javax.persistence.*;

/**
 * 热门词汇搜索
 */
@Entity
@Table(name = "shopsearchtag", schema = "", catalog = "weixin")
class ShopSearchTagEntity {
    private int id;
    private String name;//热门词汇的名称
    private String value;//搜索值,如果有多个就以逗号隔开,搜索结果为所有的值相加
    private Integer weight;//该标签对应的权值

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "value", nullable = true, insertable = true, updatable = true, length = 255)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Basic
    @Column(name = "weight", nullable = true, insertable = true, updatable = true)
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopSearchTagEntity that = (ShopSearchTagEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return !(weight != null ? !weight.equals(that.weight) : that.weight != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }
}
