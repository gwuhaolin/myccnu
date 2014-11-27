package tool;

import java.io.IOException;

/**
 * Created by wuhaolin on 7/6/14.
 * :学校服务器发送异常
 * 包括 网络异常,找不到Host,主机关闭
 * 也包括 学校服务器发生异常
 */
public class NetworkException extends IOException {

    public NetworkException(String message) {
        super(message);
    }

}
