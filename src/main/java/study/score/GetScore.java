package study.score;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 1/26/14
 * Time: 10:26 PM
 */

import tool.CCNUJWC;
import tool.R;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 从教务处网站获得成绩
 */
public class GetScore {

	/**
	 * 查成绩
	 */
	public static final String URL_CJ = "http://jwc.ccnu.edu.cn/stuScore.aspx";

	/**
	 * 查询成绩,获得这学期的最新的成绩结果
	 * 查询结果按照总分大小排序
	 *
	 * @param XH 学号
	 * @param MM 密码
	 * @return 成绩的HTML, 如果密码错误抛出IndexOutOfBoundsException
	 * 如果网络错误或学校服务器错误抛出IOException
	 */
	public static List<MyScore> get(String XH, String MM) throws Exception{
		Map<String,String> cookies = null;
		try {
			cookies = CCNUJWC.getCookie(XH, MM);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("帐号密码错误!");
		}
		Connection connection = Jsoup.connect(URL_CJ);
		connection.timeout(R.ConnectTimeout);
		connection.cookies(cookies);
		Document document = null;
		try {
			document = connection.post();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("学校教务处服务器繁忙!");
		}
		try {
			String __VIEWSTATE = document.getElementById("__VIEWSTATE").attr("value");//奇怪的验证数据
			String __EVENTVALIDATION = document.getElementById("__EVENTVALIDATION").attr("value");//奇怪的验证数据
			String time = document.getElementById("DropDownList1").child(0).text();//获得最新的成绩所代表的时间
			//设置数据
			connection.data("DropDownList1", time);
			connection.data("DropDownList2", time);
			connection.data("__VIEWSTATE", __VIEWSTATE);
			connection.data("__EVENTVALIDATION", __EVENTVALIDATION);
			connection.data("Button1", "查询");
		}catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			throw new IOException("对不起,目前不能查询成绩!");
		}

		//获得数据
		try {
			document = connection.post();
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("学校教务处服务器繁忙!");
		}
		//解析数据
		try {
			Elements tr = document.getElementById("GridView1").getElementsByTag("tr");
			List<MyScore> re = new LinkedList<MyScore>();
			for (int i = 1; i < tr.size(); i++) {
				re.add(new MyScore(tr.get(i)));
			}
			Collections.sort(re);
			return re;
		}catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			throw new IOException("帐号密码错误!");
		}
	}

	/**
	 * 代表一门成绩的所有数据指标
	 */
	public static class MyScore implements Comparable<MyScore> {
		/**
		 * 课程名称
		 */
		String name;

		/**
		 * 总评
		 */
		float sumScore;

		/**
		 * 平时
		 */
		float PSScore;

		/**
		 * 期末
		 */
		float QMScore;

		/**
		 * 学分
		 */
		float credit;

		public MyScore(String name, float sumScore, float PSScore, float QMScore, int credit) {
			this.name = name;//课程名称
			this.sumScore = sumScore;//总评
			this.PSScore = PSScore;//平时
			this.QMScore = QMScore;//期末
			this.credit = credit;//学分
		}

		/**
		 * 把HTML里的tr解析成一门成绩的详细信息
		 *
		 * @param tr
		 */
		public MyScore(Element tr) {
			Elements tds = tr.children();
			this.name = tds.get(1).text();
			this.sumScore = Float.parseFloat(tds.get(7).text());
			this.PSScore = Float.parseFloat(tds.get(5).text());
			this.QMScore = Float.parseFloat(tds.get(6).text());
			this.credit = Float.parseFloat(tds.get(8).text());
		}

		/**
		 * 根据该门成绩的最后总分来返回BootStrap里的panel的类型来使网页对不同的成绩呈现出不同的颜色
		 *
		 * @return
		 */
		public String getBootStrapPanelType() {
			if (sumScore < 60) {
				return "panel-danger";
			} else if (sumScore < 65) {
				return "panel-warning";
			} else if (sumScore < 80) {
				return "panel-info";
			} else {
				return "panel-success";
			}
		}

		/**
		 * 根据该门成绩的最后总分来返回BootStrap里的图标的类型的名称来使网页对不同的成绩呈现出不同的图标
		 *
		 * @return
		 */
		public String getBootStrapIconName() {
			if (sumScore < 60) {
				return "glyphicon-remove";
			} else {
				return "glyphicon-ok";
			}
		}

		@Override
		public int compareTo(MyScore o) {
			if (this.sumScore > o.sumScore) {
				return 1;
			} else {
				return -1;
			}
		}

		public String getName() {
			return name;
		}

		public float getSumScore() {
			return sumScore;
		}

		public float getPSScore() {
			return PSScore;
		}

		public float getQMScore() {
			return QMScore;
		}

		public float getCredit() {
			return credit;
		}
	}

	public static void main(String[] args) {
		try {
			get("2012210817", "9308201");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
