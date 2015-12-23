package xyz.hollysys.api.util;

import java.util.Map;

/**
 * 前端调用信息参数
 * @author sanhao
 *
 *URL：

	请求URL格式如下
	
	http://host/?act=demo&method=demo&timestamp=1234567890&encode=code
	
	URL中需要包含：
	
	1	服务器地址（域名或IP）
	2	模块名（act）
	3	方法名（method）
	4	时间戳（timestamp）
	5	验证码（encode）
 */
public class InvokerInfoHelper {
	public static String getMethod(Map<String,String> params){
		return params.get("method");
	}
	
	public static String getAct(Map<String,String> params){
		return params.get("act");
	}
	
	public static String getClass(Map<String,String> params){
		return params.get("class");
	}
	
	public static String getVer(Map<String,String> params){
		String ver = params.get("ver");
		if(ver != null){
			return ver;
		}else{
			return "1";
		}
	}
}
