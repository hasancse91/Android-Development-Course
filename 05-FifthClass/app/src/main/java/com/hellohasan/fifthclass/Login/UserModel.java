package com.hellohasan.fifthclass.Login;

import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("user_name") //server side code expect this `key` for JSON parsing
    private String userName;
    @SerializedName("password") //server side code expect this `key` for JSON parsing
    private String password;

    public UserModel(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
