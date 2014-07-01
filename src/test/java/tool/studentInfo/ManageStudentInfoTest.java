package tool.studentInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ManageStudentInfoTest {

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGet_XH() throws Exception {

	}

	@Test
	public void testGet_Name() throws Exception {

	}

	@Test
	public void testGet_Birthday() throws Exception {

	}

	@Test
	public void testGet_HighSchool() throws Exception {

	}

	@Test
	public void testGet_Address() throws Exception {

	}

	@Test
	public void testGet_Education() throws Exception {

	}

	@Test
	public void testGet() throws Exception {

	}

	@Test
	public void testDownloadFromJWC() throws Exception {
		StudentInfoEntity studentInfoEntity= ManageStudentInfo.DownloadFromJWC("2012210817","930820");
		System.out.println(studentInfoEntity);
	}
}