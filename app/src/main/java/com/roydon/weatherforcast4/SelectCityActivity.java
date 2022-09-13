package com.roydon.weatherforcast4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.roydon.weatherforcast4.adapter.CityAdapter;
import com.roydon.weatherforcast4.adapter.ProvinceAdapter;
import com.roydon.weatherforcast4.bean.CityBean;
import com.roydon.weatherforcast4.bean.ProvinceBean;
import com.roydon.weatherforcast4.db.DBUtils;
import com.roydon.weatherforcast4.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SelectCityActivity extends AppCompatActivity {

    private CityAdapter cityAdapter;
    private ProvinceAdapter provinceAdapter;
    private ArrayAdapter adapter;

    private RecyclerView rvCity;
    private RecyclerView rvProvince;

    private TextView tvNowCity;
    private Spinner spinner;

    private List<ProvinceBean> mProvinceBeanList;
    private List<CityBean> mCityBeanList;
    private List<CityBean> actureCityBeanList;
    private List<String> mCityNameList;

    private ArrayAdapter<String> mSpAdapter;

    private ImageView ivSearch;

    private AutoCompleteTextView query;//自动完成文本框

    DBUtils dbUtils = new DBUtils(SelectCityActivity.this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcity);

        initView();
        searchEvent();


    }

    private void searchEvent() {
        query = (AutoCompleteTextView) findViewById(R.id.edit_query);
        String[] cityArray = new String[actureCityBeanList.size()];
//        for (int i = 0; i < actureCityBeanList.size(); i++) {
//
//            if(actureCityBeanList.get(i).getName().equals("澳门特别行政区")||actureCityBeanList.get(i).getName().equals("香港特别行政区")){
//                cityArray[i] = actureCityBeanList.get(i).getName().substring(0, 2);
//            }else {
//                cityArray[i] = actureCityBeanList.get(i).getName().substring(0, actureCityBeanList.get(i).getName().length() - 1);
//            }
//        }
        int k=0;
        for (int i = 0; i < mProvinceBeanList.size(); i++) {
            for (int j = 0; j < mProvinceBeanList.get(i).getCity().size(); j++) {
                cityArray[k++]=mProvinceBeanList.get(i).getCity().get(j).getName()+"-"+mProvinceBeanList.get(i).getPname();
            }
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, cityArray);
//        cityAdapter = new CityAdapter(SelectCityActivity.this,mCityBeanList);
        query.setAdapter(adapter);

        ivSearch = (ImageView) findViewById(R.id.iv_search);
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] cityNames = query.getText().toString().split("市");

                String cityName = cityNames[0];
//                ToastUtil.showLongToast(SelectCityActivity.this, cityName);
                Intent intent = new Intent(SelectCityActivity.this, MainActivity.class);
                intent.putExtra("inputCity", cityName);
                setResult(200, intent);
                finish();
            }
        });
    }

    private void initView() {

        tvNowCity = (TextView) findViewById(R.id.tv_nowCity);

        tvNowCity.setText(getIntent().getExtras().getString("nowCity") == null ? "null" : getIntent().getExtras().getString("nowCity"));

        mProvinceBeanList = new ArrayList<>();
        actureCityBeanList = new ArrayList<>();
        mCityNameList = new ArrayList<String>();

        spinner = (Spinner) findViewById(R.id.spinnerCity);
        rvCity = findViewById(R.id.city_items);
        rvProvince = findViewById(R.id.province_items);
        //读取文件
        try {
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
            //获取省，封装
            for (int i = 0; i < Data.length(); i++) {
                JSONObject provinceJsonObject = Data.getJSONObject(i);
                String provinceName = provinceJsonObject.getString("pname");
                ProvinceBean provinceBean = new ProvinceBean();
                provinceBean.setPname(provinceName);
                mCityBeanList = new ArrayList<>();
                //添加市
                final JSONArray cityArray = provinceJsonObject.getJSONArray("city");
                for (int j = 0; j < cityArray.length(); j++) {
                    JSONObject cityObj = cityArray.getJSONObject(j);
                    String cityName = cityObj.getString("name");
                    CityBean cityBean = new CityBean();
                    cityBean.setName(cityName);
//                    mCityNameList.add(cityName);
                    mCityBeanList.add(cityBean);
                    actureCityBeanList.add(cityBean);
                }
                provinceBean.setCity(mCityBeanList);
                mProvinceBeanList.add(provinceBean);
                // 添加县待拓展。。。。。。。。。
            }
            Log.d("SelectCity", "mProvinceBeanList>>>>>>>>>" + mProvinceBeanList.toString());
//            Log.d("SelectCity", "mCityBeanList>>>>>>>>>" + mCityBeanList.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        provinceAdapter = new ProvinceAdapter(SelectCityActivity.this, mProvinceBeanList);
        rvProvince.setLayoutManager(new LinearLayoutManager(SelectCityActivity.this));
        rvProvince.setAdapter(provinceAdapter);

        List<CityBean> mDbCityList = dbUtils.getAllCity();
        cityAdapter = new CityAdapter(SelectCityActivity.this, mDbCityList);
        rvCity.setLayoutManager(new LinearLayoutManager(SelectCityActivity.this));
        rvCity.setAdapter(cityAdapter);

        //市的点击事件
        cityAdapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position2) {
                Log.d("////////////province", "======>>>>>>" + mDbCityList.get(position2));
                Intent intent = new Intent(SelectCityActivity.this, MainActivity.class);
                intent.putExtra("inputCity", mDbCityList.get(position2).getName());
                setResult(200, intent);
                finish();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        //省的点击事件
        provinceAdapter.setOnItemClickListener(new ProvinceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //显示被点击省的市
                cityAdapter = new CityAdapter(SelectCityActivity.this, mProvinceBeanList.get(position).getCity());
                rvCity.setLayoutManager(new LinearLayoutManager(SelectCityActivity.this));
                rvCity.setAdapter(cityAdapter);
                //市的点击事件
                cityAdapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position2) {
                        Log.d("////////////province", "======>>>>>>" + mProvinceBeanList.get(position).getCity().get(position2));
                        Intent intent = new Intent(SelectCityActivity.this, MainActivity.class);
                        if (mProvinceBeanList.get(position).getCity().get(position2).getName().equals("香港特别行政区")||mProvinceBeanList.get(position).getCity().get(position2).getName().equals("澳门特别行政区"))
                        {
                            intent.putExtra("inputCity", mProvinceBeanList.get(position).getCity().get(position2).getName().substring(0, 2));

                        }else {
                            intent.putExtra("inputCity", mProvinceBeanList.get(position).getCity().get(position2).getName().substring(0, mProvinceBeanList.get(position).getCity().get(position2).getName().length() - 1));
                        }
                        setResult(200, intent);
                        finish();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
            }
        });

    }
}
