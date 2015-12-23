package xyz.hollysys.api.serviceImpl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import xyz.hollysys.api.dao.UserDAO;
import xyz.hollysys.api.dispatch.ApiDispatch;
import xyz.hollysys.api.model.ApiResult;
import xyz.hollysys.api.model.User;
import xyz.hollysys.api.service.ApiResultUtil;
import xyz.hollysys.api.service.PassportService;

@Component("passportService")
public class PassportServiceImpl implements PassportService {
	private static final Logger logger = Logger.getLogger(ApiDispatch.class);
	@Autowired
	@Qualifier("apiResultUtilImpl")
	private ApiResultUtil apiResultUtil;
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;
	
	public ApiResult register(Map<String, String> params, String body) {
		ApiResult result = apiResultUtil.getApiResult(201);
		logger.info("用户注册啦");
		
		RegisterUser regUser = JSON.parseObject(body, RegisterUser.class);
		
		if(null == userDAO.getUserByAccountId(regUser.name)){
			User user = new User();
			user.setUser_account(regUser.name);
			user.setPassword(regUser.password);
			
			userDAO.addUser(user.getUser_account(), user.getPassword());
			
			logger.info("用户注册成功  : " + user.getUser_account());
			
		}else{
			logger.error("用户已注册 :" + regUser.toString());
			result =  apiResultUtil.getApiResult(1001);
		}
		
		
		return result;
	}

	public ApiResult login(Map<String, String> params, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	public ApiResult logout(Map<String, String> params, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	public ApiResult forget(Map<String, String> params, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	public class RegisterUser{
		public String name;
		public String password;
		@Override
		public String toString() {
			return "RegisterUser [name=" + name + ", password=" + password + "]";
		}
	}

	public ApiResult edit(Map<String, String> params, String body) {
		// TODO Auto-generated method stub
		return null;
	}
}
