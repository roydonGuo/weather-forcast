package com.roydon.weatherforcast4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roydon.weatherforcast4.R;
import com.roydon.weatherforcast4.bean.TipsBean;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.TipsViewHolder> {
    private Context mContext;
    private List<TipsBean> mTipsBeans;

    public TipsAdapter(Context context, List<TipsBean> tipsBeans) {
        mContext = context;
        mTipsBeans = tipsBeans;
    }

    @NonNull
    @Override
    public TipsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tips_item_layout, parent, false);

        return new TipsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TipsViewHolder holder, int position) {
        TipsBean otherTipsBean = mTipsBeans.get(position);
        holder.tvTitle.setText("🔸"+otherTipsBean.getTitle());
        holder.tvLevel.setText(otherTipsBean.getLevel());
        holder.tvDesc.setText(otherTipsBean.getDesc());
    }

    @Override
    public int getItemCount() {
        return (mTipsBeans == null) ? 0 : mTipsBeans.size();
    }

    class TipsViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDesc, tvLevel;

        public TipsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvLevel = itemView.findViewById(R.id.tv_level);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
