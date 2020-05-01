package com.eshop.shopnow.mapper;

import com.eshop.shopnow.models.Admins;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminMapper {
    @Select("SELECT * from ADMINS " +
            "WHERE username=#{username}")
    @Results({
            @Result(property = "adminid", column = "adminid"),
            @Result(property = "username", column = "username"),
            @Result(property = "firstname", column = "firstname"),
            @Result(property = "lastname", column = "lastname"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "password", column = "password"),
            @Result(property = "USER_ROLE", column = "USER_ROLE")
    })
    Admins findByUsername(String username);

    @Insert("INSERT into ADMINS(adminid, username, firstname, lastname, salt, password, USER_ROLE) " +
            "VALUES(#{adminid}, #{username}, #{firstname}, #{lastname}, #{salt}, #{password}, #{USER_ROLE})")
    @Options(useGeneratedKeys = true, keyProperty = "adminid")
    void createAdmin(Admins admin);

    @Delete("DELETE from USERS where adminid=#{adiminid}")
    void deleteAdmin(int adminid);
}
