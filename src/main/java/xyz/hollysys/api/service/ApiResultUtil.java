package xyz.hollysys.api.service;

import org.springframework.stereotype.Service;

import xyz.hollysys.api.model.ApiResult;

@Service
public interface ApiResultUtil {
	ApiResult getApiResult(int code);
}
