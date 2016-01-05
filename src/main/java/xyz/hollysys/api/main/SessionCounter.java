package xyz.hollysys.api.main;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import xyz.hollysys.api.serviceImpl.PassportServiceImpl;

public class SessionCounter implements HttpSessionListener {
	private static int activeSessions = 0;
	private  static final Logger logger = Logger.getLogger(SessionCounter.class);
	/* Session创建事件 */
	public void sessionCreated(HttpSessionEvent event) {
		ServletContext ctx = event.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute("numSessions");
		if (numSessions == null) {
			numSessions = new Integer(1);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count + 1);
		}
		ctx.setAttribute("numSessions", numSessions);
		
		logger.debug("create numSessions : " + numSessions);
		logger.debug("create session id : " + event.getSession().getId());
		logger.debug("create session life : " + event.getSession().getMaxInactiveInterval());
		
	}

	/* Session失效事件 */
	public void sessionDestroyed(HttpSessionEvent event) {
		ServletContext ctx = event.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute("numSessions");
		if (numSessions == null) {
			numSessions = new Integer(0);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count - 1);
		}
		ctx.setAttribute("numSessions", numSessions);

		logger.debug("destroy numSessions : " + numSessions);
		
		logger.debug("destroy session id : " + event.getSession().getId());
		logger.debug("destroy session life : " + event.getSession().getMaxInactiveInterval());
		logger.debug("destroy session id : " + event.getSession().getLastAccessedTime());
		
	}
}