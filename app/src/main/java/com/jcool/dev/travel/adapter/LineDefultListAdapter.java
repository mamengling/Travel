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
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineDefultListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TravelInfoBean.LinesBean.CharacteristicBean> data = new ArrayList<>();
    private Context mContext;
    public static int VIEW_1 = 101;
    public static int VIEW_2 = 102;

    public LineDefultListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<TravelInfoBean.LinesBean.CharacteristicBean> list1) {
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
        if (viewType == VIEW_1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_travel_title, viewGroup, false);
            return new ViewTitleHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_travel_line_image, viewGroup, false);
            return new ViewImageHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getViewType();

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == VIEW_1) {
            ((ViewTitleHolder) viewHolder).tvTitle.setText(data.get(i).getTitle());
        }else {
            ImageLoaderUtils.showImageViewToRoundedCorners(mContext, data.get(i).getFileUrl(), ((ViewImageHolder)viewHolder).image_city_head, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewTitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewTitleHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image_city_head;


        public ViewImageHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
