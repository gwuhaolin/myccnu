package play.shudong;


import tool.ServiceQiNiu;

public class ServiceQiNiuTest {

    @org.junit.Test
    public void testGetUpToken() throws Exception {
        ServiceQiNiu.removeOne("http://myccnushop.qiniudn.com/o_18o1u471ipq1p3h1ifs123s16ld7.jpeg");
    }
}
