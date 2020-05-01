package com.eshop.shopnow.mapper;

import com.eshop.shopnow.models.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * from USERS " +
            "WHERE username=#{username}")
    @Results({
            @Result(property = "userid", column = "userid"),
            @Result(property = "username", column = "username"),
            @Result(property = "firstname", column = "firstname"),
            @Result(property = "lastname", column = "lastname"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "password", column = "password"),
            @Result(property = "USER_ROLE", column = "USER_ROLE")
    })
    Users findByUsername(String username);

    @Insert("INSERT into USERS(userid, username, firstname, lastname, salt, password, USER_ROLE) " +
            "VALUES(#{userid}, #{username}, #{firstname}, #{lastname}, #{salt}, #{password}, #{USER_ROLE})")
    @Options(useGeneratedKeys = true, keyProperty = "userid")
    void createUser(Users user);

    @Delete("DELETE from USERS where userid=#{userid}")
    void deleteUser(int userid);
}
