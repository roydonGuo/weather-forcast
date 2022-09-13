package com.roydon.weatherforcast4.utils;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.roydon.weatherforcast4.bean.CityBean;
import com.roydon.weatherforcast4.bean.ProvinceBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonTurnUtil extends AppCompatActivity {

    public List<CityBean> getAllCity() throws IOException, JSONException {
        List<ProvinceBean> mProvinceBeanList = new ArrayList<>();
        List<CityBean> mCityBeanList = new ArrayList<>();
        InputStream inputStream = getResources().getAssets().open("City.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String lines = bufferedReader.readLine();
        while (lines != null) {
            stringBuffer.append(lines);
            lines = bufferedReader.readLine();
        }
        String resultCity = stringBuffer.toString();
        Log.d("SelectCity", "resultCity>>>>>>>>>" + resultCity);

        final JSONArray Data = new JSONArray(resultCity);
        //循环这个文件数组、获取数组中每个省对象的名字
        for (int i = 0; i < Data.length(); i++) {
            //添加省
            JSONObject provinceJsonObject = Data.getJSONObject(i);
            String provinceName = provinceJsonObject.getString("pname");
            ProvinceBean provinceBean = new ProvinceBean();
            provinceBean.setPname(provinceName);
            mProvinceBeanList.add(provinceBean);
            //添加市
            final JSONArray cityArray = provinceJsonObject.getJSONArray("city");
            for (int j = 0; j < cityArray.length(); j++) {
                JSONObject cityObj = cityArray.getJSONObject(j);
                String cityName = cityObj.getString("name");
                CityBean cityBean = new CityBean();
                cityBean.setName(cityName);
                mCityBeanList.add(cityBean);
            }
            // 添加区/县待拓展。。。。。。。。。
        }
        return mCityBeanList;

    }
}
