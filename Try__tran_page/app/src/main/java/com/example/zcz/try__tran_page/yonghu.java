package com.example.zcz.try__tran_page;

import cn.bmob.v3.BmobObject;

/**
 * Created by ZCZ on 2018/4/15.
 */

public class yonghu extends BmobObject {
    private String name;
    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}