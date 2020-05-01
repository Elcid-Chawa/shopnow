package com.eshop.shopnow.models;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "ITEMS")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer itemid;


    private String itemName;
    private String itemDescription;
    private Float price;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "adminid", referencedColumnName = "adminid")
    private Admins admin;
    private Integer admin_id;

    public Items(){

    }

    public Items(String itemName, String itemDescription, Float price, String imageUrl){
        setItemName(itemName);
        setItemDescription(itemDescription);
        setPrice(price);
        setImageUrl(imageUrl);
    }

    public void setItemid(Integer itemid){this.itemid = itemid;}
    public void setItemName(String itemName){this.itemName=itemName;}
    public void setItemDescription(String itemDescription){this.itemDescription=itemDescription;}
    public void setPrice(Float price){this.price = price;}
    public void setAdmin(Admins admin){this.admin=admin;}
    public void setImageUrl(String imageUrl){this.imageUrl=imageUrl;}
    public void setAdminid(Integer adminId){this.admin_id=adminId;}

    public Integer getItemid(){return this.itemid;}
    public String getItemName(){return this.itemName;}
    public String getItemDescription(){return this.itemDescription;}
    public Float getPrice(){return this.price;}
    public String getImageUrl(){return this.imageUrl;}

    public Admins getAdmin() {
        return this.admin;
    }

    public Integer getAdminid(){return this.admin_id;}
}
