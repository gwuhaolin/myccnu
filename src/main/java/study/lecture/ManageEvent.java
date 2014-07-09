package study.lecture;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tool.AutoUpdate;
import tool.HibernateUtil;
import tool.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/25/14
 * Time: 10:22 PM
 */
public class ManageEvent {

	public static final int TARGET_Lecture =1;
	public static final int TARGET_School=2;
	private static final String PASSWORD="EVENT";
	public static final String CMD_Change="change";
	public static final String CMD_Add="add";
	public static final String CMD_Delete="delete";

	static {
		update();
		AutoUpdate.start();
	}


	public static void update(){
		System.out.println("开始扫描讲座信息");
		add_NotSame(get0());
		System.out.println("结束扫描讲座信息");
	}

	public static boolean ManagePasswordIsOK(String password){
		return password.equals(PASSWORD);
	}

	/**
	 * 从数据库中获得一个
	 * @param id
	 * @return
	 */
	public static MyEventEntity get(int id){
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery("from MyEventEntity as event where event.id=?");
		query.setInteger(0,id);
		MyEventEntity re= (MyEventEntity)query.uniqueResult();
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
	public static List<MyEventEntity> get_page(int from, int target) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyEventEntity where target=? order by id desc ");
		query.setInteger(0,target);
		query.setFirstResult(from);
		query.setMaxResults(R.ChangeCount);
		List<MyEventEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 用于分页查询,按照id排序,关键字搜索
	 *
	 * @param from 从这个开始
	 * @param size 拿出多少个
	 * @return
	 */
	public static List<MyEventEntity> search_page(int from, int target,String want) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyEventEntity where target=? and name like ? order by id desc ");
		query.setInteger(0,target);
		query.setString(1,"%"+want+"%");
		query.setFirstResult(from);
		query.setMaxResults(R.ChangeCount);
		List<MyEventEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 数据库中删除一个event
	 * @param id
	 */
	public static void delete(int id){
		Session session=HibernateUtil.getSession();
		Query query=session.createQuery("delete MyEventEntity where id=?");
		query.setInteger(0,id);
		query.executeUpdate();
		HibernateUtil.closeSession(session);
	}

	/**
	 * 向数据库中更新一个
	 * @param eventEntity
	 */
	public static void change(MyEventEntity eventEntity){
		HibernateUtil.updateEntity(eventEntity);
	}

	/**
	 * 向数据库中添加一个工作
	 * @param jobEntity
	 */
	public static void add(MyEventEntity eventEntity){
		HibernateUtil.addEntity(eventEntity);
	}

	/**
	 * 向数据库中添加工作
	 * @param eventEntity
	 */
	public static void add(List<MyEventEntity> eventEntitys){
		for (MyEventEntity eventEntity:eventEntitys){
			HibernateUtil.addEntity(eventEntity);
		}
	}

	private static final String URL_Lecture ="http://lecture.guisheng.net/";

	/**
	 * 向数据库中添加工作要求内容不能一样
	 * @param jobEntity
	 */
	private static void add_NotSame(List<MyEventEntity> jobEntitys){
		for (MyEventEntity myEventEntity:jobEntitys){
			if (!DBhasThisOne(myEventEntity)){
				HibernateUtil.addEntity(myEventEntity);
			}
		}
	}

	/**
	 * 数据库中是否有内容一样的记录
	 * @param name
	 * @return 如果有返回true
	 */
	private static boolean DBhasThisOne(MyEventEntity myEventEntity){
		if (myEventEntity==null){
			return true;
		}
		Session session=HibernateUtil.getSession();
		Query query= session.createQuery("from MyEventEntity as event where event.name=? and event.runLocation=? and event.runDate=?");
		query.setString(0,myEventEntity.getName());
		query.setString(1,myEventEntity.getRunLocation());
		query.setString(2, myEventEntity.getRunDate());
		int reSize= query.list().size();
		HibernateUtil.closeSession(session);
		return reSize >= 1;
	}

	/////////////////////////////////////////////////////
	/**
	 * 从桂声网抓取讲座信息
	 * @param link
	 * @return
	 */
	private static List<MyEventEntity> get0() {
		List<MyEventEntity> allLectures=new ArrayList<MyEventEntity>();
		try {
			Document document= Jsoup.connect(URL_Lecture).get();
			Elements allLinks= document.getElementById("lectures").getElementsByClass("post");
			for (int i=0;i<allLinks.size();i++){
				Element link=allLinks.get(i).getElementsByTag("a").first();
				String href=link.attr("href");
				try {
					MyEventEntity re = new MyEventEntity();
					re.setTarget(ManageEvent.TARGET_Lecture);//设置类型为讲座
					Element le= Jsoup.connect(href).get().getElementById("single-content");
					String title = le.getElementsByTag("h1").text();re.setName(title);
					Elements infos= le.getElementsByClass("detail").first().getElementsByTag("span");
					String author=infos.get(1).text();re.setManager(author);
					String time=infos.get(3).text();re.setRunDate(time);
					String location=infos.get(5).text();re.setRunLocation(location);
					String detial=le.getElementsByClass("text").first().text();re.setIntro(detial);
					allLectures.add(re);
				} catch (Exception e) {
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allLectures;
	}

	public static void main(String[] args) {

	}
}
