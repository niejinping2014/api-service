package xyz.hollysys.api.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务层配置，通过SPRING框架注入属性
 * @author niejinping
 *
 */
public class ServiceConfig {
	// 通道配置参数
	private Map<String,String> CHANNEL_KEY_SET = new HashMap<String,String>();
	
	//　模块配置参数
	private  Map<String,Object> ACT_NAME_MAP = new HashMap<String,Object>();

	public Map<String, String> getCHANNEL_KEY_SET() {
		return CHANNEL_KEY_SET;
	}

	public void setCHANNEL_KEY_SET(Map<String, String> cHANNEL_KEY_SET) {
		CHANNEL_KEY_SET = cHANNEL_KEY_SET;
	}

	public Map<String, Object> getACT_NAME_MAP() {
		return ACT_NAME_MAP;
	}

	public void setACT_NAME_MAP(Map<String, Object> aCT_NAME_MAP) {
		ACT_NAME_MAP = aCT_NAME_MAP;
	}
}
