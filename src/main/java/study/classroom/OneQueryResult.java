package study.classroom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 * User: WuHaoLin
 * Date: 2014/3/29
 * Time: 19:01
 */

/**
 * 一个查询结果
 * 一个时间段内的空余教室列表
 */
public class OneQueryResult {

  /**
   * 第几节课的时候
   */
  private int diJiJieke;

  /**
   * 该段时间类的 所有的空余的教室的名称
   */
  private final List<String> allClassRoom = new ArrayList<String>();

  public int getDiJiJieke() {
    return diJiJieke;
  }

  public void setDiJiJieke(int diJiJieke) {
    this.diJiJieke = diJiJieke;
  }

  public List<String> getAllClassRoom() {
    return allClassRoom;
  }

  public void addOneClassroom(MyClassroomEntity myclassroomEntity) {
    allClassRoom.add(myclassroomEntity.getClassroom());
  }

  public OneQueryResult(int diJiJieke) {
    this.diJiJieke = diJiJieke;
  }

  public static String dijijiekeToString(int diJiJieke) {
    int temp = (diJiJieke + 3) * 2;
    return temp + ":00-" + (temp + 1) + ":40";
  }
}
