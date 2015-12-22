package xyz.hollysys.api.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import xyz.hollysys.api.model.User;

public interface UserDAO {
	@Select("SELECT * FROM `user` WHERE `user_id` = #{user_id}")
	User getUserById(@Param("user_id")long user_id);
}
