package tool;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 14-1-17
 * Time: 下午1:42
 */
public class R {

  /**
   * 连接超时时间
   */
  public static final int ConnectTimeout = 10000;//10秒

  /**
   * RESTful API 的JSONP时的callback函数名称
   */
  public static final String JSONP_CALLBACK = "callback";

  /**
   * 分页浏览时每次出现几条
   */
  public static final int ChangeCount = 5;

  /**
   * Jsoup模拟
   */
  public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0";

  /**
   * cookies的保存日期
   */
  public static final int CookiesStoreTime = 3600 * 24 * 30 * 12;//12个月

}
