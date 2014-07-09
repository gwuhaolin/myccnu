package tool.ccnu;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

public class CCNUInfoTest {

	String xh = "2012210817";
	String mm = "930820";

	@Test
	public void testSpiderStudentInfo() throws Exception {
		Document document = CCNUInfo.spiderStudentInfo(xh, mm);
		Assert.assertTrue(document != null);
	}

	@Test
	public void testSpiderTeacherInfo() throws Exception {

	}
}