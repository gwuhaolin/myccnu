package tool;

/**
 * Created by wuhaolin on 7/6/14.
 * :身份验证失败
 * 包括帐号密码错误,信息门户需要验证码
 */
public class ValidateException extends Exception {

	public ValidateException(String message) {
		super(message);
	}
}
