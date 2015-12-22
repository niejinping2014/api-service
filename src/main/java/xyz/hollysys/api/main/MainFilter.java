package xyz.hollysys.api.main;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MainFilter implements Filter {
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter ");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		System.out.println("���� URI=" + request.getRequestURI());
		long before = System.currentTimeMillis();

		
		chain.doFilter(req, res);
		long after = System.currentTimeMillis();
		System.out.println(" ���󱻶�λ��" + ((HttpServletRequest) request).getRequestURI() + "���ʱ��Ϊ: " + (after - before));
	}

	public void destroy() {
		System.out.println("Filter ����");
	}
}
