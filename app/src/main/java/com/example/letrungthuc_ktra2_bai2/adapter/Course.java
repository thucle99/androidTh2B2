package com.example.letrungthuc_ktra2_bai2.adapter;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String name,date,major;
    private int active;

    public Course() {
    }

    public Course(int id, String name, String date, String major, int active) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.major = major;
        this.active = active;
    }

    public Course(String name, String date, String major, int active) {
        this.name = name;
        this.date = date;
        this.major = major;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
