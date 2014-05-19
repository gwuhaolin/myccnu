package life.YKT;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 14-1-28
 * Time: 下午5:43
 */

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import tool.CCNUPortal;
import tool.R;
import tool.Tool;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 查询一卡通信息（余额）
 */
public class ManageYKT {

	/**
	 * 获得一卡通余额,和现在的卡的使用状态
	 * @param XH
	 * @param MM
	 * @return 返回一个2个元素的数组第一个是余额的字符串形式,第二个是该一卡通是否在用
	 */
	public static Object[] getRemain(String XH, String MM) throws Exception{
		Connection connection= Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/cardinfo");
		Object re[]=new Object[2];
		try {
			connection.cookies(getCookies(XH,MM));
			connection.timeout(R.ConnectTimeout*10);
			Document document= connection.get();
			Elements tds= document.getElementsByClass("baseTable").first().getElementsByTag("tr").last().getElementsByTag("td");
			re[0]= tds.last().text();
			re[1]=tds.get(5).text().equals("在用");
			return re;
		} catch (Exception e) {
//			e.printStackTrace();
			throw new Exception("学校一卡通服务器繁忙");
		}
	}

	/**
	 * 获得一卡通编号
	 * @param XH
	 * @param MM
	 * @return 如果获取失败返回 ""
	 */
	private static String getCardID(String XH, String MM) throws Exception{
		Connection connection= Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/cardinfo");
		try {
			connection.cookies(getCookies(XH,MM));
			connection.timeout(R.ConnectTimeout*10);
			Document document= connection.get();
			return document.getElementsByClass("baseTable").first().getElementsByTag("tr").last().getElementsByTag("td").first().text();
		} catch (Exception e) {
//			e.printStackTrace();
			throw new Exception("学校一卡通服务器繁忙");
		}
	}

	/**
	 * 获得一卡通从今天前4天的消费明细
	 * @param XH
	 * @param MM
	 * @return 如果查询失败返回null
	 */
	public static List<OneChange> getDetail(String XH,String MM) throws Exception{
		Connection connection=Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/deal");
		try {
			connection.cookies(getCookies(XH,MM));
			connection.data("start_date", getBeforeDate(4));//起始时间
			connection.data("end_date", Tool.time_YYYY_MM_DD());//结束时间
			connection.timeout(R.ConnectTimeout*10);
			Document document= connection.post();
			Elements trs= document.getElementsByClass("baseTable").first().getElementsByTag("tr");
			List<OneChange> re=new LinkedList<OneChange>();
			//prase
			for (int i=1;i<trs.size();i++){
				try {
					Elements tds=trs.get(i).getElementsByTag("td");
					String date=tds.get(1).text();
					String location=tds.get(2).text();
					String changeMoney=tds.get(4).text();
					String remainMoney=tds.get(5).text();
					OneChange oneChange=new OneChange();
					oneChange.setTime(date);
					oneChange.setLocation(location);
					oneChange.setChangeMoney(changeMoney);
					oneChange.setRemainMoney(remainMoney);
					re.add(oneChange);
				}catch (Exception e){
//					e.printStackTrace();
					continue;
				}
			}
			return re;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("学校一卡通服务器繁忙");
		}
	}

	/**
	 * 获得一卡通从2013-01-01开始到现在的补助明细
	 * @param XH
	 * @param MM
	 * @return
	 */
	public static List<OneChange> getHelpMoney(String XH,String MM) throws Exception{
		Connection connection=Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/allowance");
		try {
			connection.cookies(getCookies(XH,MM));
			String curDate= Tool.time_YYYY_MM_DD();
			connection.data("start_date",curDate.split("-")[0]+"-01-01");//起始时间
			connection.data("end_date",curDate);//结束时间
			connection.timeout(R.ConnectTimeout*10);
			Document document= connection.post();
			Elements trs= document.getElementsByClass("baseTable").first().getElementsByTag("tr");
			List<OneChange> re=new LinkedList<OneChange>();
			//prase
			for (int i=1;i<trs.size();i++){
				try {
					Elements tds=trs.get(i).getElementsByTag("td");
					String date=tds.get(3).text();
					String location=tds.get(4).text();
					String changeMoney=tds.get(5).text();
					String remainMoney=tds.get(6).text();
					OneChange oneChange=new OneChange();
					oneChange.setTime(date);
					oneChange.setLocation(location);
					oneChange.setChangeMoney(changeMoney);
					oneChange.setRemainMoney(remainMoney);
					re.add(oneChange);
				}catch (Exception e){
//					e.printStackTrace();
					continue;
				}
			}
			return re;
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("学校一卡通服务器繁忙");
		}
	}

