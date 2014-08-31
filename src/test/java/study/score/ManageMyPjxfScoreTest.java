package study.score;

import org.junit.Assert;
import org.junit.Test;
import tool.ccnu.student.ManageStudents;

public class ManageMyPjxfScoreTest {

  /**
   * 一些同一个年级的同一个院的同学的学号
   */
  String XHS[] = {"2012210817", "2012210818", "2012210819"};

  @Test
  public void testGet() throws Exception {
    for (String xh : XHS) {
      MyPjxfScoreEntity myPjxfScoreEntity = ManageMyPjxfScore.get(xh);
      Assert.assertTrue(myPjxfScoreEntity.getXh().equals(xh));
      Assert.assertTrue(myPjxfScoreEntity.getScore() > 0);
      Assert.assertTrue(myPjxfScoreEntity.getAcademy() == ManageStudents.get(xh).getAcademyByAcademy().getId());
    }
  }

  @Test
  public void testList() throws Exception {
    for (String xh : XHS) {
      Assert.assertTrue(ManageMyPjxfScore.list(xh).size() > 0);
    }
  }

  @Test
  public void testUpdate() throws Exception {
    for (String xh : XHS) {
      ManageMyPjxfScore.update(xh);
    }
  }

}
