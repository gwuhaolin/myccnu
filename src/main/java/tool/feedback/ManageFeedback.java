package tool.feedback;

import org.hibernate.Query;
import org.hibernate.Session;
import tool.HibernateUtil;
import tool.R;

import java.util.List;

/**
 * Created by wuhaolin on 9/1/14.
 * :
 */
public class ManageFeedback {

    /**
     * 用发布时间排序来获得用于分页查询
     *
     * @param from 从这个开始
     * @return
     */
    public static List<MyFeedbackEntity> get_page(int from) {
        Session session = HibernateUtil.getSession();
        Query query = session.createQuery("from MyFeedbackEntity order by id desc ");
        query.setFirstResult(from);
        query.setMaxResults(R.ChangeCount);
        List<MyFeedbackEntity> re = query.list();
        HibernateUtil.closeSession(session);
        return re;
    }

    /**
     * 向数据库中添加一条树洞,树洞的发布时间就是id排序
     *
     * @param shuDongEntity
     */
    public static void add(MyFeedbackEntity shuDongEntity) {
        HibernateUtil.addEntity(shuDongEntity);
    }

}
