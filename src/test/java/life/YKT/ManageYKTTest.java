package life.YKT;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ManageYKTTest {

	private final String XH = "2012210817";
	private final String MM = "930820";
	private final int TIMES = 100;

	@Test
	public void testGetRemain() throws Exception {
		for (int i = 0; i < TIMES; i++) {
			MyYktEntity re = ManageYKT.getState(XH, MM);
			System.out.println(re.getRemainMoney());
		}
	}

	@Test
	public void testGetDetail() throws Exception {
		for (int i = 0; i < TIMES; i++) {
			List<MyYktEntity> re = ManageYKT.getDetail(XH, MM);
			Assert.assertTrue(re.size() > 0);
		}
	}

	@Test
	public void testGetHelpMoney() throws Exception {
		for (int i = 0; i < TIMES; i++) {
			List<MyYktEntity> re = ManageYKT.getHelpMoney(XH, MM);
			Assert.assertTrue(re.size() > 0);
		}
	}

	@Test
	public void testGetKaoQin() throws Exception {
		for (int i = 0; i < TIMES; i++) {
			List<MyYktEntity> re = ManageYKT.getKaoQin(XH, MM);
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