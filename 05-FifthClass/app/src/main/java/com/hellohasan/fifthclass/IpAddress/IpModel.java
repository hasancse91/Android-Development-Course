package com.hellohasan.fifthclass.IpAddress;

import com.google.gson.annotations.SerializedName;

public class IpModel{
    @SerializedName("ip")
    private String ip;

    public IpModel(String ip) {
        this.ip = ip;
    }

    public IpModel() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
