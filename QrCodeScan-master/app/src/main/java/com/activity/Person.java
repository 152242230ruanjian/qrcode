package com.activity;

import cn.bmob.v3.BmobObject;

/**
 * Created by ji on 2018/5/29.
 */

public class Person extends BmobObject {
    private String name;
    public int num;
    public int getnum() {
        return num;
    }
    public void setnum(int num1) {
        this.num = num1;
    }
    private String isadmin;
    public void setisadmin(String name) {
        this.isadmin = name;
    }
    public String getisadmin(){
        return isadmin;
    }
    private String address;
    private String password;
    private String email;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getaddress() {
        return address;
    }
    public void setaddress(String address) {
        this.address = address;
    }

    public void setpassword(String password){ this.password=password;}
    public String getpassword(){
        return password;
    }
    public void setEmail(String email){this.email=email;};
    public String getEmail(){return email;}
}