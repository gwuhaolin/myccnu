package life.jobs;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tool.AutoUpdate;
import tool.HibernateUtil;
import tool.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/25/14
 * Time: 6:27 PM
 */
public class ManageJob {

	public static final int TARGET_PartTimeJob=1;
	public static final int TARGET_PrivateTeacher=2;
	public static final String ManagePassword ="JOBS";
	public static final String CMD_Change="change";
	public static final String CMD_Add="add";
	public static final String CMD_Delete="delete";

	static {
		update();
		AutoUpdate.start();
	}

	/**
	 * 验证管理员帐号密码是否正确
	 * @param password
	 * @return
	 */
	public static boolean managePasswordIsOK(String password){
		return password.equals(ManagePassword);
	}

	/**
	 * 向数据库中添加一个工作
	 * @param jobEntity
	 */
	public static void add(MyJobEntity jobEntity){
		HibernateUtil.addEntity(jobEntity);
	}

	/**
	 * 向数据库中添加很多工作
	 * @param jobEntity
	 */
	public static void add(List<MyJobEntity> jobEntitys){
		for (MyJobEntity jobEntity:jobEntitys){
			HibernateUtil.addEntity(jobEntity);
		}
	}

	/**
	 * 向数据库中添加工作要求内容不能一样
	 * @param jobEntity
	 */
	public static void add_NotSame(List<MyJobEntity> jobEntitys){
		for (MyJobEntity jobEntity:jobEntitys){
			if (!DBhasThisOne(jobEntity)){
				HibernateUtil.addEntity(jobEntity);
			}
		}
	}

	/**
	 * 数据库中是否有内容一样的记录
	 * @param name
	 * @return 如果有返回true
	 */
	private static boolean DBhasThisOne(MyJobEntity jobEntity){
		if (jobEntity==null){
			return true;
		}
		Session session=HibernateUtil.getSession();
		Query query= session.createQuery("from MyJobEntity as job where  job.jobInfo=? and job.target=?");
		query.setString(0,jobEntity.getJobInfo());
		query.setInteger(1,jobEntity.getTarget());
//		query.setInteger(2,jobEntity.getTarget());
//		query.setString(3,jobEntity.getJobLocation());
//		query.setString(4,jobEntity.getJobTime());
		int reSize= query.list().size();
		HibernateUtil.closeSession(session);
		return reSize >= 1;
	}

