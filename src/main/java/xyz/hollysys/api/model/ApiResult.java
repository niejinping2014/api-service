package xyz.hollysys.api.model;

/**
 * 返回结果
 * @author sanhao
 *
 返回规范
	格式：
		status 请求状态
		code 错误码
		error 错误信息
		data 返回数据
 */

public class ApiResult {
	public int status;
	public int code;
	public String error;
	public Object data;
	
	public ApiResult(){
		this.status = 0;
		this.code = 0;
		this.error = "ok";
		this.data = new Object();
	}

	@Override
	public String toString() {
		return "ApiResult [status=" + status + ", code=" + code + ", error=" + error + ", data=" + data + "]";
	}
}
