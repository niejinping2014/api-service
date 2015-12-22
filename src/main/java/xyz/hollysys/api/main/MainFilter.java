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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;

import xyz.hollysys.api.dispatch.ApiCheck;
import xyz.hollysys.api.model.ApiResult;
import xyz.hollysys.api.util.HttpRequestHelper;

@Controller
public class MainFilter implements Filter {
	
	@Autowired
	@Qualifier("apiCheck")
	private ApiCheck apiCheck;
	
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter init ok ");
		System.out.println("is null : " + (apiCheck == null));
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		System.out.println(" URI=" + request.getRequestURI());
		long before = System.currentTimeMillis();
		Map<String,String> params = HttpRequestHelper.getParams(request);
		
		System.out.println(params.toString());
		
		ApiResult apiResult = apiCheck.check(params);
		
		// 接口检查成功
		if(apiResult.status == 0){
			chain.doFilter(req, res);
		}else{
			res.setContentType("application/json");
			res.setCharacterEncoding("utf-8");
			
			PrintWriter out = res.getWriter();
			out.println(JSON.toJSONString(apiResult));
			
			System.out.println("api check failed ==> " + JSON.toJSONString(apiResult));
		}
		
		
		long after = System.currentTimeMillis();
		System.out.println( ((HttpServletRequest) request).getRequestURI() + " ==> elapse : " + (after - before));
	}

	public void destroy() {
		System.out.println("Filter end");
	}
}
