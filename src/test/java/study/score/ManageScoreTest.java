package study.score;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ManageScoreTest {

	String xh = "2012210817";
	String mm = "930820";
	String classNo = "33104901";

	@Test
	public void testGet() throws Exception {
		List<MyScoreEntity> myScoreEntities = ManageScore.get(xh, mm);
		Assert.assertTrue(myScoreEntities.size() > 0);
	}

	@Test
	public void testSaveOrUpdateScores() throws Exception {
		List<MyScoreEntity> myScoreEntities = ManageScore.get(xh, mm);
		ManageScore.saveOrUpdateScores(myScoreEntities);
	}

	@Test
	public void testGet_XH() throws Exception {
		List<MyScoreEntity> myScoreEntities = ManageScore.get_XH(xh);
		Assert.assertTrue(myScoreEntities.size() > 0);
	}

	@Test
	public void testGet_ClassNo() throws Exception {
		List<MyScoreEntity> myScoreEntities = ManageScore.get_ClassNo(classNo);
		Assert.assertTrue(myScoreEntities.size() > 0);
	}
}