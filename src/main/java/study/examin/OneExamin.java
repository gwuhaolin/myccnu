package study.examin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhaolin on 6/24/14.
 * 一门考试
 */
public class OneExamin implements Comparable<OneExamin> {
    private String name;
    private String teacher;
    private String time = "";
    private List<String> locations = new ArrayList<>();

    public OneExamin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    @Override
    public int compareTo(OneExamin o) {
        return this.getTime().compareTo(o.getTime());
    }

    /**
     * 获得所有的考试地点
     */
    public String locations() {
        if (locations.size() < 1) {
            return "学院安排";
        }
        String re = "";
        for (int i = 0; i < locations.size() - 1; i++) {
            re += locations.get(i) + " , ";
        }
        re += locations.get(locations.size() - 1);
        return re;
    }
}
