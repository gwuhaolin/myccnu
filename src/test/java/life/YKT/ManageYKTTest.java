package life.YKT;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ManageYKTTest {

	String XH = "2012210817";
	String MM = "930820";
	int TIMES = 100;

	@Test
	public void testGetRemain() throws Exception {
		for (int i = 0; i < TIMES; i++) {
			String re = ManageYKT.getRemain(XH, MM);
			System.out.println(re);
		}
	}

	@Test
	public void testGetDetail() throws Exception {
		for (int i = 0; i < TIMES; i++) {
			List<OneChange> re = ManageYKT.getDetail(XH, MM);
			Assert.assertTrue(re.size() > 0);
		}
	}

	@Test
	public void testGetHelpMoney() throws Exception {
		for (int i = 0; i < TIMES; i++) {
			List<OneChange> re = ManageYKT.getHelpMoney(XH, MM);
			Assert.assertTrue(re.size() > 0);
		}
	}

	@Test
	public void testGetKaoQin() throws Exception {
		for (int i = 0; i < TIMES; i++) {
			List<OneChange> re = ManageYKT.getKaoQin(XH, MM);
			Assert.assertTrue(re.size() > 0);
		}
	}

	@Test
	public void testGsjg() throws Exception {

	}

	@Test
	public void testGetCookies() throws Exception {
		for (int i = 0; i < TIMES; i++) {
			Map<String, String> cookies = ManageYKT.getCookies(XH, MM);
			Assert.assertTrue(cookies.size() > 0);
		}
	}
}