package com.miaodiyun.httpApiDemo;

import com.miaodiyun.httpApiDemo.common.Config;
import com.miaodiyun.httpApiDemo.common.HttpUtil;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	private static String to = "18362926307";
	private static String templateid="114795303";
	private static String param= String.valueOf((int)((Math.random()*9+1)*1000));
	/**
	 * 验证码通知短信
	 */
	public static String execute(String phoneNumber)
	{
		to=phoneNumber;
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&to=" + to + "&templateid=" + templateid
				+ "&param=" +param
				+ HttpUtil.createCommonParam();
		// 提交请求
		String result = HttpUtil.post(url, body);
		System.out.println("result:" + System.lineSeparator() + result);
		return param;
	}
}
