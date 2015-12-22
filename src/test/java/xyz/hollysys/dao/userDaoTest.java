package xyz.hollysys.dao;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.hollysys.api.dao.UserDAO;
import xyz.hollysys.api.model.User;


public class userDaoTest {
	private static final Logger logger = Logger.getLogger("D");
	private static UserDAO dao;

	
	@BeforeClass
    public static void before(){                                                                   
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml","classpath:conf/spring-api.xml"});
        dao = (UserDAO) context.getBean("userDAO");        
        if(dao == null){
        	logger.error("dao init error");
        }
    }

	
	@Test
	public void testUserAccount(){
		User user = dao.getUserById(1);
		
		System.out.println(user.toString());
	}
	
}

