package xyz.hollysys.api.service;

import java.util.Map;

import xyz.hollysys.api.model.ApiResult;

/**
 * 账号服务
 * @author sanhao
 *
 */
public interface PassportService {
	ApiResult register(Map<String,String> params,String body);
	
	ApiResult login(Map<String,String> params,String body);
	
	ApiResult logout(Map<String,String> params,String body);
	
	ApiResult forget(Map<String,String> params,String body);
	
	ApiResult reset(Map<String,String> params,String body);
	
	ApiResult edit(Map<String,String> params,String body);
	
	ApiResult userdetail(Map<String,String> params,String body);
}
