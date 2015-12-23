package xyz.hollysys.api.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import xyz.hollysys.api.dispatch.ApiCheck;
import xyz.hollysys.api.model.ApiResult;
import xyz.hollysys.api.util.HttpRequestHelper;

@Service("mainFilter")
public class MainFilter implements Filter {
	private static final Logger logger = Logger.getLogger(MainFilter.class);
	
	@Autowired
	@Qualifier("apiCheck")
	private ApiCheck apiCheck;

	// FOR DI
/*	public void setApiCheck(ApiCheck apiCheck) {
		this.apiCheck = apiCheck;
	}*/
	
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("Filter init ok ");
		logger.info("is null : " + (apiCheck == null));
		System.out.println("Filter init ok ");
		System.out.println("is null : " + (apiCheck == null));
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		logger.info(" URI=" + request.getRequestURI());
		long before = System.currentTimeMillis();
		Map<String, String> params = HttpRequestHelper.getQueryParams(request);
		String body = HttpRequestHelper.getBody(request);

		logger.info(params.toString());
		logger.info(body);
		
		if (apiCheck != null) {
			ApiResult apiResult = apiCheck.check(params);

			// 接口检查成功
			if (apiResult.status == 0) {
				chain.doFilter(req, res);
			} else {
				res.setContentType("application/json");
				res.setCharacterEncoding("utf-8");

				PrintWriter out = res.getWriter();
				out.println(JSON.toJSONString(apiResult));

				logger.info("api check failed ==> " + JSON.toJSONString(apiResult));
			}
		}else{
			logger.info("apiCheck is null " );
		}
		
		long after = System.currentTimeMillis();
		logger.info(((HttpServletRequest) request).getRequestURI() + " ==> elapse : " + (after - before));
		System.out.println(((HttpServletRequest) request).getRequestURI() + " ==> elapse : " + (after - before));
	}

	public void destroy() {
		logger.info("Filter end");
	}
}
