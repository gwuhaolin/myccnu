package study.CET;

import org.junit.Test;

public class ManageCETTest {

    @Test
    public void testSpider() throws Exception {
        ManageCET.spider("4", "360121199310056414", "201312");
    }

    @Test
    public void testGet() throws Exception {
        ManageCET.get("2012210817");
    }
}
