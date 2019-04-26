package com.jcool.dev.travel.bean;

import com.google.gson.Gson;

public class CodeBean {

    /**
     * phone : 15554509193
     * token : 0384n6kdm
     */

    private String phone;
    private String token;

    public static CodeBean objectFromData(String str) {

        return new Gson().fromJson(str, CodeBean.class);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
