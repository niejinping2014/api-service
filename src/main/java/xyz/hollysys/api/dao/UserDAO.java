package xyz.hollysys.api.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import xyz.hollysys.api.model.User;

public interface UserDAO {
	@Select("SELECT * FROM `user` WHERE `user_id` = #{user_id}")
	User getUserById(@Param("user_id")long user_id);
	
	@Select("SELECT * FROM `user` WHERE `user_account` = #{user_account}")
	User getUserByAccountId(@Param("user_account")String user_account);
	
	@Insert("INSERT INTO user set")
	int addUser(@Param("user_account")String user_account,@Param("user_password")String user_password);
}
