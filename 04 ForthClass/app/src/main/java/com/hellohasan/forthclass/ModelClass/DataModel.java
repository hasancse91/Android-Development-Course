package com.hellohasan.forthclass.ModelClass;

import com.google.gson.annotations.SerializedName;

public class DataModel {

    @SerializedName("sender_name")
    private String senderName;
    @SerializedName("age")
    private int age;

    public DataModel(String senderName, int age) {
        this.senderName = senderName;
        this.age = age;
    }
}
