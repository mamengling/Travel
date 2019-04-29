package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.DestinationBean;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

public class ContextRightListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DestinationBean.SecondPlaceBean> data = new ArrayList<>();
    private Context mContext;
    public static int VIEW_TYPE_TITLE = 101;
    public static int VIEW_TYPE_ONLY_CITY_NAME = 102;
    public static int VIEW_TYPE_IMAGE_NAME = 103;
    private ConstmOnItemOnclickListener<String> mOnItemClickListener;

    public ContextRightListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<DestinationBean.SecondPlaceBean> list1) {
        if (list1 != null) {
            data.clear();
            data.addAll(list1);
            notifyDataSetChanged();
        } else {
            data.clear();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        if (viewType == 3) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_right_list_title, viewGroup, false);
            return new ViewTitleHolder(view);
        } else if (viewType == 2) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_right_list_city, viewGroup, false);
            return new ViewImageHolder(view);
        } else if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_right_list_city_name, viewGroup, false);
            return new ViewNameHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_right_list_city_name, viewGroup, false);
            return new ViewNameHolder(view);//100
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.equals("02", data.get(position).getPlaceType())) {//只是名字
            return 1;
        } else if (TextUtils.equals("01", data.get(position).getPlaceType())) {//带图片
            return 2;
        } else if (TextUtils.equals("03", data.get(position).getPlaceType())) {//城市标题
            return 3;
        }
        return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        if (getItemViewType(position) == 2) {
            ((ViewImageHolder) viewHolder).tvTitle.setText(data.get(position).getPlaceName());
            ImageLoaderUtils.showImageViewToRoundedCorners(mContext, data.get(position).getPlaceImage(), ((ViewImageHolder) viewHolder).image_city_head, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
            ((ViewImageHolder) viewHolder).tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(((ViewImageHolder) viewHolder).tvTitle, position, 0, 0, data.get(position).getPlaceName());
                }
            });
        } else if (getItemViewType(position) == 3) {
            ((ViewTitleHolder) viewHolder).tvTitle.setText(data.get(position).getPlaceName());
        } else {
            ((ViewNameHolder) viewHolder).tvTitle.setText(data.get(position).getPlaceName());
            ((ViewNameHolder) viewHolder).tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(((ViewNameHolder) viewHolder).tvTitle, position, 0, 0, data.get(position).getPlaceName());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<DestinationBean.SecondPlaceBean> getList() {
        return data;
    }


    public class ViewTitleHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public ViewTitleHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title_name);
        }
    }

    public class ViewImageHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView image_city_head;

        public ViewImageHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_city_name);
            image_city_head = itemView.findViewById(R.id.image_city_head);
        }
    }

    public class ViewNameHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public ViewNameHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_name);
        }
    }

    public void setOnItemClickListener(ConstmOnItemOnclickListener<String> listener) {
        this.mOnItemClickListener = listener;
    }
}
