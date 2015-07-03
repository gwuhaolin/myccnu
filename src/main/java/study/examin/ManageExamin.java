package study.examin;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tool.ccnu.academy.AcademyEntity;
import tool.ccnu.student.ManageStudents;
import tool.ccnu.student.StudentsEntity;

import java.util.*;

/**
 * Created by wuhaolin on 6/24/14.
 * 期末考试查询
 */
public class ManageExamin {

    public static final HashMap<String, String> Deps = new HashMap<>();

    static {
        Deps.put("公共素质课", "000");
        Deps.put("公共外语", "002");
        Deps.put("公共体育", "003");
        Deps.put("七校联合办学", "004");
        Deps.put("公共教师教育", "005");
        Deps.put("马克思主义学院", "101");
        Deps.put("经济与工商管理学院", "102");
        Deps.put("社会学院", "103");
        Deps.put("教育学院", "104");
        Deps.put("文学院", "105");
        Deps.put("外国语学院", "106");
        Deps.put("理论课部", "001");
        Deps.put("心理学院", "107");
        Deps.put("历史文化学院", "108");
        Deps.put("国际交流学院", "109");
        Deps.put("法学院", "110");
        Deps.put("新闻传播学院", "111");
        Deps.put("教育信息技术学院", "201");
        Deps.put("数学与统计学院", "202");
        Deps.put("物理科学与技术学院", "203");
        Deps.put("化学学院", "204");
        Deps.put("生命科学学院", "205");
        Deps.put("城市与环境科学学院", "206");
        Deps.put("计算机学院", "207");
        Deps.put("信息管理学院", "208");
        Deps.put("公共管理学院", "209");
        Deps.put("国家文化产业研究中心", "300");
        Deps.put("体育学院", "301");
        Deps.put("音乐学院", "302");
        Deps.put("美术学院", "303");
    }

    /**
     * 目标url
     */
    private static final String URL = "http://202.114.37.153:8080/ems/allQueryView.jsp";

    /**
     * 查询
     * 如果学号为空 就查全部年级
     * 如果院系为空就返回公共素质课
     *
     * @param dep   院系名称 公共素质课
     * @param grade 年级2012 2014
     * @return 所有的考试 如果查询失败返回null
     */
    public static List<OneExamin> f(String dep, String grade) {
        if (grade == null) {
            grade = "2014";
        }
        if (!Deps.containsKey(dep)) {
            dep = "公共素质课";
        }
        String depId = Deps.get(dep);
        Connection connection = Jsoup.connect(URL);
        connection.data("depId", depId);//学院id
        connection.data("grade", grade);//年级
        connection.data("examType", "0");//考试类型 所有
        connection.data("selectedTimeId", "-1");//考试时间 所有
        connection.data("query", "true");//查询标识
        List<OneExamin> re = new ArrayList<>(1);
        try {
            Document document = connection.post();
            Elements trs = document.getElementsByTag("table").first().getElementsByTag("tr");

            //第一个用来存储查询参数
            OneExamin oneExamin = new OneExamin();
            oneExamin.setTeacher(dep);
            oneExamin.setName(grade);

            for (int i = 2; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.getElementsByTag("td");
                if (tds.size() == 11) {
                    re.add(oneExamin);
                    oneExamin = new OneExamin();
                    oneExamin.setName(tds.get(1).text());
                    oneExamin.setTeacher(tds.get(5).text());
                    oneExamin.setTime(tds.get(7).text());
                } else if (tds.size() == 4) {
                    oneExamin.getLocations().add(tds.get(1).text());
                }
            }
            re.add(oneExamin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(re);
        return re;
    }

    /**
     * @param XH 同学的学号
     */
    public static List<OneExamin> f(String XH) {
        if (XH != null) {
            StudentsEntity studentInfoEntity = ManageStudents.get(XH);
            if (studentInfoEntity != null) {
                AcademyEntity academyEntity=studentInfoEntity.getAcademyByAcademy();
                if (academyEntity!=null){
                    return f(academyEntity.getAcademyName(), XH.substring(0, 4));
                }
            }
        }
        return f(null, null);
    }
}
