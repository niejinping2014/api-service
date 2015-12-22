package xyz.hollysys.api.dispatch;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import xyz.hollysys.api.config.ServiceConfig;
import xyz.hollysys.api.model.ApiResult;
import xyz.hollysys.api.service.ApiResultUtil;

/*
 * API服务接口检查模块
 */
public class ApiCheck {
	private static final Logger logger = Logger.getLogger(ApiCheck.class);
	private Set<String> CHECK_PARAM_LIST; // 检查参数列表，必须要有act,method,rpc-serv
	private String enable_key;            // 万能开关
	
	@Autowired
	@Qualifier("apiResultUtilImpl")
	private ApiResultUtil apiResultUtil;
	
	@Autowired
	@Qualifier("serviceConfig")
	private ServiceConfig config;
	

	
	// DI使用
	public void setCHECK_PARAM_LIST(Set<String> cHECK_PARAM_LIST) {
		CHECK_PARAM_LIST = cHECK_PARAM_LIST;
	}
	
	// DI使用
	public void setApiResultUtil(ApiResultUtil apiResultUtil) {
		this.apiResultUtil = apiResultUtil;
	}
	
	// DI使用
	public void setEnable_key(String enable_key) {
		this.enable_key = enable_key;
	}

	public ApiResult check(Map<String,String> inputs){
		String key_src = null;
		String key_dest = null;
		String rpcserver = null;
		TreeMap<String,String> params = new TreeMap<String,String>();
		
		for(String key : inputs.keySet()){
			logger.debug("key : " + key + " ==> value : " + inputs.get(key) );
		}
		
		// 获取查询参数，并对查询参数进行排序，以key为关键字，升序排序
		SortedSet<String> sort  = new TreeSet<String>();		
		
		for(String key : inputs.keySet()){
			sort.add(key);
		}
			
		Iterator<String> it = sort.iterator();
		while(it.hasNext()){
			String key = it.next();
			
			if(!key.equals("encode")){
				params.put(key, inputs.get(key));
			}else{
				key_src = inputs.get(key);
			}
		}
		
		// 检查查询参数
		if(sort.containsAll(CHECK_PARAM_LIST)){
			logger.error("check query parameters ok...");
		}else{
			logger.debug(CHECK_PARAM_LIST.toString());
			logger.debug("source request params : " + sort.toString());
			logger.error("check query parameters error...");
			return apiResultUtil.getApiResult(200);
		}
		
		// 调试模式下，直接通过
		if(key_src.equals(enable_key)){
			logger.error(" ======================================= debug mode : check ok ============================");
			return apiResultUtil.getApiResult(0);
		}
		
		// 检查key-id
		//if(hs.get("channel-id") == null){
		if(inputs.get("keyid") == null){
			//logger.error("channel-id is not exists...");
			logger.error("keyid is not exists...");
			return apiResultUtil.getApiResult(203);
		}else{
			rpcserver = inputs.get("keyid");		
			logger.debug("keyid: " + rpcserver);
		}
		
	//	key_dest = ApiHelper.getEncode(params, config.getCHANNEL_KEY_SET().get(rpcserver));
		
		if(key_src.equals(key_dest)){
			logger.error(" ======================================= channel id check ok ============================");
			return apiResultUtil.getApiResult(0);
		}else{
			logger.error(" *************************** key check failed ********************");
			return apiResultUtil.getApiResult(99);
		}
	}
}