	/**
	 * 从数据库中获得一个
	 * @param id
	 * @return
	 */
	public static MyJobEntity get(int id){
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery("from MyJobEntity as job where job.id=?");
		query.setInteger(0,id);
		MyJobEntity re= (MyJobEntity)query.uniqueResult();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 用于分页查询,关键字搜索
	 *
	 * @param from 从这个开始
	 * @param size 拿出多少个
	 * @return
	 */
	public static List<MyJobEntity> search_page(int from,String want) {
		System.out.println("from:"+from+" want:"+want);
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyJobEntity where jobInfo like ? or name like ? order by id desc ");
		query.setString(0, "%" + want + "%");
		query.setString(1,"%"+want+"%");
		query.setFirstResult(from);
		query.setMaxResults(R.ChangeCount);
		List<MyJobEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 用于分页查询,按照发布时间排序
	 *
	 * @param from 从这个开始
	 * @param size 拿出多少个
	 * @return
	 */
	public static List<MyJobEntity> get_page(int from, int target) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyJobEntity where target=? order by id desc ");
		query.setInteger(0,target);
		query.setFirstResult(from);
		query.setMaxResults(R.ChangeCount);
		List<MyJobEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 用于分页查询,按照id排序
	 *
	 * @param from 从这个开始
	 * @param size 拿出多少个
	 * @return
	 */
	public static List<MyJobEntity> get_OrderByID(int from, int target) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyJobEntity where target=? order by id desc ");
		query.setInteger(0,target);
		query.setFirstResult(from);
		query.setMaxResults(R.ChangeCount);
		List<MyJobEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 数据库中删除一个job
	 * @param id
	 */
	public static void delete(int id){
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery("delete MyJobEntity where id=?");
		query.setInteger(0,id);
		query.executeUpdate();
		HibernateUtil.closeSession(session);
	}

	/**
	 * 向数据库中更新一个
	 * @param jobEntity
	 */
	public static void change(MyJobEntity jobEntity){
		HibernateUtil.updateEntity(jobEntity);
	}

	/**
	 * 扫描自动抓取兼职信息
	 * @return
	 */
	public static List<MyJobEntity> scanPartTimeJobs(){
		List<MyJobEntity> re=new LinkedList<MyJobEntity>();
		try {
			//找出最新的一张网页
			Document document= Jsoup.connect("http://xgb.ccnu.edu.cn/zz/list.asp?id=36").get();
			Elements links= document.getElementById("list").getElementsByTag("a");
			Element theNewestLink=links.get(0);
			for (Element a:links){
				if (a.text().contains("兼职信息")){
					theNewestLink=a;
					break;
				}
			}

			//对该网页分析
			document= Jsoup.connect("http://xgb.ccnu.edu.cn/zz/"+theNewestLink.attr("href")).get();
			Elements spans= document.getElementById("text").getElementsByTag("p");
			MyJobEntity newOne=null;
			for (Element span:spans){
				String text=span.text();
				String two[]= text.split("：", 2);
				if(two.length<2){
					continue;
				}
				if (two[0].contains("名称")){
					re.add(newOne);
					newOne=new MyJobEntity();
					newOne.setTarget(TARGET_PartTimeJob);
					newOne.setManager(two[1]);//公司名称
				}else if (two[0].contains("地址")){
					newOne.setJobLocation(two[1]);//工作地点
				}else if (two[0].contains("内容")){
					newOne.setJobInfo(two[1]);//工作内容
				}else if (two[0].contains("时间")){
					newOne.setJobTime(two[1]);//工作时间
				}else if (two[0].contains("酬金")){
					newOne.setMoney(two[1]);//工作报酬
				}else if (two[0].contains("备注")){
					newOne.setOtherInfo(two[1]);
				}
			}
			re.add(newOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 扫描自动抓取家教信息
	 * @return
	 */
	public static List<MyJobEntity> scanPrivateTeacher(){
		List<MyJobEntity> re=new LinkedList<MyJobEntity>();
		try {
			//找出最新的一张网页
			Document document= Jsoup.connect("http://xgb.ccnu.edu.cn/zz/list.asp?id=35").get();
			Elements links= document.getElementById("list").getElementsByTag("a");
			Element theNewestLink=links.get(0);
			for (Element a:links){
				if (a.text().contains("家教信息")){
					theNewestLink=a;
					break;
				}
			}

			//对该网页分析
			document= Jsoup.connect("http://xgb.ccnu.edu.cn/zz/"+theNewestLink.attr("href")).get();
			String all[]= document.getElementById("text").html().split("<br/>|<br />|<br>|<br >");
			MyJobEntity newOne=null;
			for (String one:all){
				String two[]=Jsoup.parse(one).text().split("：", 2);
				if(two.length<2){
					continue;
				}
				if (two[0].contains("编号")){
					re.add(newOne);
					newOne=new MyJobEntity();
					newOne.setTarget(TARGET_PrivateTeacher);
					newOne.setManager(two[1]);
				}else if (two[0].contains("科目")){
					newOne.setJobInfo(two[1]);//课程内容
				}else if (two[0].contains("要求")){
					newOne.setName(two[1]);//详细要求
				}else if (two[0].contains("地")){
					newOne.setJobLocation(two[1]);
				}
			}
			re.add(newOne);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 扫描更新
	 */
	public static void update(){
		System.out.println("开始扫描家教兼职信息");
		add_NotSame(scanPartTimeJobs());
		add_NotSame(scanPrivateTeacher());
		System.out.println("结束扫描家教兼职信息");
	}

	public static void main(String[] args) {
//		scanPartTimeJobs();
//		scanPrivateTeacher();
//		update();
		search_page(0,"1");
	}

}
