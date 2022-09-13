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
import com.roydon.weatherforcast4.bean.DayWeatherBean;
import com.roydon.weatherforcast4.utils.WeatherImgUtil;

import java.util.List;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.WeatherViewHolder> {

    private Context mContext;
    private List<DayWeatherBean> mWeatherBeans;

    public FutureWeatherAdapter(Context context, List<DayWeatherBean> weatherBeans) {
        mContext = context;
        this.mWeatherBeans = weatherBeans;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.future_item_layout, parent, false);
        WeatherViewHolder weatherViewHolder = new WeatherViewHolder(view);
        return weatherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        DayWeatherBean weatherBean = mWeatherBeans.get(position);

        if (position==0) {
            holder.tvDate.setText(weatherBean.getDate().substring(5)+"[明天]");
        }
        if (position==1) {
            holder.tvDate.setText(weatherBean.getDate().substring(5)+"[后天]");
        }
        if (position>1){
            holder.tvDate.setText(weatherBean.getDate().substring(5)+"[周"+weatherBean.getWeek().substring(2)+"]");
        }
        holder.tvTem.setText(weatherBean.getTem());
        holder.tvTemLowHigh.setText(weatherBean.getTem2() + "/" + weatherBean.getTem1());
        holder.ivWeather.setImageResource(WeatherImgUtil.getImgResOfWeather(weatherBean.getWea_img()));
    }

    @Override
    public int getItemCount() {
        return (mWeatherBeans == null) ? 0 : mWeatherBeans.size();
    }


    class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView tvTem, tvDate,tvTemLowHigh;
        ImageView ivWeather;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTem = itemView.findViewById(R.id.tv_tem);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvTemLowHigh = itemView.findViewById(R.id.tv_tem_low_high);
            ivWeather = itemView.findViewById(R.id.iv_weather);
        }

    }
}
