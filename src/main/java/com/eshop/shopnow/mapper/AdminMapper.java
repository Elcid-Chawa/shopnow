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
            @Result(property = "password", column = "password")
    })
    Admins findByUsername(String username);

    @Insert("INSERT into ADMINS(adminid, username, firstname, lastname, salt, password) " +
            "VALUES(#{adminid}, #{username}, #{firstname}, #{lastname}, #{salt}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "adminid")
    void createAdmin(Admins admin);

    @Delete("DELETE from USERS where userid=#{userid}")
    void deleteAdmin(int adminid);
}
