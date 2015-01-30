package study.score;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ManageScoreTest {

    final String xh = "2012210817";
    final String mm = "930820";
    final String classNo = "33104901";

    @Test
    public void testGet() throws Exception {
        for (int i = 0; i < 100; i++) {
            List<MyScoreEntity> myScoreEntities = ManageScore.spider(xh, mm);
            Assert.assertTrue(myScoreEntities.size() > 0);
            myScoreEntities = ManageScore.spider("2012210008", "2012210008");
            Assert.assertTrue(myScoreEntities.size() > 0);
            myScoreEntities = ManageScore.spider("2013213766", "190124");
            Assert.assertTrue(myScoreEntities.size() > 0);
            myScoreEntities = ManageScore.spider("2013213760", "666895");
            Assert.assertTrue(myScoreEntities.size() > 0);
            myScoreEntities = ManageScore.spider("2013213756", "081994");
            Assert.assertTrue(myScoreEntities.size() > 0);
            myScoreEntities = ManageScore.spider("2013213752", "004817");
            Assert.assertTrue(myScoreEntities.size() > 0);
        }
    }

    @Test
    public void testGet_XH() throws Exception {
        List<MyScoreEntity> myScoreEntities = ManageScore.query_XH(xh);
        Assert.assertTrue(myScoreEntities.size() > 0);
    }

    @Test
    public void testGet_ClassNo() throws Exception {
        List<MyScoreEntity> myScoreEntities = ManageScore.query_ClassNo(classNo);
        Assert.assertTrue(myScoreEntities.size() > 0);
    }

    @Test
    public void testUpdateStudentsScore() throws Exception {
        int re = ManageScore.updateAllStudentsScore();
        Assert.assertTrue(re > 0);
    }
}
