package com.eshop.shopnow.mapper;

import com.eshop.shopnow.models.Items;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {
    @Select("SELECT * FROM ITEMS " +
            "WHERE itemid =#{itemid}")
    @Results({
            @Result(property = "itemid", column = "itemid" ),
            @Result(property = "itemName", column = "itemName"),
            @Result(property = "ItemDescription", column = "itemDescription"),
            @Result(property = "price", column = "price"),
            @Result(property = "itemImage", column = "itemImage"),
            @Result(property = "adminid", column = "adminId")
    })
    List<Items> findByItemId(int itemid);

    @Insert("INSERT INTO ITEMS (itemid, itemName, itemDescription, price, itemImage, adminid) " +
            "VALUES (#{itemid}, #{itemName}, #{itemDescription}, #{price}, #{itemImage}, #{adminid})")
    @Options(useGeneratedKeys = true, keyProperty = "itemid")
    void createItem(Items items);

    @Update("UPDATE ITEMS SET itemid=#{itemid}, itemName=#{itemName}, itemDescription=#{itemDescription}, " +
            "price=#{price}, itemImage= #{itemImage}, adminid= #{adminid}")
    @Options(useGeneratedKeys = true, keyProperty = "itemid")
    void updateItem(Items items);

    @Delete("DELETE from ITEMS where itemid=#{itemid}")
    void deleteItem(int itemid);
}
