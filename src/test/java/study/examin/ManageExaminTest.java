package study.examin;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ManageExaminTest {

    @Test
    public void testF() throws Exception {
        List<OneExamin> re = ManageExamin.f("计算机学院", "2013");
        Assert.assertTrue(re.size() > 0);
    }

    @Test
    public void testF2() throws Exception {
        List<OneExamin> re = ManageExamin.f("2012210008");
        Assert.assertTrue(re.size() > 0);
    }
}
