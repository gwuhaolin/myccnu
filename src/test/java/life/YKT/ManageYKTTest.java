package life.YKT;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ManageYKTTest {

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

    @Test
    public void testGetRemain() throws Exception {
        for (int i = 0; i < TIMES; i++) {
            MyYktEntity re = ManageYKT.spiderState(XH, MM);
            System.out.println(re.getRemainMoney());
        }
    }

    @Test
    public void testGetDetail() throws Exception {
        for (int i = 0; i < TIMES; i++) {
            List<MyYktEntity> re = ManageYKT.spiderDetail(XH, MM);
            System.out.println(re);
            Assert.assertTrue(re.size() > 0);
        }
    }

    @Test
    public void testGetHelpMoney() throws Exception {
        for (int i = 0; i < TIMES; i++) {
            List<MyYktEntity> re = ManageYKT.spiderHelpMoney(XH, MM);
            Assert.assertTrue(re.size() > 0);
        }
    }

    @Test
    public void testGetKaoQin() throws Exception {
        for (int i = 0; i < TIMES; i++) {
            List<MyYktEntity> re = ManageYKT.spiderKaoQin(XH, MM);
            Assert.assertTrue(re.size() > 0);
        }
    }

    @Test
    public void testGetCookies() throws Exception {
        for (int i = 0; i < TIMES; i++) {
            Map<String, String> cookies = ManageYKT.getCookies(XH, MM);
            Assert.assertTrue(cookies.size() > 0);
        }
    }
}
