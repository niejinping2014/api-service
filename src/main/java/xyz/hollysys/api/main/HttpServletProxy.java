package xyz.hollysys.api.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class HttpServletProxy extends HttpServlet {

  private static final long serialVersionUID = 4382945551447764730L;

  private String targetServlet;

  private HttpServlet proxy;

  public void destroy() {
    super.destroy(); // Just puts "destroy" string in log
    // Put your code here
  }

  public void init() throws ServletException {

    this.targetServlet = getInitParameter("targetServlet");
    getServletBean();
    proxy.init(getServletConfig());
  }

  private void getServletBean() {
    WebApplicationContext wac = WebApplicationContextUtils
        .getRequiredWebApplicationContext(getServletContext());
    this.proxy = (HttpServlet) wac.getBean(targetServlet);

  }
    @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException, RuntimeException {

    proxy.service(request, response);
  }
}