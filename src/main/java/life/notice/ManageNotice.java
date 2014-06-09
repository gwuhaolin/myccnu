package life.notice;

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
import java.util.LinkedList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/22/14
 * Time: 5:32 PM
 */
public class ManageNotice {

	public static final String CMD_Add = "add";
	public static final String CMD_Delete = "delete";
	public static final String CMD_Change = "change";
	public static final String ManagePassword = "NOTICE";
	public static final int IsOK_YES = 0;//用于标准人工筛选后的信息删除,该信息ok
	public static final int IsOK_NO = 1;//用于标准人工筛选后的信息删除,该信息不符合要求不能被显示
	public static final String[][] FromSiteName = {
			{"教务处-教学教务", "http://jwc.ccnu.edu.cn/"},//0
			{"学生资助", "http://xgb.ccnu.edu.cn/"},//1
			{"勤工之家", "http://xgb.ccnu.edu.cn/"},//2
			{"奖助学金", "http://xgb.ccnu.edu.cn/"},//3
			{"助学贷款", "http://xgb.ccnu.edu.cn/"},//4
			{"网络中心", "http://nisc.ccnu.edu.cn/"},//5
			{"华师官网", "http://www.ccnu.edu.cn/"},//6
			{"研究生会", "http://gsu.ccnu.edu.cn/"},//7
			{"后勤集团", "http://houqin.ccnu.edu.cn/"},//8
			{"一卡通中心", "http://ecard.ccnu.edu.cn/"},//9
			{"校图书馆", "http://lib.ccnu.edu.cn/"},//10
			{"学工动态", "http://www.guisheng.net/"},//11
			{"华大青年网", "http://www.ccnuyouth.com/"},//12

	};

	public static final String[][] FromSiteName1 = {
			{"1", "2"},
			{"2", "3"},
	};


	/**
	 * 用来判断该标题的文章是一个通知的筛选器,如果包含了该关键字就认为是文明想要的
	 */
	public static final String FilterString[] = {
			"通知",
			"安排",
			"说明",
			"启事",
	};

	/**
	 * 加载类时自动更新最新通知
	 */
	static {
		update();
		AutoUpdate.start();
	}

	/**
	 * 判断管理员密码是否正确
	 *
	 * @param password
	 * @return
	 */
	public static boolean managePasswordIsOK(String password) {
		return password.equals(ManagePassword);
	}

