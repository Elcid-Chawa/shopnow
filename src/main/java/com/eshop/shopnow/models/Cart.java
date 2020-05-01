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

    @ManyToOne
    @JoinColumn(name = "itemid", referencedColumnName = "itemid")
    private Items items;
    public Integer item_id;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private Users users;
    public Integer user_id;
    public Cart(){}

    public void setCartid(Integer cartid) {
        this.cartid = cartid;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setItem(Items item) {
        this.items = item;
    }

    public void setItemid(Integer itemid){
        this.item_id = itemid;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public void setUserid(Integer userid) {
        this.user_id = userid;
    }


    public Integer getCartid() {
        return cartid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Items getItem() {
        return this.items;
    }

    public Integer getItemid() {
        return this.items.getItemid();
    }

    public Integer getUserid() {
        return users.getUserId();
    }
}
