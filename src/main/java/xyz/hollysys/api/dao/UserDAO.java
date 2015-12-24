package xyz.hollysys.api.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import xyz.hollysys.api.model.User;

public interface UserDAO {
	@Select("SELECT * FROM `hls_user` WHERE `user_id` = #{user_id}")
	User getUserById(@Param("user_id")long user_id);
	
	@Select("SELECT * FROM `hls_user` WHERE `user_account` = #{user_account}")
	User getUserByAccountId(@Param("user_account")String user_account);
	
	@Insert("INSERT INTO `hls_user`(`user_account`, `user_password`) VALUES (#{user_account},#{user_password})")
	int addUser(@Param("user_account")String user_account,@Param("user_password")String user_password);
	
	@Select("SELECT `user_password` FROM `hls_user` WHERE `user_account`=#{user_account}")
	String getPassword(@Param("user_account")String user_account);
	
	@Update("UPDATE `hls_user` SET `user_session`=#{user_session} WHERE `user_account`=#{user_account}")
	int updateSessioin(@Param("user_account")String user_account,@Param("user_session")String user_session);
	
	@Select("SELECT * FROM `hls_user` WHERE `user_session` = #{user_session}")
	User getUserBySessioinId(@Param("user_session")String user_session);
	
	
	@Update("UPDATE `hls_user` SET `user_password`=#{user_password} WHERE `user_account`=#{user_account}")
	int updatePassword(@Param("user_account")String user_account,@Param("user_password")String user_password);
}
