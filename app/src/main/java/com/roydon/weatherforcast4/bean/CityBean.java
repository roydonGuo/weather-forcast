package com.roydon.weatherforcast4.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;

public class CityBean implements Serializable {

    @SerializedName("name")
    private String name;
    @SerializedName("area")
    private String[] area;//县/区
    private String tem;
    private String updateTime;

    public CityBean() {
    }

    public CityBean(String name) {
        this.name = name;
    }

    public CityBean(String name, String tem, String updateTime) {
        this.name = name;
        this.tem = tem;
        this.updateTime = updateTime;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "name='" + name + '\'' +
                ", area=" + Arrays.toString(area) +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getArea() {
        return area;
    }

    public void setArea(String[] area) {
        this.area = area;
    }
}
