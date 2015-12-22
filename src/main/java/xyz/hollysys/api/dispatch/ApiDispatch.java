package xyz.hollysys.api.dispatch;

import java.lang.reflect.Method;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import xyz.hollysys.api.config.ServiceConfig;
import xyz.hollysys.api.model.ApiResult;
import xyz.hollysys.api.service.ApiResultUtil;
import xyz.hollysys.api.util.InvokerInfoHelper;


/**
 * API接口调度模块，将接收来自于外部的请求，进行内部调度
 * @author sanhao
 *
 */
@Component
public class ApiDispatch {
	private static Logger logger = Logger.getLogger(ApiDispatch.class);
	
	@Autowired
	@Qualifier("serviceConfig")
	ServiceConfig config;
	
	
	@Autowired
	@Qualifier("apiResultUtilImpl")
	private ApiResultUtil apiResultUtil;
	
	/**
	 * 
	 * @param info  : URL的请求行
	 * 
	 * @param headers　:  http协议头部信息
	 * @param body :  json格式的请求参数
	 * @return
	 */
	public ApiResult dispatch(Map<String,String> params,String body){
		ApiResult result = new ApiResult();
		
		
		String actName = InvokerInfoHelper.getAct(params);
		String methodName = InvokerInfoHelper.getMethod(params);
		String version = InvokerInfoHelper.getVer(params);
		
		
		Class<?> class1 = null;  
		try {
			//class1 = Class.forName(ApiConfig.ACT_NAME_MAP.get(actName));
			//class1 = Class.forName(config.getACT_NAME_MAP().get(actName));
			//Object obj= class1.newInstance();
			Object obj = config.getACT_NAME_MAP().get(actName);
			
			// 检查对象
			if(null == obj){
				logger.error(String.format("act not found.act_name : %s", actName));
				result = apiResultUtil.getApiResult(201);
				
				return result;
			}
			
			class1 = obj.getClass();
			
/*			Method[] methods = class1.getMethods();
			
			for(Method item : methods){
				System.out.println(item.getName() + ":" + item.getParameterTypes());

			}*/
			
			// 这里特殊处理了
			Method method = class1.getMethod(methodName,Class.forName("javax.ws.rs.core.MultivaluedMap"),String.class,String.class);
			
			// 检查接口
			if(method == null){
				logger.error(String.format("method not found.method_name : %s", methodName));
				result = apiResultUtil.getApiResult(202);
				return result;
			}
					
			result = (ApiResult) method.invoke(obj, params,body,version);
			//logger.debug(new Gson().toJson(result));
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			result = apiResultUtil.getApiResult(2000);
			return result;
		}
	}
}
