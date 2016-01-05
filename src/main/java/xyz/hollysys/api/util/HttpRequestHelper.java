package xyz.hollysys.api.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * HTTP 请求参数提取,这个不再对外提供使用
 */
public class HttpRequestHelper {
	private static Logger logger = Logger.getLogger(HttpRequestHelper.class);

	public static Map<String, String> getPathParams(HttpServletRequest request) {
		Map<String, String> result = new HashMap<String, String>();
		String[] parts = request.getRequestURI().split("\\/");
		String[] subparts = null;
		String params = parts[parts.length - 1];

		parts = params.split("&");

		for (String part : parts) {
			subparts = part.split("=");

			if (subparts.length == 2) {
				result.put(subparts[0], subparts[1]);
			}
		}

		return result;
	}

	// 会加入session
	public static Map<String, String> getQueryParams(HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> result = new HashMap<String, String>();
		String params = request.getQueryString();
		String[] parts = params.split("&");
		String[] subparts = null;

		for (String part : parts) {
			subparts = part.split("=");

			if (subparts.length == 2) {
				result.put(subparts[0], subparts[1]);
			}
		}

		String sessionId = "";

		/*
		 * Cookie[] cookies = request.getCookies(); if (cookies != null) { for
		 * (Cookie cook : cookies) { if
		 * ("JSESSIONID".equalsIgnoreCase(cook.getName())) { sessionId =
		 * cook.getValue(); break; } } }
		 */

		HttpSession session = request.getSession();
		sessionId = session.getId();

		// session.setMaxInactiveInterval(-1);
		/*
		 * session.setMaxInactiveInterval(2400); logger.debug(
		 * "session is new : " + session.isNew());
		 * 
		 * if(session.getAttribute("user_session") == null){
		 * session.setAttribute("user_session", sessionId);
		 * 
		 * // 更新session logger.info("session is update : " + sessionId);
		 * logger.info("session life : " + session.getMaxInactiveInterval());
		 * 
		 * }else{ logger.debug("session is  : " + sessionId); }
		 */
/*		Cookie[] cookies = request.getCookies();
		String token = "token";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(token)) {
					// 如果user Cookie存在，进行处理
					sessionId = cookie.getValue();
					logger.info("token id : " + sessionId);
				} else {
					// 首次
					Cookie newcookie = new Cookie(token, sessionId);
					newcookie.setMaxAge(1800);
					newcookie.setPath("/");
					response.addCookie(newcookie);
				
				}
			}
		}else{
			// 首次
			Cookie newcookie = new Cookie(token, sessionId);
			newcookie.setMaxAge(1800);
			newcookie.setPath("/");
			response.addCookie(newcookie);
		}*/
		
		if (result.containsKey("user_session") == false) {
			result.put("user_session", sessionId);
		}
		return result;
	}

	/**
	 * 注意：这个接口只能调用一次，第二次调用，会返回null.国为流的特性是这样:读完就没了.
	 * 
	 * @param request
	 * @return
	 */
	public static String getBody(HttpServletRequest request) {
		String result = null;

		try {
			result = IOUtils.toString(request.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return result;
	}
	
	public static String getUserSession(String body){
		return getBodyItem(body,"user_session");
	}
	
	public static String getBodyItem(String body,String key){
		String value;
		value = body.substring(1, body.length()-1);           //remove curly brackets
		
	//	System.out.println(value);
		
		String[] keyValuePairs = value.split(",");    //split the string to creat key-value pairs
		Map<String,String> map = new HashMap<String,String>();               

//		System.out.println(keyValuePairs.length);
		
		for(String pair : keyValuePairs)                        //iterate over the pairs
		{
		    String[] entry = pair.split(":");                   //split the pairs to get key and value 
		    
/*			System.out.println(entry.length);
			System.out.println(entry[0]);
			System.out.println(entry[1]);*/
		    
		    map.put(entry[0].trim().replace("\"", ""), entry[1].trim().replace("\"", ""));          //add them to the hashmap and trim whitespaces
		}
		
		if(map.containsKey(key) == false){
			return null;
		}
		
		return map.get(key);
	}
	
	public static void main(String[] args){
		String body = "{\"user_session\":\"747A6F664A407D21EE949056BACF2EC1\"}";
		System.out.println(getUserSession(body));
	}
}
