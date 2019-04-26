package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.LocationInfoBean;

import java.util.ArrayList;
import java.util.List;

public class SearchAddressAdapter extends RecyclerView.Adapter<SearchAddressAdapter.ViewHolder> {
    private List<LocationInfoBean> mDatas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;
    //回调接口
    public ClickInterface clickInterface;


    public SearchAddressAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    public void onReference(List<LocationInfoBean> list1) {
        if (list1 != null) {
            mDatas.clear();
            mDatas.addAll(list1);
            notifyDataSetChanged();
        } else {
            mDatas.clear();
            notifyDataSetChanged();
        }
    }

    public void setOnclick(ClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    public List<LocationInfoBean> getList() {
        return mDatas;
    }

    public interface ClickInterface {
        void onItemClick(View view, int position);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder myViewHolder, final int i) {
        myViewHolder.cityTitle.setText(mDatas.get(i).getAddress());
        myViewHolder.cityAdress.setText(mDatas.get(i).getAddressSearch());
        myViewHolder.con_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickInterface != null) {
                    clickInterface.onItemClick(view, i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.home_item_select_location, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView cityTitle, cityAdress;
        private ConstraintLayout con_location;

        public ViewHolder(View view) {
            super(view);
            cityTitle = view.findViewById(R.id.tv_title);
            cityAdress = view.findViewById(R.id.tv_location);
            con_location = view.findViewById(R.id.con_location);
        }
    }
}
