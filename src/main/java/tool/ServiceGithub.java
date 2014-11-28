package tool;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wuhaolin on 11/27/14.
 * :
 */
@Path("/github")
@Produces({"application/json"})
public class ServiceGithub {

    /**
     * 当github上有新的push提交到master时,会执行这个方法
     * 然后系统会调用myccnu命令,重新从github上下载代码编译,重新部署项目
     *
     * @return 系统执行更新的结果
     */
    @POST
    @Path("/newPush")
    public String getSomeByPage() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec("myccnu");
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line, output = "";
            while ((line = reader.readLine()) != null) {
                output += line + "\n";
            }
            return output;
        } catch (IOException e) {
            return "Update error! " + e.getMessage();
        } catch (InterruptedException e) {
            return "Update error! " + e.getMessage();
        }
    }

}
