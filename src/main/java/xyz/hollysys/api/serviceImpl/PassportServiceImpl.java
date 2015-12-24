package xyz.hollysys.api.serviceImpl;

import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import xyz.hollysys.api.dao.UserDAO;
import xyz.hollysys.api.model.ApiResult;
import xyz.hollysys.api.model.RegisterUser;
import xyz.hollysys.api.model.User;
import xyz.hollysys.api.service.ApiResultUtil;
import xyz.hollysys.api.service.PassportService;
import xyz.hollysys.api.util.EncodeHelper;

@Component("passportService")
public class PassportServiceImpl implements PassportService {
	private static final Logger logger = Logger.getLogger(PassportServiceImpl.class);
	@Autowired
	@Qualifier("apiResultUtilImpl")
	private ApiResultUtil apiResultUtil;
	
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;
	
	// 用户注册
	public ApiResult register(Map<String, String> params, String body) {
		ApiResult result = apiResultUtil.getApiResult(201);
		int salt = new Random().nextInt(100000) + 3;
		
		logger.info("用户注册啦 用户信息：" + body);
		
		RegisterUser regUser = JSON.parseObject(body, RegisterUser.class);
		
		if(null == userDAO.getUserByAccountId(regUser.account)){
			User user = new User();
			user.setUser_account(regUser.account);
			user.setUser_password(regUser.password);
			
			userDAO.addUser(user.getUser_account(), EncodeHelper.getPassword(regUser.account,regUser.password,salt),salt);
			
			logger.info("用户注册成功  : " + user.getUser_account());
			
			result.status = 0;
			result.code = 0;
			result.error = "注册成功";
			
		}else{
			logger.error("用户已注册 :" + regUser.toString());
			result =  apiResultUtil.getApiResult(1001);
		}
		
		
		return result;
	}

	// 用户登陆
	public ApiResult login(Map<String, String> params, String body) {
		ApiResult result = apiResultUtil.getApiResult(201);
		logger.info("用户登陆：" );
		
		RegisterUser regUser = JSON.parseObject(body, RegisterUser.class);
		
		User user = userDAO.getUserByAccountId(regUser.account);
		
		if(user == null){
			logger.error("用户登陆失败 usr :" + regUser.account);
			
			result = apiResultUtil.getApiResult(1002);
			return result;
		}
		
		String password = userDAO.getPassword(regUser.account);
		String sessionid = "";
		String pass = EncodeHelper.getPassword(regUser.account, regUser.password, user.getUser_salt());
				
		if(pass.equals(password)){
			logger.info("用户登陆成功 usr : " + regUser.account);
			
			// 绑定session
			sessionid = params.get("user_session");
			
			if(sessionid.isEmpty() == false){
				userDAO.updateSessioin(regUser.account,sessionid);
				logger.info("用户会话成功");
			}else{
				logger.error("用户会话失败 usr: " + regUser.account);
			}
			
			result.code = 0;
			result.status = 0;
			result.error = "登陆成功";
		}else{
			logger.error("用户登陆失败 usr :" + regUser.account);
			
			result = apiResultUtil.getApiResult(1002);
		}
		
		
		return result;
	}

	public ApiResult logout(Map<String, String> params, String body) {
		ApiResult result = apiResultUtil.getApiResult(201);
		User user = new User();
		String sessionid = "";

		sessionid = params.get("user_session");
		
		if(sessionid.isEmpty() == false){
			user = userDAO.getUserBySessioinId(sessionid);
		
			if(user != null){
				// 清空会话信息
				userDAO.updateSessioin(user.getUser_account(),"");
				
				result.code = 0;
				result.status = 0;
				result.error = "退出成功";
			}else{
				logger.error("无效会话标识");
				result = apiResultUtil.getApiResult(1003);
			}
		}else{
			logger.error("无效会话标识" );
			
			result = apiResultUtil.getApiResult(1003);
		}
		
		return result;
	}

	public ApiResult forget(Map<String, String> params, String body) {
		ApiResult result = apiResultUtil.getApiResult(201);
		logger.info("用户登陆：");
		
		RegisterUser regUser = JSON.parseObject(body, RegisterUser.class);
		
		User user = userDAO.getUserByAccountId(regUser.account);
		
		if(user == null){
			logger.error("用户信息失败 usr :" + regUser.account);
			
			result = apiResultUtil.getApiResult(1002);
			return result;
		}
		
		String password = userDAO.getPassword(regUser.account);
		String sessionid = "";
		String pass = EncodeHelper.getPassword(regUser.account, regUser.password, user.getUser_salt());
		
		if(pass.equals(password)){
			logger.info("用户登陆成功 usr : " + regUser.account);
			
			// 绑定session
			sessionid = params.get("user_session");
			
			if(sessionid.isEmpty() == false){
				userDAO.updateSessioin(regUser.account,sessionid);
				logger.info("用户会话成功");
			}else{
				logger.error("用户会话失败 usr: " + regUser.account);
			}
			
			result.code = 0;
			result.status = 0;
			result.error = "登陆成功";
		}else{
			logger.error("用户登陆失败 usr :" + regUser.account);
			
			result = apiResultUtil.getApiResult(1002);
		}
		
		
		return result;
	}

	public ApiResult edit(Map<String, String> params, String body) {
		// TODO Auto-generated method stub
		return null;
	}

	// 重围密码,重新修改密码后,需要重登陆,再绑定会话关系
	public ApiResult reset(Map<String, String> params, String body) {
		ApiResult result = apiResultUtil.getApiResult(201);
		logger.info("用户修改密码：");
		
		RegisterUser regUser = JSON.parseObject(body, RegisterUser.class);
		User user = userDAO.getUserByAccountId(regUser.account);
		
		if(user == null){
			logger.error("用户信息失败 usr :" + regUser.account);
			
			result = apiResultUtil.getApiResult(1002);
			return result;
		}
		
		String password = userDAO.getPassword(regUser.account);
		String sessionid = "";
		String pass = EncodeHelper.getPassword(regUser.account, regUser.password, user.getUser_salt());

		if(pass.equals(password)){
			
			userDAO.updatePassword(regUser.account, EncodeHelper.getPassword(regUser.account, regUser.newpassword, user.getUser_salt()));
			userDAO.updateSessioin(regUser.account,sessionid);
			
			logger.info("重置密码成功 usr: " + regUser.account);
			result.code = 0;
			result.status = 0;
			result.error = "修改密码成功";
		}else{
			logger.error("用户密码错误 :" + regUser.account);
			
			result = apiResultUtil.getApiResult(1004);
		}
		
		return result;
	}
}
