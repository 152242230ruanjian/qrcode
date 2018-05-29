package com.activity;

import cn.bmob.v3.BmobObject;

/**
 * Created by ji on 2018/5/29.
 */

public class Person extends BmobObject {
    private String name;
    private String address;
    private String password;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void setpassword(String password){ this.password=password;}
    public String getpassword(){
        return password;
    }
}