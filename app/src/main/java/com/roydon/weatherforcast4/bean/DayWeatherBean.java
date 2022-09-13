package com.roydon.weatherforcast4.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * TextView tv_city,tv_time,tv_weather,tv_week,tv_tem,tv_tem_low_high,tv_win,tv_air;7个
 * ImageView iv_weather;//天气图标
 */
public class DayWeatherBean implements Serializable {
    @SerializedName("date")
    private String date;
    private String wea;//天气
    private String wea_img;//天气图标
    private String week;//周几
    private String tem;//温度
    //tv_tem_low_high=tem2+tem1拼接一起
    private String tem2;//低温
    private String tem1;//高温
    //tv_win=win+win_speed
    private String[] win;//风力
    private String win_speed;//风力等级
    //tv_air=air+air_level+air_tips拼接一起
    private String air;//
    private String air_level;//
    private String air_tips;//

    @SerializedName("hours")
    private List<HoursWeatherBean> hoursWeatherBeanList;

    @SerializedName("index")
    private List<TipsBean> mTipsBeans;

    @Override
    public String toString() {
        return "DayWeatherBean{" +
                "date='" + date + '\'' +
                ", wea='" + wea + '\'' +
                ", wea_img='" + wea_img + '\'' +
                ", week='" + week + '\'' +
                ", tem='" + tem + '\'' +
                ", tem2='" + tem2 + '\'' +
                ", tem1='" + tem1 + '\'' +
                ", win=" + Arrays.toString(win) +
                ", win_speed='" + win_speed + '\'' +
                ", air='" + air + '\'' +
                ", air_level='" + air_level + '\'' +
                ", air_tips='" + air_tips + '\'' +
                ", hoursWeatherBeanList=" + hoursWeatherBeanList +
                ", mTipsBeans=" + mTipsBeans +
                '}';
    }

    public List<TipsBean> getmTipsBeans() {
        return mTipsBeans;
    }

    public void setmTipsBeans(List<TipsBean> mTipsBeans) {
        this.mTipsBeans = mTipsBeans;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<HoursWeatherBean> getHoursWeatherBeanList() {
        return hoursWeatherBeanList;
    }

    public void setHoursWeatherBeanList(List<HoursWeatherBean> hoursWeatherBeanList) {
        this.hoursWeatherBeanList = hoursWeatherBeanList;
    }

    public String getWea() {
        return wea;
    }

    public void setWea(String wea) {
        this.wea = wea;
    }

    public String getWea_img() {
        return wea_img;
    }

    public void setWea_img(String wea_img) {
        this.wea_img = wea_img;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getTem() {
        return tem;
    }

    public void setTem(String tem) {
        this.tem = tem;
    }

    public String getTem2() {
        return tem2;
    }

    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }

    public String getTem1() {
        return tem1;
    }

    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }

    public String[] getWin() {
        return win;
    }

    public void setWin(String[] win) {
        this.win = win;
    }

    public String getWin_speed() {
        return win_speed;
    }

    public void setWin_speed(String win_speed) {
        this.win_speed = win_speed;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getAir_level() {
        return air_level;
    }

    public void setAir_level(String air_level) {
        this.air_level = air_level;
    }

    public String getAir_tips() {
        return air_tips;
    }

    public void setAir_tips(String air_tips) {
        this.air_tips = air_tips;
    }
}
