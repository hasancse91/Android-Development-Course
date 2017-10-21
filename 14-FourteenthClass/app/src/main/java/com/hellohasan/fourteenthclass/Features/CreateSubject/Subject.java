package com.hellohasan.fourteenthclass.Features.CreateSubject;

public class Subject {
    private long _id;
    private String name;
    private int code;
    private double credit;

    public Subject(long _id, String name, int code, double credit) {
        this._id = _id;
        this.name = name;
        this.code = code;
        this.credit = credit;
    }

    public Subject(String name, int code, double credit) {
        this.name = name;
        this.code = code;
        this.credit = credit;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
