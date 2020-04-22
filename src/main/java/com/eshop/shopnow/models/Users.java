package com.eshop.shopnow.models;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    @Column
    private String username;
    private String firstname;
    private String lastname;
    private String salt;
    private String password;
    public Users(){

    }

    public void setUserid(Integer id) {this.userid = id;}
    public void setUsername(String username) {this.username = username;}
    public void setSalt(String salt){this.salt = salt;}
    public void setPassword(String password){this.password=password;}
    public void setFirstname(String firstname){this.firstname=firstname;}
    public void setLastname(String lastname){this.lastname=lastname;}

    public Integer getUserId(){return this.userid;}
    public String getUsername(){return this.username;}
    public String getSalt(){return this.salt;}
    public String getPassword(){return this.password;}
    public String getFirstname(){return this.firstname;}
    public String getLastname(){return this.lastname;}
}
