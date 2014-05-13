package study.book;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * MyDate: 1/15/14
 * Time: 10:13 AM
 */

import tool.R;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 在学校的图书馆网站上查询想要借的书的情况
 * http://202.114.34.15/opac/openlink.php
 */
public class SearchBook {
	private static final String URL = "http://202.114.34.15/opac/openlink.php/";

	/**
	 * 如果出现异常返回null,
	 * 没有书返回size=0
	 *
	 * @param want
	 * @param page
	 * @return
	 */
	public static List<MyBook> get(String want, String page) {
		try {
			System.out.println(want + page);
			Connection connection = Jsoup.connect(URL);
			connection.timeout(R.ConnectTimeout);
			connection
					.data("strSearchType", "title")
					.data("match_flag", "forward")
					.data("historyCount", "1")
					.data("doctype", "ALL")
					.data("displaypg", "20")
					.data("showmode", "list")
					.data("sort", "CATA_DATE")
					.data("orderby", "desc")
					.data("dept", "ALL")
					.data("strText", want)
					.data("page", page);//分页查询
			List<MyBook> re = new LinkedList<MyBook>();
			Elements books = connection.get().getElementById("search_book_list").getElementsByTag("li");
			for (int i = 0; i < books.size(); i++) {
				Element li = books.get(i);
				MyBook book = new MyBook();
				book.setTitle(li.getElementsByTag("h3").first().getElementsByTag("a").first().text());
				book.setBookDetailInfoUrl("http://202.114.34.15/opac/" + li.getElementsByTag("h3").first().getElementsByTag("a").attr("href"));
				book.setImgUrl(li.getElementsByTag("img").attr("src"));
				Element p = li.getElementsByTag("p").first();
				Element state = p.getElementsByTag("span").first();
				book.setState(state.text());
				state.remove();
				String temp[] = p.html().split("<br />", 3);
				book.setAuthor(temp[0]);
				book.setPress(temp[1]);
				re.add(book);
			}
			Collections.sort(re);
			return re;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}

	public static class MyBook implements Comparable<MyBook> {
		String title;
		String imgUrl;
		String author;
		String press;
		String state;
		String bookDetailInfoUrl;
		int leaveBookCount;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title.substring(2);
		}

		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getPress() {
			return press;
		}

		public void setPress(String press) {
			this.press = press;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
			this.leaveBookCount = Integer.parseInt(state.substring(state.length() - 1, state.length()));
		}

		public String getBookDetailInfoUrl() {
			return bookDetailInfoUrl;
		}

		public void setBookDetailInfoUrl(String bookDetailInfoUrl) {
			this.bookDetailInfoUrl = bookDetailInfoUrl;
		}

		public int getLeaveBookCount() {
			return leaveBookCount;
		}

		public String getBootStrapBtnType() {
			if (leaveBookCount > 0) {
				return "green";
			} else {
				return "red";
			}
		}

		@Override
		public int compareTo(MyBook o) {
			return o.getLeaveBookCount() - this.leaveBookCount;
		}
	}

	public static void main(String[] args) {
		List<MyBook> books = get("爱", "1");
		System.out.println(books);
	}

}