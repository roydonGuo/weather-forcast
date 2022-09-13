package com.roydon.weatherforcast4.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roydon.weatherforcast4.R;
import com.roydon.weatherforcast4.bean.ProvinceBean;

import java.util.List;

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.ProvinceViewHolder> {

    private Context mContext;
    private List<ProvinceBean> mProvinceBeans;

    public ProvinceAdapter(Context context, List<ProvinceBean> provinceBeans) {
        mContext = context;
        this.mProvinceBeans = provinceBeans;
    }

    class ProvinceViewHolder extends RecyclerView.ViewHolder {

        TextView tvProvince;

        public ProvinceViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProvince = itemView.findViewById(R.id.tv_province_list);
            //绑定点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    ToastUtil.showShotToast(mContext, "data==" + mProvinceBeans.get(getLayoutPosition()));
                    if (onItemClickListener!=null) {
                        onItemClickListener.onItemClick(view,getLayoutPosition());
                    }

                }
            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
////                    ToastUtil.showShotToast(mContext, "data==" + mProvinceBeans.get(getLayoutPosition()));
//                    if (onItemClickListener!=null) {
//                        onItemClickListener.onItemLongClick(view,getLayoutPosition());
//                    }
//
//                }
//            });

        }

    }

    /**
     * 点击响应事件
     */
    public interface OnItemClickListener {

        /**
         * 当RecyclerView某个被点击的时候回调
         * @param view 点击item的视图
         * @param position 点击得到的数据
         */
        public void onItemClick(View view, int position);
//        public void onItemLongClick(View view,int position);

    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
//    public final VH createViewHolder(@NonNull ViewGroup parent, int viewType) {
//        try {
//            TraceCompat.beginSection(TRACE_CREATE_VIEW_TAG);
//            final VH holder = onCreateViewHolder(parent, viewType);
//            if (holder.itemView.getParent() != null) {
//                throw new IllegalStateException("ViewHolder views must not be attached when"
//                        + " created. Ensure that you are not passing 'true' to the attachToRoot"
//                        + " parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
//            }
//            holder.mItemViewType = viewType;
//            return holder;
//        } finally {
//            TraceCompat.endSection();
//        }
//    }


    @NonNull
    @Override
    public ProvinceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.province, parent, false);
        ProvinceViewHolder provinceViewHolder = new ProvinceViewHolder(view);
        return provinceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProvinceViewHolder holder, int position) {
        ProvinceBean provinceBean = mProvinceBeans.get(position);

        holder.tvProvince.setText(provinceBean.getPname());
//        holder.tvProvince.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("selectProvince",">>>>>>>>citypro>>>>======"+provinceBean.getPname());
//
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return (mProvinceBeans == null) ? 0 : mProvinceBeans.size();
    }


}
