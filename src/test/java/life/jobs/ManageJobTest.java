package life.jobs;

import org.junit.Test;

public class ManageJobTest {

    @Test
    public void testScanPartTimeJobs() throws Exception {
        ManageJob.scanPartTimeJobs();
    }

    @Test
    public void testScanPrivateTeacher() throws Exception {
        ManageJob.scanPrivateTeacher();
    }
}
