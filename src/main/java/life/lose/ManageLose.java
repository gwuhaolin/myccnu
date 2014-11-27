package life.lose;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 3/3/14
 * Time: 7:29 PM
 */
public class ManageLose {
    public static final byte TYPE_Lose = 1;//掉了东西
    public static final byte TYPE_Update = 2;//捡到东西
    public static final byte STATE_Finding = 1;//正在寻找
    public static final byte STATE_Complete = 2;//已经完成找到x`

    /**
     * 按照发布时间排序记录
     *
     * @return
     */
    public static List<MyLoseEntity> get_page(int from, byte type) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from MyLoseEntity where myType=? order by myState asc ,id desc ");
        query.setByte(0, type);
        query.setFirstResult(from);
        query.setMaxResults(R.ChangeCount);
        List<MyLoseEntity> re = query.list();
        HibernateUtil.closeSession(session);
        return re;
    }

    /**
     * 按照发布时间排序记录,获取学号为XH的同学发布的所有失物信息,如果学号为空返回空集合
     *
     * @param XH
     * @return
     */
    public static List<MyLoseEntity> get(String XH) {
        if (XH == null || XH.length() < 2) {
            return new LinkedList<MyLoseEntity>();
        }
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from MyLoseEntity where myXh=? order by myState asc ,id desc ");
        query.setString(0, XH);
        List<MyLoseEntity> re = query.list();
        HibernateUtil.closeSession(session);
        return re;
    }

    /**
     * 按照发布时间排序记录,获取学号为XH的同学发布的所有失物信息,如果学号为空返回空集合
     */
    public static List<MyLoseEntity> search_page(int from, String want) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from MyLoseEntity where myDes like ? order by myState asc ,id desc ");
        query.setString(0, "%" + want + "%");
        query.setFirstResult(from);
        query.setMaxResults(R.ChangeCount);
        List<MyLoseEntity> re = query.list();
        HibernateUtil.closeSession(session);
        return re;
    }

    /**
     * 添加一条新的失物招领记录
     *
     * @param des
     * @param location
     * @param phone
     * @param type
     */
    public static void add(String des, String location, String phone, byte type, String XH) {
        MyLoseEntity loseEntity = new MyLoseEntity(type, des, phone, location, XH);
        HibernateUtil.addEntity(loseEntity);
    }

    /**
     * 把id处的记录设置为完成
     *
     * @param id
     */
    public static String complete(int id, String XH) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from MyLoseEntity where id=?");
        query.setInteger(0, id);
        MyLoseEntity re = (MyLoseEntity) query.uniqueResult();
        String reString = "";
        if (re.getMyXh() != null && re.getMyXh().equals(XH)) {
            re.setMyState(STATE_Complete);
            reString = XH + "的失物招领信息修改为已成功完成招领";
        } else if (re.getMyXh() == null) {
            re.setMyState(STATE_Complete);
            reString = "失物招领信息修改为已成功完成招领";
        } else {
            reString = "你的身份不合法";
        }
        session.update(re);
        HibernateUtil.closeSession(session);
        return reString;
    }

    public static void main(String[] args) {
        List<MyLoseEntity> re = get_page(0, (byte) 1);
        System.out.print(re);
    }

}
