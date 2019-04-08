package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.DestinationBean;
import com.jcool.dev.travel.bean.TravelBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContextRightListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DestinationBean.SecondPlaceBean> data = new ArrayList<>();
    private Context mContext;
    public static int VIEW_TYPE_TITLE = 101;
    public static int VIEW_TYPE_ONLY_CITY_NAME = 102;
    public static int VIEW_TYPE_IMAGE_NAME = 103;

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
//        if (viewType == VIEW_TYPE_IMAGE_NAME) {
//            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_right_list_city, viewGroup, false);
//            return new ViewImageHolder(view);
//        } else if (viewType == VIEW_TYPE_ONLY_CITY_NAME) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_right_list_city_name, viewGroup, false);
            return new ViewNameHolder(view);
//        } else {
//            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_right_list_title, viewGroup, false);
//            return new ViewTitleHolder(view);
//        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewTitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title_name)
        TextView tvTitle;

        public ViewTitleHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_city_name)
        TextView tvTitle;
        @BindView(R.id.image_city_head)
        ImageView image_city_head;


        public ViewImageHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewNameHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_city_name)
        TextView tvTitle;
        public ViewNameHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
