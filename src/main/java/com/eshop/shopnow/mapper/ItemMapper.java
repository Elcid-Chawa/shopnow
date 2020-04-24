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
            @Result(property = "itemDescription", column = "itemDescription"),
            @Result(property = "price", column = "price"),
            @Result(property = "imageUrl", column = "imageUrl"),
            @Result(property = "adminid", column = "adminid")
    })
    Items findByItemId(int itemid);

    @Select("SELECT * FROM ITEMS")
    @Results({
            @Result(property = "itemid", column = "itemid" ),
            @Result(property = "itemName", column = "itemName"),
            @Result(property = "itemDescription", column = "itemDescription"),
            @Result(property = "price", column = "price"),
            @Result(property = "imageUrl", column = "imageUrl"),
            @Result(property = "adminid", column = "adminid")
    })
    List<Items> findAllItems();

    @Insert("INSERT INTO ITEMS (itemid, itemName, itemDescription, price, imageUrl, adminid) " +
            "VALUES (#{itemid}, #{itemName}, #{itemDescription}, #{price}, #{imageUrl}, #{adminid})")
    @Options(useGeneratedKeys = true, keyProperty = "itemid")
    void createItem(Items items);

    @Update("UPDATE ITEMS SET itemName=#{itemName}, itemDescription=#{itemDescription}, " +
            "price=#{price}, imageUrl= #{imageUrl}, adminid= #{adminid}")
     void updateItem(Items items);

    @Delete("DELETE from ITEMS where itemid=#{itemid}")
    void deleteItem(int itemid);
}