	/**
	 * 获得最近一个星期内的考勤
	 * @param XH
	 * @param MM
	 * @return
	 * @throws Exception
	 */
	public static List<OneChange> getKaoQin(String XH,String MM) throws Exception{
		Connection connection=Jsoup.connect("http://192.168.44.7:10000/sisms/index.php/person/timer");
		try {
			connection.cookies(getCookies(XH,MM));
			String curDate= Tool.time_YYYY_MM_DD();
			connection.data("start_date",Tool.DateFormat_YYYY_MM_DD.format(new Date(System.currentTimeMillis()-604800000)));//起始时间,一周前
			connection.data("end_date",curDate);//结束时间
			connection.timeout(R.ConnectTimeout*10);
			Document document= connection.post();
			Elements trs= document.getElementsByClass("baseTable").first().getElementsByTag("tr");
			List<OneChange> re=new LinkedList<OneChange>();
			//prase
			for (int i=1;i<trs.size();i++){
				try {
					Elements tds=trs.get(i).getElementsByTag("td");
					String date=tds.get(1).text();
					String location=tds.get(2).text();
					OneChange oneChange=new OneChange();
					oneChange.setTime(date);
					oneChange.setLocation(location);
					re.add(oneChange);
				}catch (Exception e){
//					e.printStackTrace();
					continue;
				}
			}
			return re;
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("学校一卡通服务器繁忙");
		}
	}

	/**
	 * 挂失解挂
	 * TODO
	 * @param XH
	 * @param MM
	 * @return
	 */
	public static String gsjg(String XH,String MM,String yktMM,boolean isJG) throws Exception{
		String url="http://192.168.44.7:10000/sisms/index.php/person/guashi";
		if (isJG){
			url="http://192.168.44.7:10000/sisms/index.php/person/jiegua";
		}
		Connection connection=Jsoup.connect(url);
		try {
			connection.cookies(getCookies(XH,MM));
			connection.timeout(R.ConnectTimeout);
			connection.data("password",yktMM);
			connection.data("cardid",getCardID(XH,MM));
			Document document= connection.post();
			return document.toString();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("学校一卡通服务器繁忙");
		}
	}

	/**
	 * 获得cookie
	 * @param XH
	 * @param MM
	 * @return
	 */
	public static Map<String,String> getCookies(String XH,String MM) throws Exception{
		Map<String,String> CCNUPORATLcookies= CCNUPortal.getCookie(XH, MM);
		Connection connection= Jsoup.connect("http://portal.ccnu.edu.cn/roamingAction.do?appId=ECARD");
		connection.cookies(CCNUPORATLcookies);
		connection.timeout(R.ConnectTimeout);
		try {
			connection.get();
		} catch (IOException e) {
			throw new Exception("学校信息门户服务器繁忙");
		}
		return connection.response().cookies();
	}

	/**
	 * 获得yyyy-MM-dd格式的系统当前时间day天前的时间
	 * @return
	 */
	private static String getBeforeDate(int day){
		Date reDate=new Date(System.currentTimeMillis()-day*3600000*24);
		return Tool.DateFormat_YYYY_MM_DD.format(reDate);
	}

	public static void main(String[] args) {
//		System.out.println(getRemain("2012210817", "930820"));
		Date fromDate=new Date(System.currentTimeMillis());
		fromDate.setYear(fromDate.getYear() - 1);
		System.out.println(fromDate);
	}

}
