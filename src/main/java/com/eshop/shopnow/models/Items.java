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
    private Blob itemimage;

    //@ManyToOne
    //@JoinColumn(name = "adminid", referencedColumnName = "adminid")
    //private Admins admin;
    private Integer adminId;
}
