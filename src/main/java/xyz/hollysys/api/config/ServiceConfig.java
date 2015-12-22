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
	private Map<String,String> KEY_SET = new HashMap<String,String>();
	
	//　模块配置参数
	private  Map<String,Object> ACT_NAME_MAP = new HashMap<String,Object>();

	public Map<String, String> getKEY_SET() {
		return KEY_SET;
	}

	public void setKEY_SET(Map<String, String> kEY_SET) {
		KEY_SET = KEY_SET;
	}

	public Map<String, Object> getACT_NAME_MAP() {
		return ACT_NAME_MAP;
	}

	public void setACT_NAME_MAP(Map<String, Object> aCT_NAME_MAP) {
		ACT_NAME_MAP = aCT_NAME_MAP;
	}
}
