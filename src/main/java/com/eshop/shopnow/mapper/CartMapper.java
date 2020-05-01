package com.eshop.shopnow.mapper;

import com.eshop.shopnow.models.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {
    @Select("SELECT * FROM CART " +
            "WHERE userid=#{userid}")
    @Results({
            @Result(property = "cartid", column = "cartid"),
            @Result(property = "quantity",column = "quantity"),
            @Result(property = "itemid", column = "itemid"),
            @Result(property = "userid", column = "userid")
    })
    List<Cart> findCartByUserId(int userid);

    @Select("SELECT * FROM CART " +
            "WHERE cartid=#{cartid}")
    @Results({
            @Result(property = "cartid", column = "cartid"),
            @Result(property = "quantity",column = "quantity"),
            @Result(property = "itemid", column = "itemid"),
            @Result(property = "userid", column = "userid")
    })
    Cart findCartByCartId(int cartid);

    @Insert("INSERT INTO CART(cartid, quantity, itemid, userid) " +
            "VALUES(#{cartid}, #{quantity}, #{itemid}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "cartid")
    void createCart(Cart cart);

    @Delete("DELETE from CART WHERE cartid=#{cartid}")
    void deleteItem(int cartid);
}
