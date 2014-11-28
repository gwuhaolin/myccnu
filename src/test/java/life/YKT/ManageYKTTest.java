package life.YKT;

import junit.framework.TestCase;
import org.junit.Before;
import tool.R;

import java.util.List;

public class ManageYKTTest extends TestCase {

    private final String XH = "2012210817";
    private final String MM = "930820";
    private final int TIMES = 10;

    @Before
    public void setUp() throws Exception {
        //设置代理
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.proxyHost", "my.ccnuyouth.com");
        System.setProperty("http.proxyPort", "8888");
    }

    public void testGsjg() throws Exception {

    }

    public void testSpiderAndGet() throws Exception {
        MyYktEntity entity = ManageYKT.spiderAndGet(XH, MM);
    }

    public void testGet() throws Exception {
        List<MyYktEntity> list = ManageYKT.get(ManageYKT.Type_State, XH, 0, R.ChangeCount);
    }

}