package xyz.hollysys.api.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * HTTP 请求参数提取,这个不再对外提供使用
 */
public class HttpRequestHelper {
	private static Logger logger = Logger.getLogger(HttpRequestHelper.class);
		
	public static Map<String,String> getPathParams(HttpServletRequest request){
		Map<String,String> result = new HashMap<String,String>();
		String[] parts =  request.getRequestURI().split("\\/");
		String[] subparts = null;
		String params = parts[parts.length - 1];
		
		parts = params.split("&");
		
		for(String part : parts){
			subparts = part.split("=");
			
			if(subparts.length == 2){
				result.put(subparts[0], subparts[1]);
			}
		}
		
		return result;
	}
	
	// 会加入session
	public static Map<String, String> getQueryParams(HttpServletRequest request){
		Map<String,String> result = new HashMap<String,String>();
		String params = request.getQueryString();
		String[] parts  = params.split("&");
		String[] subparts = null;
		
		for(String part : parts){
			subparts = part.split("=");
			
			if(subparts.length == 2){
				result.put(subparts[0], subparts[1]);
			}
		}
		
		
		String sessionId = "";
		
/*		 Cookie[] cookies = request.getCookies();
         if (cookies != null)
         {
             for (Cookie cook : cookies)
             {
                 if ("JSESSIONID".equalsIgnoreCase(cook.getName()))
                 {
                	 sessionId = cook.getValue();
                     break;
                 }
             }
         }*/
         
         HttpSession session = request.getSession();
         sessionId = session.getId();
         
         logger.debug("session is new : " + session.isNew());
         
         if(session.getAttribute("user_session") == null){
        	 session.setAttribute("user_session", sessionId);
        	 
        	 // 更新session
        	 logger.info("session is update : " + sessionId);
         }else{
        	 logger.debug("session is  : " + sessionId);
         }
         
         result.put("user_session", sessionId);
		
		 return result;
	}
	
	/**
	 * 注意：这个接口只能调用一次，第二次调用，会返回null.国为流的特性是这样:读完就没了.
	 * @param request
	 * @return
	 */
	public static String getBody(HttpServletRequest request){
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
}
