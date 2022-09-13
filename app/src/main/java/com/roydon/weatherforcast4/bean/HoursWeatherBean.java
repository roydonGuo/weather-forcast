package com.roydon.weatherforcast4.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HoursWeatherBean implements Serializable {

    @SerializedName("hours")
    private String hours;

    @SerializedName("wea_img")
    private String weaImg;

    @SerializedName("tem")
    private String tem;

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getWeaImg() {
        return weaImg;
    }

    public void setWeaImg(String weaImg) {
        this.weaImg = weaImg;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    @Override
    public String toString() {
        return "HoursWeatherBean{" +
                "hours='" + hours + '\'' +
                ", weaImg='" + weaImg + '\'' +
                ", tem='" + tem + '\'' +
                '}';
    }
}
