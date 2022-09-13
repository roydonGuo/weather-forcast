package com.roydon.weatherforcast4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roydon.weatherforcast4.R;
import com.roydon.weatherforcast4.bean.HoursWeatherBean;
import com.roydon.weatherforcast4.utils.WeatherImgUtil;
import com.roydon.weatherforcast4.view.TemperatureView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HourWeatherAdapter extends RecyclerView.Adapter<HourWeatherAdapter.HourViewHolder> {
    private Context mContext;
    private List<HoursWeatherBean> mHoursWeatherBeans;
    private List<Integer> hourData = new ArrayList<>();
    private int minValue;
    private int maxValue;

    public HourWeatherAdapter(Context context, List<HoursWeatherBean> hoursWeatherBeans) {
        mContext = context;
        this.mHoursWeatherBeans = hoursWeatherBeans;

        for (int i = 0; i < hoursWeatherBeans.size(); i++) {
            this.hourData.add(Integer.valueOf(hoursWeatherBeans.get(i).getTem()));
        }

        minValue = Collections.min(this.hourData);
        maxValue = Collections.max(this.hourData);

    }

    @NonNull
    @Override
    public HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hour_item_layout, parent, false);
        HourViewHolder hourViewHolder = new HourViewHolder(view);
        return hourViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position) {
        HoursWeatherBean hoursweatherBean = mHoursWeatherBeans.get(position);

        if (position==0){
            holder.tvHours.setText("现在");
        }else {holder.tvHours.setText(hoursweatherBean.getHours().substring(0, 2) + ":00");}
        holder.tvTem.setText(hoursweatherBean.getTem() + "℃");

        holder.ivWeather.setImageResource(WeatherImgUtil.getImgResOfWeather(hoursweatherBean.getWeaImg()));

        //如果是第一个
        if(position==0){
            holder.mTemperatureView.setDrawLeftLine(false);
        }
        //除第一个以外
        else{
            holder.mTemperatureView.setDrawLeftLine(true);
            holder.mTemperatureView.setLastValue(hourData.get(position-1));
        }

        //如果是最后一个
        if(position == hourData.size()-1){
            holder.mTemperatureView.setDrawRightLine(false);
        }
        //除最后一个以外
        else{
            holder.mTemperatureView.setDrawRightLine(true);
            holder.mTemperatureView.setNextValue(hourData.get(position+1));
        }

        holder.mTemperatureView.setCurrentValue(hourData.get(position));

    }

    @Override
    public int getItemCount() {
        return (mHoursWeatherBeans == null) ? 0 : mHoursWeatherBeans.size();
    }


    class HourViewHolder extends RecyclerView.ViewHolder {

        TextView tvHours, tvTem;
        ImageView ivWeather;
        private TemperatureView mTemperatureView;

        public HourViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHours = itemView.findViewById(R.id.tv_hours);
            tvTem = itemView.findViewById(R.id.tv_tem);
            ivWeather = itemView.findViewById(R.id.iv_weather);

            mTemperatureView = itemView.findViewById(R.id.temp_view);
            mTemperatureView.setMinValue(minValue);
            mTemperatureView.setMaxValue(maxValue);
        }


    }


}
