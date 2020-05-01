package com.eshop.shopnow.models;

import javax.persistence.*;

@Entity
@Table(name = "ADMINS")
public class Admins {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminid;

    @Column
    private String username;
    private String salt;
    private String password;
    private String firstname;
    private String lastname;
    private String USER_ROLE;

    public Admins(){}

    public void setAdminid(Integer adminid){this.adminid=adminid;}
    public void setUsername(String username){this.username=username;}
    public void setSalt(String salt){this.salt = salt;}
    public void setPassword(String password){this.password=password;}
    public void setFirstname(String firstname){this.firstname=firstname;}
    public void setLastname(String lastname){this.lastname=lastname;}

    public void setUSER_ROLE(String USER_ROLE) {
        this.USER_ROLE = USER_ROLE;
    }

    public Integer getAdminid(){return this.adminid;}
    public String getUsername(){return this.username;}
    public String getSalt(){return this.salt;}
    public String getPassword(){return this.password;}
    public String getFirstname(){return this.firstname;}
    public String getLastname(){return this.lastname;}

    public String getUSER_ROLE() {
        return this.USER_ROLE;
    }
}
