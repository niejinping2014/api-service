package xyz.hollysys.api.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ����������
 * @author sanhao
 *
 */
public class MainServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*	 1. ��ÿͻ�����Ϣ
    getRequestURL�������ؿͻ��˷�������ʱ������URL��
    getRequestURI���������������е���Դ�����֡�
    getQueryString ���������������еĲ������֡�
    getRemoteAddr�������ط�������Ŀͻ�����IP��ַ 
    getRemoteHost�������ط�������Ŀͻ���������������
    getRemotePort�������ؿͻ�����ʹ�õ�����˿ں�
    getLocalAddr��������WEB��������IP��ַ��
    getLocalName��������WEB�������������� 
    getMethod�õ��ͻ�������ʽ
 2.��ÿͻ�������ͷ 
    getHeader(string name)���� 
    getHeaders(String name)���� 
    getHeaderNames���� 

 3. ��ÿͻ����������(�ͻ����ύ������)
    getParameter(name)����
    getParameterValues��String name������
    getParameterNames���� 
    getParameterMap����*/
 public void doPost(HttpServletRequest request,
         HttpServletResponse response)
 throws ServletException, IOException{
	StringBuilder sb = new StringBuilder();
	
	sb.append("characterencoding : ");
	sb.append(request.getCharacterEncoding()); 
	
	sb.append("\nauthtype:");
	sb.append(request.getAuthType());
	
	sb.append("\ngetContentLength : ");
	sb.append(request.getContentLength());
	
	sb.append("\ngetContextPath : ");
	sb.append(request.getContextPath());
	
	sb.append("\ngetLocalAddr:");
	sb.append(request.getLocalAddr());
	
	sb.append("\ngetLocalName");
	sb.append(request.getLocalName());
	
	sb.append("\n requesturl : ");
	sb.append(request.getRequestURL().toString());
	
	sb.append("\n requestURI :");
	sb.append(request.getRequestURI());
	
	sb.append("\n requestQueryString : ");
	sb.append(request.getQueryString());
	
	sb.append("\n q1 =");
	sb.append(request.getParameter("q1"));
	
	sb.append("\n q4 =");
	sb.append(request.getParameter("q4"));
	
	sb.append("\n remotehost");
	sb.append(request.getRemoteHost());
	
	sb.append("\n method :");
	sb.append(request.getMethod());
	
	sb.append("\n header : h1 = ");
	sb.append(request.getHeader("h1"));
	
	sb.append("\n queryMap : ");
	//�õ�����Ĳ���Map��ע��map��value��String��������  
	Map map = request.getParameterMap();
	Set<String> keySet = map.keySet();
	for (String key : keySet) {
		String[] values = (String[]) map.get(key);
		for (String value : values) {
			sb.append(key + "=" + value + " \t");
		}
	}
	
	for (String key : keySet) {
		String[] values = (String[]) map.get(key);
		sb.append("key : \t");
		sb.append(key);
		
		for (String value : values) {
			sb.append( value + " \t");
		}
	}
	
	sb.append("\n getSession : session = ");
	HttpSession session = request.getSession();
	sb.append(session.getId());
	Enumeration en = session.getAttributeNames();
	
	while(en.hasMoreElements()){
		sb.append("\n");
		sb.append(en.nextElement());
	}
	
	session.setAttribute("name", "Njp");
	
	sb.append("\n cookie : ");
	
	for(Cookie item : request.getCookies()){
		sb.append(item.getName() + " _ " +  item.getValue());
		sb.append("\n");
	}
	
	// ������Ӧ��������
	response.setContentType("text/html");
	response.setCharacterEncoding("utf-8");
	
	// ʵ�ʵ��߼���������
	PrintWriter out = response.getWriter();
	out.println(sb.toString());
	}
	
}
