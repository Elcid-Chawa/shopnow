package com.eshop.shopnow.mapper;

import com.eshop.shopnow.models.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * from USERS " +
            "WHERE userid=#{usersid}")
    @Results({
            @Result(property = "userid", column = "userid"),
            @Result(property = "username", column = "username"),
            @Result(property = "firstname", column = "firstname"),
            @Result(property = "lastname", column = "lastname"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "password", column = "password")
    })
    List<Users> findByUserId(int userid);
}
