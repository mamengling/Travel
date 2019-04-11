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
import com.jcool.dev.travel.bean.VisaInfoDtoList;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisaListViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VisaInfoDtoList.VisaInfoDtoListBean> mList = new ArrayList<>();
    private Context mContext;
    private ConstmOnItemOnclickListener<VisaInfoDtoList.VisaInfoDtoListBean> mOnItemClickListener;

    public VisaListViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<VisaInfoDtoList.VisaInfoDtoListBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        if (viewType == 101) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_visa_list_title, viewGroup, false);
            return new ViewTitleHolder(view);
        } else if (viewType == 102) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_visa_end_image, viewGroup, false);
            return new ViewImageBHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_visa_list_imge, viewGroup, false);
            return new ViewImageHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getViewType();
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        if (getItemViewType(i) == 101) {
            ((ViewTitleHolder) viewHolder).tvTitle.setText(mList.get(i).getVisaSortName());
            ((ViewTitleHolder) viewHolder).tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(((ViewTitleHolder) viewHolder).tv_more, i, 0, 0, mList.get(i));
                }
            });
        } else if (getItemViewType(i) == 102) {

            ((ViewImageBHolder) viewHolder).image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(((ViewImageBHolder) viewHolder).image, i, 0, 1, mList.get(i));
                }
            });
        } else {
            ((ViewImageHolder) viewHolder).tvTitle.setText("¥" + mList.get(i).getVisaPrice());
            ((ViewImageHolder) viewHolder).tv_name.setText(mList.get(i).getVisaName());
            ImageLoaderUtils.showImageViewToRoundedCorners(mContext, mList.get(i).getVisaImage(), ((ViewImageHolder) viewHolder).image_goods_visa, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
            ((ViewImageHolder) viewHolder).image_goods_visa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(((ViewImageHolder) viewHolder).image_goods_visa, i, 0, 2, mList.get(i));
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewTitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_more)
        TextView tv_more;


        public ViewTitleHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class ViewImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_money)
        TextView tvTitle;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.image_goods_visa)
        ImageView image_goods_visa;

        public ViewImageHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewImageBHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;
        public ViewImageBHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setOnItemClickListener(ConstmOnItemOnclickListener<VisaInfoDtoList.VisaInfoDtoListBean> listener) {
        this.mOnItemClickListener = listener;
    }
}
