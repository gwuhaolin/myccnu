package play.vote;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tool.CCNUPortal;
import tool.HibernateUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 3/13/14
 * Time: 8:25 PM
 */
public class ManageVote {

	public static VoteBasicInfo voteBasicInfo=new VoteBasicInfo();

	/**
	 * 用Id去数据库里获得一个
	 * @param id
	 * @return 如果有这个Id就返回,没有就返回null
	 */
	public static MyCampaignerEntity get(int id) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyCampaignerEntity where id=?");
		Object re = query.setInteger(0, id).uniqueResult();
		HibernateUtil.closeSession(session);
		if (re != null) {
			return (MyCampaignerEntity) re;
		} else {
			return null;
		}
	}

	/**
	 * 每次返回的都会随机排序
	 * @return
	 */
	public static List<MyCampaignerEntity> get_All() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyCampaignerEntity");
		List<MyCampaignerEntity> re =query.list();
		HibernateUtil.closeSession(session);
		Collections.shuffle(re);
		return re;
	}

	public static List<MyCampaignerEntity> get_All_SortByCount() {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyCampaignerEntity order by voteSum desc ");
		List<MyCampaignerEntity> re =query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	public static boolean add(MyCampaignerEntity voteCampaignerEntity) {
		return HibernateUtil.addEntity(voteCampaignerEntity);
	}

	public static boolean change(MyCampaignerEntity voteCampaignerEntity) {
		return HibernateUtil.updateEntity(voteCampaignerEntity);
	}

	public static boolean remove(int id) {
		if (id<0){
			return false;
		}
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("delete from MyCampaignerEntity where id=?");
		query.setInteger(0, id);
		int result= query.executeUpdate();
		HibernateUtil.closeSession(session);
		return result>0;
	}

	/**
	 * 先判断帐号密码是否正确,如果正确在写入数据库
	 * 添加一条新的投票记录
	 * 竞争者的总票数加一
	 *
	 * @param voterXH
	 * @param voterMM
	 * @param campaignerID
	 * @return 如果投票失败就回滚事务
	 * 0=成功
	 * 1=帐号密码错误
	 * 2=所选人数不对
	 * 3=该学号已经投票
	 * 4=投票已截至
	 */
	public static int vote(String voterXH,String voterMM,String[] ids){
		if (voteBasicInfo.VoteIsStop){
			return 4;
		}
		if(!CCNUPortal.XHMMisTrue(voterXH, voterMM)){
			return 1;
		}
		//如果所选人数不符合要求就直接返回
		if (ids.length!=voteBasicInfo.ShouldVoteCount){
			return 2;
		}
		//开始事务
		Session session= HibernateUtil.getSession();
		Transaction transaction=session.getTransaction();
		try {
			//把投票者加入已经 投票列
			session.save(new MyVoteEntity(voterXH));

			for(String id:ids){//被选中的全部加一
				Query query= session.createQuery("update MyCampaignerEntity set voteSum=voteSum+1 where id=?");
				query.setInteger(0,Integer.parseInt(id));
				query.executeUpdate();
			}
			//提交事务
			transaction.commit();
		}catch (HibernateException e){
			transaction.rollback();
			return 3;
		}
		return 0;
	}

	/**
	 * 清空投票数据库的表的所有数据
	 * @return 如果清空成功,就返回true,
	 */
	public static boolean clearDB(String MM){
		if(!MM.equals(VoteBasicInfo.ManagePassword)){
			return false;
		}
		try {
			Session session= HibernateUtil.getSession();
			Query query= session.createQuery("delete from MyCampaignerEntity");
			query.executeUpdate();
			query=session.createQuery("delete from MyVoteEntity");
			query.executeUpdate();
			HibernateUtil.closeSession(session);
		}catch (Exception e){
			return false;
		}
		return true;
	}

}
