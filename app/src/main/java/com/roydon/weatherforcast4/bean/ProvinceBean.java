package com.roydon.weatherforcast4.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProvinceBean implements Serializable {//省

    @SerializedName("pname")
    private String pname;
    @SerializedName("city")
    private List<CityBean> city;

    @Override
    public String toString() {
        return "ProvinceBean{" +
                "pname='" + pname + '\'' +
                ", city=" + city +
                '}';
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }
}