	/**
	 * 判断数据库中是否含有该标题的文章而且时间相同
	 *
	 * @param title
	 * @return 如果有返回true
	 */
	public static boolean DBhasThisOne(String title, String date) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyNoticeEntity as notice where notice.title=? and date=?");
		query.setString(0, title);
		query.setString(1, date);
		int re = query.list().size();
		HibernateUtil.closeSession(session);
		return re >= 1;
	}

	/**
	 * 如果数据库中没有该标题的文章就加入数据库中
	 *
	 * @param noticeEntity
	 */
	public static void add_NotSame(List<MyNoticeEntity> noticeEntity) {
		for (MyNoticeEntity entity : noticeEntity) {
			if (!DBhasThisOne(entity.getTitle(), entity.getDate())) {
				HibernateUtil.addEntity(entity);
			}
		}
	}

	/**
	 * 如果数据库中没有该标题的文章就加入数据库中
	 *
	 * @param noticeEntity
	 */
	public static void add_NotSame(MyNoticeEntity noticeEntity) {
		HibernateUtil.addEntity(noticeEntity);
	}

	/**
	 * 修改该通知,在数据库中
	 *
	 * @param noticeEntity
	 */
	public static void changeNotice(MyNoticeEntity noticeEntity) {
		HibernateUtil.updateEntity(noticeEntity);
	}

	/**
	 * 把这个设置为不是我们想要的通知,但还在数据库里
	 *
	 * @param id
	 */
	public static void remove(int id) {
		MyNoticeEntity removeOne = get(id);
		removeOne.setIsOk(IsOK_NO);
		changeNotice(removeOne);
	}

	/**
	 * 用文章的id去数据库中获得一篇文章
	 *
	 * @param id
	 * @return 如果数据库中不存在该文章就返回null
	 */
	public static MyNoticeEntity get(int id) {
		Session session = HibernateUtil.getSession();
		try {
			MyNoticeEntity re = (MyNoticeEntity) session.createQuery("from MyNoticeEntity as notice where notice.id=?").setInteger(0, id).uniqueResult();
			HibernateUtil.closeSession(session);
			return re;
		} catch (Exception e) {
			HibernateUtil.closeSession(session);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用于分页查询加关键字搜索
	 *
	 * @param from 从这个开始
	 * @param size 拿出多少个
	 * @return
	 */
	public static List<MyNoticeEntity> search_page(int from, String want) {
		System.out.println("form=" + from + " want=" + want);
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyNoticeEntity as notice where isOk=0 and content like ? or orgUrl like ? order by notice.date desc ,id desc");
		query.setString(0, "%" + want + "%");
		query.setString(1, "%" + want + "%");
		query.setFirstResult(from);
		query.setMaxResults(R.ChangeCount);
		List<MyNoticeEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 用于分页查询
	 *
	 * @param from 从这个开始
	 * @param size 拿出多少个
	 * @return
	 */
	public static List<MyNoticeEntity> get_page(int from) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyNoticeEntity as notice where isOk=0 order by notice.date desc ,id desc");
		query.setFirstResult(from);
		query.setMaxResults(R.ChangeCount);
		List<MyNoticeEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 用于分页查询,
	 *
	 * @param from 从这个开始
	 * @param size 拿出多少个
	 * @return
	 */
	public static List<MyNoticeEntity> get_page_ccnuyouth(int from) {
		Session session = HibernateUtil.getSession();
		Query query = session.createQuery("from MyNoticeEntity as notice where isOk=0 and orgUrl like ? order by notice.date desc ,id desc");
		query.setString(0, FromSiteName[12][1]);
		query.setFirstResult(from);
		query.setMaxResults(R.ChangeCount);
		List<MyNoticeEntity> re = query.list();
		HibernateUtil.closeSession(session);
		return re;
	}

	/**
	 * 对从html里提起到的文章的原链接进行检查处理,如果他包含http即是完整的url就原样返回.如果不是完整的url即不包含http://就求得原url然后返回
	 *
	 * @param orgUrl   从html里提起到的文章的原链接
	 * @param BasicUrl 该文章来源网站的根URL
	 * @return
	 */
	private static String checkURL(Element link, String BasicUrl) {
		String orgUrl = link.attr("href");//原地址
		if (!orgUrl.contains("http://")) {//处理不合法的源地址使其变为合法的
			orgUrl = BasicUrl + link.attr("href").replace("..", "");
		}
		return orgUrl;
	}

	/**
	 * 对html进行semanticUI修饰
	 * 把所有的图片全都加上semanticUI响应式图片类
	 * 把所有的表格全都加上semanticUI响应式表格
	 *
	 * @param document
	 * @return
	 */
	public static Document semanticUI(Document document, String baseURL) {
		//关键字去除
		String tempStr = document.html().replaceAll(";&nbsp|<br>|</br>", "");
		document = Jsoup.parse(tempStr);

		//Bootstrap修饰
		document.getAllElements()
				.removeAttr("style")
				.removeAttr("width")
				.removeAttr("height")
				.removeAttr("align")
				.removeAttr("border");
		document.getElementsByTag("table")//把所有的表格全都加上Bootstrap响应式表格
				.addClass("ui")
				.addClass("table")
				.addClass("segment");
//				.wrap("<div class='table-responsive'></div>");

		Elements imgs = document.getElementsByTag("img");
		for (Element one : imgs) {
			one.addClass("ui").addClass("image");//把所有的图片全都加上Bootstrap响应式图片类
			//图片的URL处理
			String url = one.attr("src");
			if (!url.contains("http://")) {
				url = baseURL + url;
			}
			one.attr("src", url);
		}

		return document;
	}

	/**
	 * 判断该文章是否符合是重要通知的要求
	 * 看该文章的标题是否含有筛选关键字FilterString[],如果有就认为该文章符合要求
	 *
	 * @param link 一篇文章的链接
	 * @return
	 */
	public static boolean isNotice(String title) {
		//判断该文章是否符合是重要通知的要求
		for (String filter : FilterString) {//看该文章的标题是否含有筛选关键字,如果有就认为该文章符合要求
			if (title.contains(filter)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得该链接的标题
	 *
	 * @param link
	 * @return
	 */
	public static String getLinkTitle(Element link) {
		//获得文章的标题
		String title = link.attr("title");
		if (title == null || title.trim().length() < 2) {//优先取该链接的title属性作为标题,如果没有该属性就取他的文本属性
			title = link.text();
		}
		return title;
	}

	/**
	 * 把日期格式统一化为yyyy-mm-dd
	 *
	 * @param dateStr
	 * @return
	 */
	private static String parseDateStr(String dateStr) {
		char[] all = dateStr.toCharArray();
		for (int i = 0; i < all.length; i++) {
			char c = dateStr.charAt(i);
			if ((c < '0' || c > '9')) {
				all[i] = '-';
			}
		}
		int flagEnd = 0;
		int flagBegin = 0;
		for (int i = 0; i < dateStr.length(); i++) {
			char c = dateStr.charAt(i);
			if ((c >= '0' && c <= '9')) {
				flagBegin = i;
				break;
			}
		}
		for (int i = all.length - 1; i > 0; i--) {
			char c = dateStr.charAt(i);
			if ((c >= '0' && c <= '9')) {
				flagEnd = i;
				break;
			}
		}
		String re = new String(all);
		re = re.substring(flagBegin, flagEnd + 1);
		re = re.replace(" ", "");
		String three[] = re.split("-", 3);
		if (three[1].length() == 1) {
			three[1] = "0" + three[1];
		}
		if (three[2].length() == 1) {
			three[2] = "0" + three[2];
		}
		re = three[0] + "-" + three[1] + "-" + three[2];
		String all_[] = re.split("-");
		if (all.length > 3) {
			re = all_[0] + "-" + all_[1] + "-" + all_[2];
		}
		three = re.split("-", 3);
		if (three[1].length() == 1) {
			three[1] = "0" + three[1];
		}
		if (three[2].length() == 1) {
			three[2] = "0" + three[2];
		}
		re = three[0] + "-" + three[1] + "-" + three[2];
		return re;
	}

	/**
	 * 全部扫描一次,并把结果反应到数据库中
	 * //TODO 添加到自动更新
	 */
	public static void update() {
		System.out.println("重要通知开始扫描");
		add_NotSame(get0());//ok
		add_NotSame(get1());//ok
		add_NotSame(get2());//ok
		add_NotSame(get3());//ok
		add_NotSame(get4());//ok
		add_NotSame(get5());//ok
		add_NotSame(get6());//ok
		add_NotSame(get7());//ok
		add_NotSame(get8());//ok
		add_NotSame(get9());//ok
		add_NotSame(get10());//ok
		add_NotSame(get11());//ok
		add_NotSame(get12());//ok
		System.out.println("重要通知扫描结束");
	}

	////////////////////////////////////////////////////////////

	/**
	 * 来自 http://jwc.ccnu.edu.cn/zytz.aspx
	 * 教学教务,教务处重要通知
	 *
	 * @return 如果获取失败就返回数量为0的List
	 */
	public static List<MyNoticeEntity> get0() {
		String form = FromSiteName[0][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://jwc.ccnu.edu.cn/zytz.aspx").get();
			Elements links = document.getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://jwc.ccnu.edu.cn/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://jwc.ccnu.edu.cn/");

						String dateStr = document.getElementById("date").text().split("：", 2)[1];
						dateStr = parseDateStr(dateStr);

						String content = document.getElementsByClass("newsContent").html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://xgb.ccnu.edu.cn/zz/list.asp?id=34
	 * 学生资助
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get1() {
		String form = FromSiteName[1][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://xgb.ccnu.edu.cn/zz/list.asp?id=34").get();
			Elements links = document.getElementById("main").getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://xgb.ccnu.edu.cn/zz/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://xgb.ccnu.edu.cn/zz/");

						String dateStr = document.getElementById("text_info").text();
						int tempIndex = dateStr.indexOf("日期:");
						dateStr = dateStr.substring(tempIndex + 3, dateStr.indexOf("点", tempIndex) - 2);
						dateStr = parseDateStr(dateStr);

						String content = document.getElementById("text").html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://xgb.ccnu.edu.cn/zz/list.asp?id=213
	 * 学生资助
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get2() {
		String form = FromSiteName[2][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://xgb.ccnu.edu.cn/zz/list.asp?id=213").get();
			Elements links = document.getElementById("main").getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://xgb.ccnu.edu.cn/zz/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://xgb.ccnu.edu.cn/zz/");

						String dateStr = document.getElementById("text_info").text();
						int tempIndex = dateStr.indexOf("日期:");
						dateStr = dateStr.substring(tempIndex + 3, dateStr.indexOf("点", tempIndex) - 2);
						dateStr = parseDateStr(dateStr);

						String content = document.getElementById("text").html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://xgb.ccnu.edu.cn/zz/list.asp?id=492
	 * 奖助学金
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get3() {
		String form = FromSiteName[3][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://xgb.ccnu.edu.cn/zz/list.asp?id=492").get();
			Elements links = document.getElementById("main").getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://xgb.ccnu.edu.cn/zz/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://xgb.ccnu.edu.cn/zz/");

						String dateStr = document.getElementById("text_info").text();
						int tempIndex = dateStr.indexOf("日期:");
						dateStr = dateStr.substring(tempIndex + 3, dateStr.indexOf("点", tempIndex) - 2);
						dateStr = parseDateStr(dateStr);

						String content = document.getElementById("text").html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://xgb.ccnu.edu.cn/zz/list.asp?id=491
	 * 助学贷款
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get4() {
		String form = FromSiteName[4][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://xgb.ccnu.edu.cn/zz/list.asp?id=491").get();
			Elements links = document.getElementById("main").getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://xgb.ccnu.edu.cn/zz/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://xgb.ccnu.edu.cn/zz/");

						String dateStr = document.getElementById("text_info").text();
						int tempIndex = dateStr.indexOf("日期:");
						dateStr = dateStr.substring(tempIndex + 3, dateStr.indexOf("点", tempIndex) - 2);
						dateStr = parseDateStr(dateStr);

						String content = document.getElementById("text").html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://nisc.ccnu.edu.cn/news1.asp
	 * 网络中心
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get5() {
		String form = FromSiteName[5][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://nisc.ccnu.edu.cn/news1.asp").get();
			Elements links = document.getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://nisc.ccnu.edu.cn/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://nisc.ccnu.edu.cn/");

						String dateStr = document.getElementsByClass("p2").first().text();
						int tempIndex = dateStr.indexOf("时间：");
						dateStr = dateStr.substring(tempIndex + 3, dateStr.indexOf("点", tempIndex) - 4);
						dateStr = parseDateStr(dateStr);

						String content = document.getElementsByClass("p3").first().html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://www.ccnu.edu.cn/list.php?catid=62
	 * 华师官网
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get6() {
		String form = FromSiteName[6][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://www.ccnu.edu.cn/list.php?catid=62").get();
			Elements links = document.getElementById("newslist").getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://www.ccnu.edu.cn/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://www.ccnu.edu.cn/");

						String dateStr = document.getElementsByClass("ftsl").first().text();
						int tempIndex = dateStr.indexOf("日期:");
						dateStr = dateStr.substring(tempIndex + 1);
						dateStr = parseDateStr(dateStr);

						String content = document.getElementById("thirdcontend").html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://gsu.ccnu.edu.cn/
	 * 华中师范大学研究生会
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get7() {
		String form = FromSiteName[7][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://gsu.ccnu.edu.cn/").get();
			Elements links = document.getElementsByClass("mainbar").first().getElementsByClass("article").get(1).getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://gsu.ccnu.edu.cn/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://gsu.ccnu.edu.cn/");

						String dateStr = document.getElementById("lblTime").text();
						dateStr = parseDateStr(dateStr);

						String content = document.getElementById("content").html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://houqin.ccnu.edu.cn/index.php?m=content&c=index&a=lists&catid=8
	 * 华中师范大学后勤集团
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get8() {
		String form = FromSiteName[8][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://houqin.ccnu.edu.cn/index.php?m=content&c=index&a=lists&catid=8").get();
			Elements links = document.getElementsByClass("rightContent").first().getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://houqin.ccnu.edu.cn/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://houqin.ccnu.edu.cn/");

						String dateStr = link.nextElementSibling().text();
						dateStr = parseDateStr(dateStr);

						String content = document.getElementsByClass("rightContent").first().html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://ecard.ccnu.edu.cn/newslist.asp?id=12&type=2
	 * 一卡通
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get9() {
		String form = FromSiteName[9][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://ecard.ccnu.edu.cn/newslist.asp?id=12&type=2").get();
			Elements links = document.getElementById("content").getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://ecard.ccnu.edu.cn/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://ecard.ccnu.edu.cn/");

						String dateStr = link.parent().nextElementSibling().text();
						dateStr = parseDateStr(dateStr);

						String content = document.getElementById("textcontent").html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://lib.ccnu.edu.cn/pages/NewsList.aspx?classId=68
	 * 校图书馆
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get10() {
		String form = FromSiteName[10][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://lib.ccnu.edu.cn/pages/NewsList.aspx?classId=68").get();
			Elements links = document.getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://lib.ccnu.edu.cn/");
						orgUrl = "http://lib.ccnu.edu.cn/Pages/Article.aspx?id=" + orgUrl.split("=", 2)[1];
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://lib.ccnu.edu.cn/");

						String dateStr = document.getElementsByClass("newstitle004").first().text();
						dateStr = parseDateStr(dateStr);

						String content = document.getElementsByClass("newscontent003").first().html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://www.guisheng.net/notice/
	 * 学工动态
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get11() {
		String form = FromSiteName[11][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://www.guisheng.net/notice/").get();
			Elements links = document.getElementsByClass("passage_text_content").first().getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					try {
						String orgUrl = checkURL(link, "http://www.guisheng.net/notice/");
						document = Jsoup.connect(orgUrl).get();
						document = semanticUI(document, "http://www.guisheng.net/notice/");

						String dateStr = document.getElementsByClass("passage_text_title_author").first().text();
						int tempIndex = dateStr.indexOf("日期:");
						dateStr = dateStr.substring(tempIndex + 3, dateStr.indexOf("点", tempIndex) - 2);
						dateStr = parseDateStr(dateStr);

						String content = document.getElementsByClass("passage_text_content").first().html();

						re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(form + "ERROR!");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	/**
	 * 来自 http://www.ccnuyouth.com/plus/list.php?tid=14
	 * 华大青年网
	 *
	 * @return
	 */
	public static List<MyNoticeEntity> get12() {
		String form = FromSiteName[12][0];
		LinkedList<MyNoticeEntity> re = new LinkedList<MyNoticeEntity>();
		try {
			Document document = Jsoup.connect("http://www.ccnuyouth.com/plus/list.php?tid=14").get();
			Elements links = document.getElementsByClass("e2").first().getElementsByTag("a");
			for (int i = 0; i < links.size(); i++) {
				Element link = links.get(i);
				String title = getLinkTitle(link);
				if (isNotice(title)) {
					String orgUrl = checkURL(link, "http://www.ccnuyouth.com/");
					document = Jsoup.connect(orgUrl).get();
					document = semanticUI(document, "http://www.ccnuyouth.com/");

					String dateStr = document.getElementById("newsInfo").text();
					int tempIndex = dateStr.indexOf("时间:");
					dateStr = dateStr.substring(tempIndex + 3, dateStr.indexOf("来", tempIndex) - 4);
					dateStr = parseDateStr(dateStr);

					String content = document.getElementsByClass("newsBody").first().html();

					re.add(new MyNoticeEntity(title, dateStr, orgUrl, content, form));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	public static void main(String[] args) {
//		get0();//ok
//		get1();//ok
//		get2();//ok
//		get3();//ok
//		get4();//ok
//		get5();//ok
//		get6();//ok
//		get7();//ok
//		get8();//ok
//		get9();//ok
//		get10();//ok
//		get11();//ok
//		get12();
//		change();//ok
//		System.out.println(get_page_XH(1));//ok
//		System.out.println(getSome(2));//ok
//		System.out.println(getSome(200000));//ok
//		System.out.println(search_page(0,"http://jwc.ccnu.edu.cn/"));
	}

}
