package xyz.hollysys.api.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import xyz.hollysys.api.model.User;
import xyz.hollysys.api.model.UserDetail;

public interface UserDAO {
	@Select("SELECT * FROM `hls_user` WHERE `user_id` = #{user_id}")
	User getUserById(@Param("user_id")long user_id);
	
	@Select("SELECT * FROM `hls_user` WHERE `user_account` = #{user_account}")
	User getUserByAccountId(@Param("user_account")String user_account);
	
	@Select("SELECT * FROM `hls_user` WHERE `user_session` = #{user_session}")
	User getUserBySessionId(@Param("user_session")String user_session);
	
	@Select("SELECT user_id FROM `hls_user` WHERE `user_account` = #{user_session}")
	int getUserIdBySessionId(@Param("user_session")String user_session);
	
	@Insert("INSERT INTO `hls_user`(`user_account`, `user_password`,`user_salt`) VALUES (#{user_account},#{user_password},#{user_salt})")
	int addUser(@Param("user_account")String user_account,@Param("user_password")String user_password,@Param("user_salt")int user_salt);;
	
	@Select("SELECT `user_password` FROM `hls_user` WHERE `user_account`=#{user_account}")
	String getPassword(@Param("user_account")String user_account);
	
	@Update("UPDATE `hls_user` SET `user_session`=#{user_session} WHERE `user_account`=#{user_account}")
	int updateSessioin(@Param("user_account")String user_account,@Param("user_session")String user_session);
	
	@Select("SELECT * FROM `hls_user` WHERE `user_session` = #{user_session}")
	User getUserBySessioinId(@Param("user_session")String user_session);
	
	
	@Update("UPDATE `hls_user` SET `user_password`=#{user_password} WHERE `user_account`=#{user_account}")
	int updatePassword(@Param("user_account")String user_account,@Param("user_password")String user_password);
	
	@Select("SELECT * FROM `hls_user_detail` WHERE `user_id`=#{user_id}")
	UserDetail getUserDetail(@Param("user_id")int user_id);
	
	@Insert("INSERT INTO `hls_user_detail`(`user_id`) VALUE (#{user_id})")
	int insertUserDetail(@Param("user_id")int user_id);
	
//	int updateUserDetail(@Param("user_id")int user_id,UserDetail userdetail);
	int updateUserDetail(UserDetail userdetail);
}
