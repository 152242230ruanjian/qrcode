package com.example.zcz.try__tran_page;

        import cn.bmob.v3.BmobObject;

/**
 * Created by ZCZ on 2018/4/7.
 */

public class Person extends BmobObject {
    private String name;
    private String address;

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
}