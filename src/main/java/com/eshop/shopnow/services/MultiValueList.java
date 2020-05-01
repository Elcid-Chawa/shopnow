package com.eshop.shopnow.services;

import com.eshop.shopnow.models.Items;

import java.util.Arrays;
import java.util.List;

public class MultiValueList {
    public static List<Object> getDetails(Items item, Integer id){
       return Arrays.asList(item, id);
    }
}
