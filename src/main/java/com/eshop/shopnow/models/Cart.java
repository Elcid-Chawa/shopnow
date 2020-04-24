package com.eshop.shopnow.models;

import javax.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartid;

    @Column
    private Integer quantity;

    //@ManyToOne
    //@JoinColumn(name = "itemid", referencedColumnName = "itemid")
    //private Items items;

    private Integer itemid;
    //@ManyToOne
    //@JoinColumn(name = "userid", referencedColumnName = "userid")
    //private Users users;
    private Integer userid;

    public Cart(){}
}
