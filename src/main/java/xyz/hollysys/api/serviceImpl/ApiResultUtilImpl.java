package xyz.hollysys.api.serviceImpl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import xyz.hollysys.api.model.ApiResult;
import xyz.hollysys.api.service.ApiResultUtil;

@Component
public class ApiResultUtilImpl implements ApiResultUtil{
	@Resource(name="Error_Code_Map")
	private Map<Integer,String> ERROR_CODE_MAP;
	
	public void setERROR_CODE_MAP(Map<Integer, String> eRROR_CODE_MAP) {
		ERROR_CODE_MAP = eRROR_CODE_MAP;
	}

	public ApiResult getApiResult(int code){
		ApiResult result = new ApiResult();
		
		if(code == 0){
			result.status = 0;
			result.code = 0;
			result.error = "ok";
			result.data = "";
		}else{
			result.status = 2;
			result.code = code;
			result.error = ERROR_CODE_MAP.get(code);
			result.data = "";  // 这里JSON序列化可能会出问题。需要注意
		}
		
		return result;
	}
	
	public static void main(String[] args){
		ApiResultUtil util = new ApiResultUtilImpl();
		ApiResult result = util.getApiResult(99);
		
		System.out.println(new Gson().toJson(result));
	}
}
