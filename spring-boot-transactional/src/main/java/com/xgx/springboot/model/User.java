package com.xgx.springboot.model;

/**
 * Description: <br/>
 *
 * @author: xgx <br/>
 * date: 2021/4/9 10:52 <br/>
 */
public class User {
    private Integer id;
    private String username;
    private String address;

    public User(Integer id, String username, String address) {
        this.id = id;
        this.username = username;
        this.address = address;
    }

    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
