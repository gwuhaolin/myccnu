package tool;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CCNUJWCTest {

	String xh = "2012210817";
	String mm = "930820";
	int time = 50;

	@Test
	public void testGetCookie_True() throws Exception {
		for (int i = 0; i < time; i++) {
			System.out.println(i);
			Map<String, String> cookies = CCNUJWC.getCookie(xh, mm);
			Assert.assertTrue(cookies.size() > 0);
		}
	}

}