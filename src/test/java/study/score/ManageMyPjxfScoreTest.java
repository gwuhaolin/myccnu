package study.score;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ManageMyPjxfScoreTest {

    /**
     * 一些同一个年级的同一个院的同学的学号
     */
    String XHS[] = {"2012210817", "2012210818", "2012210819"};

    @Test
    public void testList() throws Exception {
        for (String xh : XHS) {
            List<MyPjxfScoreEntity> re = ManageMyPjxfScore.list(xh);
            Assert.assertTrue(re.size() > 0);
        }
    }

    @Test
    public void testUpdate() throws Exception {
        for (String xh : XHS) {
            ManageMyPjxfScore.update(xh);
        }
    }

}
