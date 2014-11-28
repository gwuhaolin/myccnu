package tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2/24/14
 * Time: 7:58 PM
 */
public class Tool {

    private static final Logger log = LoggerFactory.getLogger("严重意外异常");

    private static final Calendar calendar = Calendar.getInstance();
    /**
     * 今天是星期几
     */
    private static int Week_NOW = calendar.get(Calendar.DAY_OF_WEEK);

    /**
     * 年年年年-月月-日日 的格式的日期格式器
     */
    public static final SimpleDateFormat DateFormat_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 年年年年-月月-日日的现在的时间
     */
    private static String nowDate = DateFormat_YYYY_MM_DD.format(new Date(System.currentTimeMillis()));
    private static final SimpleDateFormat DateFormat_YYYY_MM_DD_HHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    static {
        calendar.setTime(new Date(System.currentTimeMillis()));
        Week_NOW = calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 更新当前系统时间
     * TODO
     */
    public static void update() {
        System.out.println("更新系统时间");
        nowDate = DateFormat_YYYY_MM_DD.format(new Date(System.currentTimeMillis()));

        //今天是星期几
        calendar.setTime(new Date(System.currentTimeMillis()));
        Week_NOW = calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得今天的时间格式为 yyyy-mm-dd
     */
    public static String time_YYYY_MM_DD() {
        return nowDate;
    }

    /**
     * 获得系统现在时间 获取准确的现在系统的时间 yyyy-MM-dd HH:mm
     */
    public static String time_YYYY_MM_DD_HH_MM_NOW() {
        return DateFormat_YYYY_MM_DD_HHMM.format(new Date(System.currentTimeMillis()));
    }


    /**
     * 获得今天是星期几
     */
    public static int week_NOW() {
        return Week_NOW;
    }

    /**
     * 该请求的cookies中如果有XH就返回XH,如果没有就返回""放在第0个
     * 该请求的cookies中如果有MM就返回MM,如果没有就返回""放在第1个
     * 如果cookies中没有该cookies的话就返回"",而不是null
     */
    public static String[] getXHMMfromCookie(HttpServletRequest request) {
        String XHMM[] = {"", ""};
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return XHMM;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("XH") && cookie.getValue() != null) {
                XHMM[0] = cookie.getValue();
            } else if (cookie.getName().equals("MM") && cookie.getValue() != null) {
                XHMM[1] = cookie.getValue();
            }
        }
        return XHMM;
    }

    /**
     * 判读从cookies中获得的帐号密码是否符合格式并没有判断是否正确
     */
    public static boolean XHMMisOK(String XHMM[]) {
        return XHMM.length == 2 && XHMM[0] != null && XHMM[0].length() > 0 && XHMM[1] != null && XHMM[1].length() > 0;
    }

    /**
     * 把该学号密码写到cookies(更目录)中保存一月(共享Cookie)
     */
    public static void setXHMMtoCookies(HttpServletResponse response, String XH, String MM) {
        Cookie cookieXH = new Cookie("XH", XH);
        cookieXH.setPath("/");
        cookieXH.setMaxAge(R.CookiesStoreTime);
        Cookie cookieMM = new Cookie("MM", MM);
        cookieMM.setPath("/");
        cookieMM.setMaxAge(R.CookiesStoreTime);
        response.addCookie(cookieXH);
        response.addCookie(cookieMM);
    }

    /**
     * 用于直接向html加载js函数然后依次执行一条条函数
     * 向jsp页面输出js函数,只需要传入函数体名称和(参数,参数)不要加;号
     *
     * @param args 一条条要执行的函数
     */
    public static void jspWriteJSForHTML(HttpServletResponse response, String... args) {
        StringBuilder re = new StringBuilder(
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "\t<meta charset=\"utf-8\"/>\n" +
                        "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"/>\n" +
                        "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\">\n" +
                        "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/lib/css/semantic.min.css\">\n" +
                        "\t<link rel=\"stylesheet\" type=\"text/css\" href=\"/lib/css/main.css\">\n" +
                        "\t<script src=\"/lib/js/jquery.min.js\"></script>\n" +
                        "\t<script src=\"/lib/js/semantic.min.js\"></script>\n" +
                        "\t<script src=\"/lib/js/main.js\"></script>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<script>\n"
        );
        for (String oneF : args) {
            re.append(oneF + ";\n");
        }
        re.append("</script>\n" +
                "</body>\n" +
                "</html>");
        try {
            Writer writer = response.getWriter();
            writer.write(re.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于直接向html页面中输出执行shouldBind(msg)
     * 使页面直接跳转到绑定界面
     *
     * @param msg 需要向用户传达的信息
     */
    public static void jspWriteJSForHTML_shouldBind(HttpServletResponse response, String msg) {
        jspWriteJSForHTML(response, "shouldBind('" + msg + "')");
    }

    /**
     * 生成AJAX加载更多JS 不包含<scritp></scritp>标签
     *
     * @param targetURL 目标URL
     * @param params    传入的参数,如果没有就传入null
     *                  如果有先加一个,然后dataName:dateValue,dataName:dateValue
     * @return js
     */
    public static String makeAJAXLoadMoreJS(String targetURL, Map<String, Object> params) {
        String datas = "";
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> param : params.entrySet()) {
                datas += "," + param.getKey() + ":'" + param.getValue() + "'";
            }
        }
        return "var ajaxBegin=0;\n" +
                "function ajaxMore(btn) {\n" +
                "$(btn).addClass('loading');\n" +
                "ajaxBegin += " + R.ChangeCount + ";\n" +
                "$.ajax({\n" +
                "type: 'POST',\n" +
                "url: '" + targetURL + "',\n" +
                "data: { begin: ajaxBegin" + datas + "},\n" +
                "contentType: \"application/x-www-form-urlencoded; charset=utf-8\"\n" +
                "}).done(function (data) {\n" +
                "$(btn).removeClass('loading');\n" +
                "if (data.length < 20) {\n" +
                "$(btn).text(\"没有更多了!\");\n" +
                "$(btn).addClass('disabled');\n" +
                "} else {\n" +
                "$(btn).parent().before(data);\n" +
                "$(btn).text(\"更多\");\n" +
                "}\n" +
                "});\n" +
                "}";
    }

    /**
     * 生成加载更多按钮的HTML
     *
     * @param onclick 在按钮onclick时要执行的js语句
     *                注意,在传入js语句时如果js语句有参数,参数要用'包围,不要用"
     * @return HTML
     */
    public static String makeAjaxLoadMoreBtnHtml(String... onclick) {
        String js = "";
        for (String s : onclick) {
            js += s + ";";
        }
        return "<div class='column'>\n" +
                "        <button class='ui button fluid circular loadMoreBtn' onclick=\"ajaxMore(this);" + js + "\">MORE" +
                "        </button>\n" +
                "</div>";
    }

    /**
     * 清除字符串的头尾的中文空格和英文空格
     */
    public static String trim(String string) {
        char[] value = string.toCharArray();
        int len = value.length;
        int st = 0;

        while ((st < len) && ((value[st] <= ' ') || value[st] == 160)) {
            st++;
        }
        while ((st < len) && ((value[len - 1] <= ' ') || value[len - 1] == 160)) {
            len--;
        }
        return ((st > 0) || (len < value.length)) ? string.substring(st, len) : string;
    }

    /**
     * 以严重错误的方式记下一条异常日志
     *
     * @param e 异常
     */
    public static void log(Throwable e) {
        if (e != null) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

}

