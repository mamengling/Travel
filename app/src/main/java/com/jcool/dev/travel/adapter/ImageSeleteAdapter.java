package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.utils.GlideLoader;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

public class ImageSeleteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<String> mList = new ArrayList<>();
    private Context mContext;
    private int lastCount = 9;
    private static final int TYPE_ADD = 101;
    private static final int TYPE_IMAGE = 102;
    private ConstmOnItemOnclickListener iOnItemClickListener;

    public void setOnItemClickListener(ConstmOnItemOnclickListener listener) {
        this.iOnItemClickListener = listener;
    }

    public ImageSeleteAdapter(Context mContext, int lastCount) {
        this.mContext = mContext;
        this.lastCount = lastCount;
    }

    public ImageSeleteAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void onReference(List<String> list1) {
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 101) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.xml_add_item_image_list, viewGroup, false);
            return new AddViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_image_list, viewGroup, false);
            return new ViewHolder(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        int size = mList == null ? 0 : mList.size();
        int count = getItemCount();
        if (size < lastCount) {
            if (position == count - 1)
                return TYPE_ADD;
            else
                return TYPE_IMAGE;
        } else {
            return TYPE_IMAGE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_ADD:
                setDataAdd(position, viewHolder);
                break;
            case TYPE_IMAGE:
                setDataImage(position, viewHolder);
                break;
        }
    }

    private void setDataAdd(int position, RecyclerView.ViewHolder holder) {
        ((AddViewHolder) holder).image_add.setTag(position);
        ((AddViewHolder) holder).image_add.setOnClickListener(this);
    }

    private void setDataImage(int position, RecyclerView.ViewHolder holder) {
        ((ViewHolder) holder).image_close.setTag(position);
        ((ViewHolder) holder).image_close.setOnClickListener(this);
        ((ViewHolder) holder).image_show.setOnClickListener(this);
//        ((ViewHolder) holder).image_show.setTag(position);
        //小图加载
        Glide.with(mContext)
                .load(mList.get(position))
                .placeholder(R.mipmap.goods_icon).error(R.mipmap.goods_icon).into(((ViewHolder) holder).image_show);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        int size = mList == null ? 1 : mList.size();
        if (size < lastCount)
            count = size + 1;
        else
            count = lastCount;
        return count;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_add:
                int image_add = (int) v.getTag();
                iOnItemClickListener.clickItem(v, image_add, 0, 0, "");
                break;
            case R.id.image_close:
                int image_close = (int) v.getTag();
                mList.remove(image_close);
                notifyDataSetChanged();
                iOnItemClickListener.clickItem(v, image_close, 0, 2, "");
                break;
            case R.id.image_show:
//                int image_show = (int) v.getTag();
                iOnItemClickListener.clickItem(v, 0, 0, 1, "");
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_show;
        ImageView image_close;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_show = itemView.findViewById(R.id.image_show);
            image_close = itemView.findViewById(R.id.image_close);
        }
    }

    public class AddViewHolder extends RecyclerView.ViewHolder {
        ImageView image_add;

        public AddViewHolder(@NonNull View itemView) {
            super(itemView);
            image_add = itemView.findViewById(R.id.image_add);
        }
    }
}
