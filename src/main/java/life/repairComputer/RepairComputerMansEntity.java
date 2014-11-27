package life.repairComputer;

import javax.persistence.*;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/3/29
 * Time: 22:18
 */
@Entity
@Table(name = "repaircomputermans", schema = "", catalog = "weixin")
public class RepairComputerMansEntity {
    private int id;
    private String name;
    private String phone;
    private String qq;
    private Integer jobsCount;
    private String address;
    private String org;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Phone", nullable = true, insertable = true, updatable = true, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "QQ", nullable = true, insertable = true, updatable = true, length = 20)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "JobsCount", nullable = true, insertable = true, updatable = true)
    public Integer getJobsCount() {
        return jobsCount;
    }

    public void setJobsCount(Integer jobsCount) {
        this.jobsCount = jobsCount;
    }

    @Basic
    @Column(name = "Address", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "org", nullable = true, insertable = true, updatable = true, length = 20)
    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepairComputerMansEntity that = (RepairComputerMansEntity) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (jobsCount != null ? !jobsCount.equals(that.jobsCount) : that.jobsCount != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (org != null ? !org.equals(that.org) : that.org != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        return !(qq != null ? !qq.equals(that.qq) : that.qq != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (jobsCount != null ? jobsCount.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (org != null ? org.hashCode() : 0);
        return result;
    }
}
