package tool.ccnu.student;

import org.junit.Assert;
import org.junit.Test;
import tool.HibernateUtil;
import tool.ccnu.CCNUPortal;
import tool.ccnu.academy.AcademyEntity;
import tool.ccnu.academy.ManageAcademy;

import java.util.List;

public class ManageStudentsTest {

  private final String xh = "2012210817";
  private final String name = "吴浩麟";
  private final Integer sex = 1;
  private final String password = "930820";
  private final String phoneNumber = "15623007796";
  private final String qq = "569230199";
  private final String dormitory = "东16栋412";
  private final String idNumber = "360121199310056414";
  private final AcademyEntity academyByAcademy = ManageAcademy.get(1);
  private final StudentsEntity STUDENT = new StudentsEntity(xh, name, sex, password, phoneNumber, qq, dormitory, idNumber, academyByAcademy);

  @Test
  public void testAdd() throws Exception {
    HibernateUtil.addOrUpdateEntity(STUDENT);
  }

  @Test
  public void testGet() throws Exception {

    StudentsEntity studentsEntity = ManageStudents.get(xh);

    assertAllPro(studentsEntity);

    studentsEntity = ManageStudents.get("");
    Assert.assertNull(studentsEntity);
  }

  @Test
  public void testGet_IdNumber() throws Exception {

    StudentsEntity studentsEntity = ManageStudents.get_IdNumber(idNumber);
    assertAllPro(studentsEntity);

    studentsEntity = ManageStudents.get("");
    Assert.assertNull(studentsEntity);
  }

  @Test
  public void testGet_phoneNumber() throws Exception {

    StudentsEntity studentsEntity = ManageStudents.get_phoneNumber(phoneNumber);
    assertAllPro(studentsEntity);

    studentsEntity = ManageStudents.get("");
    Assert.assertNull(studentsEntity);
  }

  @Test
  public void testGet_QQ() throws Exception {

    StudentsEntity studentsEntity = ManageStudents.get_QQ(qq);
    assertAllPro(studentsEntity);

    studentsEntity = ManageStudents.get("");
    Assert.assertNull(studentsEntity);
  }

  @Test
  public void testGet_Name() throws Exception {
    List<StudentsEntity> re = ManageStudents.get_Name(name);
    Assert.assertTrue(re.size() > 0);
  }

  @Test
  public void testGet_Academy() throws Exception {
    List<StudentsEntity> re = ManageStudents.get_Academy(academyByAcademy.getId());
    Assert.assertTrue(re.size() > 0);
  }

  private void assertAllPro(StudentsEntity studentsEntity) {
    Assert.assertEquals(studentsEntity.getXh(), xh);
    Assert.assertEquals(studentsEntity.getName(), name);
    Assert.assertEquals(studentsEntity.getIdNumber(), idNumber);
    Assert.assertEquals(studentsEntity.getAcademyByAcademy(), academyByAcademy);
    Assert.assertEquals(studentsEntity.getDormitory(), dormitory);
    Assert.assertEquals(studentsEntity.getPhoneNumber(), phoneNumber);
    Assert.assertEquals(studentsEntity.getPassword(), password);
    Assert.assertEquals(studentsEntity.getQq(), qq);
  }

  @Test
  public void testScanPassword() throws Exception {
    int start = 2014210001;
    int end = 2014214000;
    String[] pass = new String[]{"123456"};
    int re = CCNUPortal.scanPassword(start, end, pass);
    System.out.println(re);
  }

  @Test
  public void testUpdatePasswordToSQL() throws Exception {
    ManageStudents.update_PasswordToSQL(xh, "123456");
  }
}
