package tool.ccnu.student.detailInfo;

import org.junit.Test;

public class ManageStudentAllInfoTest {

    @Test
    public void testDownloadAndStoreToSQLFromJWC() throws Exception {
    }

    @Test
    public void testDownloadAndStoreToSQLFromJWCwhereInfoNull() throws Exception {
        ManageStudentAllInfo.downloadAndStoreToSQLFromJWCwhereInfoNull();
    }

    @Test
    public void testStudentsAllInfoEntityToStudentsEntity() throws Exception {
        int re = ManageStudentAllInfo.downloadAndStoreToSQLFromJWCwhereInfoNull();
    }

    @Test
    public void testParse() throws Exception {

    }
}
