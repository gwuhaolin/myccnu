package tool.ccnu;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

public class CCNUInfoTest {

	String xh = "2012210817";
	String mm = "930820";

	@Test
	public void testSpiderStudentInfo() throws Exception {
//		使用charles调试
//		System.setProperty("http.proxyHost", "127.0.0.1");
//		System.setProperty("http.proxyPort", "8888");

		Document document = CCNUInfo.spiderStudentInfo(xh, mm);
		Assert.assertTrue(document != null);
	}

	@Test
	public void testSpiderTeacherInfo() throws Exception {
		Document document = CCNUInfo.spiderTeacherInfo("2007980103", "2007980103");
		Assert.assertTrue(document != null);
	}
}