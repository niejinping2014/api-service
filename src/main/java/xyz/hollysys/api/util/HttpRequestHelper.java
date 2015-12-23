package xyz.hollysys.api.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.util.MultiValueMap;

/**
 * HTTP 请求参数提取,这个不再对外提供使用
 */
public class HttpRequestHelper {
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
		
		return result;
	}
	
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
