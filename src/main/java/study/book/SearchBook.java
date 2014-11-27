package study.book;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * MyDate: 1/15/14
 * Time: 10:13 AM
 */

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.NetworkException;
import tool.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 在学校的图书馆网站上查询想要借的书的情况
 * http://202.114.34.15/opac/openlink.php
 */
public class SearchBook {
    private static final Logger log = LoggerFactory.getLogger(SearchBook.class);

    private static final String URL = "http://202.114.34.15/opac/openlink.php/";

    /**
     * 搜索图书
     *
     * @param want 搜索关键字
     * @param page 第几页,学校图书查询网站是从1开始计数的
     * @return 查到的书
     * @throws tool.NetworkException
     */
    public static List<MyBook> get(String want, String page) throws NetworkException {
        System.out.println(want + page);
        Connection connection = Jsoup.connect(URL);
        connection.timeout(R.ConnectTimeout);
        connection.userAgent(R.USER_AGENT);
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
        Elements books;
        List<MyBook> re = new LinkedList<>();
        try {
            books = connection.get().getElementById("search_book_list").getElementsByTag("li");
        } catch (Exception e) {
            log.error(Arrays.toString(e.getStackTrace()));
            return re;
        }
        try {
            for (Element li : books) {
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
        } catch (Exception ignored) {
        }
        Collections.sort(re);//按照剩余书量排序
        return re;
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

}
