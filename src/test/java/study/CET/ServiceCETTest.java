package study.CET;

import org.junit.Test;

public class ServiceCETTest {

  @Test
  public void testSpiderAll() throws Exception {
    ServiceCET serviceCET = new ServiceCET();
    int re = serviceCET.spiderAll();
  }
}
