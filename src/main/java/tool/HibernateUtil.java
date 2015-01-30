package tool;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/11/14
 * Time: 10:04 PM
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Hibernate框架
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration=new Configuration().configure();
            StandardServiceRegistryBuilder standardServiceRegistryBuilder=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            return configuration.buildSessionFactory(standardServiceRegistryBuilder.build());
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * 已经打开传送
     */
    public static Session getSession() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        return session;
    }

    public static void closeSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    /**
     * 向数据库中添加这个对象
     */
    public static boolean addEntity(Object o) {
        Session session = getSession();
        session.save(o);
        closeSession(session);
        return true;
    }

    /**
     * 向数据库中添加或更新这个对象
     */
    public static boolean addOrUpdateEntity(Object o) {
        Session session = getSession();
        session.saveOrUpdate(o);
        closeSession(session);
        return true;
    }

    /**
     * 向数据库中添加或更新多个对象
     */
    public static boolean addOrUpdateEntitys(List entitys) {
        Session session = getSession();
        entitys.forEach(session::saveOrUpdate);
        closeSession(session);
        return true;
    }


    /**
     * 从数据库中修改这个对象
     */
    public static boolean updateEntity(Object o) {
        Session session = getSession();
        session.update(o);
        closeSession(session);
        return true;
    }

    /**
     * 从数据库中删除这个对象
     */
    public static boolean removeEntity(Object o) {
        Session session = getSession();
        session.delete(o);
        closeSession(session);
        return true;
    }


}
