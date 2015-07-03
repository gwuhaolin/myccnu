package study.book;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MyLibTest {

    String xh = "2012210817";
    String pass = "9308201";

    @Test
    public void testGet() throws Exception {
        List<MyLib.MyLibBook> re = MyLib.get(xh, pass);
        Assert.assertTrue(re.size() > 0);
    }

}
