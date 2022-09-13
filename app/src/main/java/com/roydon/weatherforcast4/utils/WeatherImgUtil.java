package com.roydon.weatherforcast4.utils;

import com.roydon.weatherforcast4.R;

public class WeatherImgUtil {

    public static int getImgResOfWeather(String weaStr) {
        // xue、lei、shachen、wu、bingbao、yun、yu、yin、qing
        int result = 0;
        switch (weaStr) {
            case "qing":
                result = R.drawable.weather_qing;
                break;
            case "yin":
                result = R.drawable.weather_yin;
                break;
            case "yu":
                result = R.drawable.weather_dayu;
                break;
            case "yun":
                result = R.drawable.weather_duoyun;
                break;
            case "bingbao":
                result = R.drawable.weather_leizhenyubingbao;
                break;
            case "wu":
                result = R.drawable.weather_wu;
                break;
            case "shachen":
                result = R.drawable.weather_shachenbao;
                break;
            case "lei":
                result = R.drawable.weather_leizhenyu;
                break;
            case "xue":
                result = R.drawable.weather_daxue;
                break;
            default:
                result = R.drawable.weather_qing;
                break;
        }

        return result;

    }
}
