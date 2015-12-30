package xyz.hollysys.dao;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import xyz.hollysys.api.dao.UserDAO;
import xyz.hollysys.api.model.User;
import xyz.hollysys.api.model.UserDetail;


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

	@Ignore
	@Test
	public void testUserAccount(){
		User user = dao.getUserById(1);
		
		System.out.println(user.toString());
	}
	//@Ignore
	@Test
	public void testUpdateUserDetail(){
		UserDetail user = new UserDetail();
		
		user.setUser_id(24997);
		user.setUser_default_mobile("15810534204");
		try {
			user.setUser_default_address(new String("西三旗沁春家园".getBytes(),"utf-8"));
			user.setUser_nickname(new String("西三旗平平平静".getBytes(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setUser_nickname("平平平");
		
		dao.updateUserDetail(user);
		
		System.out.println(user.toString());
	}
	
//	@Ignore
	@Test
	public void testgetUserDetail(){
		UserDetail user = dao.getUserDetail(24997);
		
		System.out.println(user.toString());
	}
}

