package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.GroupBean;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DatumCommitAdapter extends RecyclerView.Adapter<DatumCommitAdapter.ViewHolder> {
    private ConstmOnItemOnclickListener<GroupBean> mOnItemClickListener;
    private Context mContext;
    private ArrayList<GroupBean> mList = new ArrayList<>();
    private SparseBooleanArray mBooleanMap;//记录下哪个section是被打开的

    public DatumCommitAdapter(Context mContext) {
        this.mContext = mContext;
        mBooleanMap = new SparseBooleanArray();
    }

    public void onReference(List<GroupBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            mBooleanMap.put(0, true);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hotel_title_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.titleView.setText(mList.get(i).getTitle());
        viewHolder.tv_card_content.setText(mList.get(i).getContent());
        viewHolder.openView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(i);
//                String text = isOpen ? "展开" : "关闭";
                mBooleanMap.put(i, !isOpen);
//                holder.openView.setText(text);
                notifyDataSetChanged();
            }
        });
        viewHolder.openView.setImageResource(mBooleanMap.get(i) ? R.mipmap.icon_home_arrow_down_black : R.mipmap.icon_home_arrow_right_black);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        viewHolder.recyclerView.setLayoutManager(manager);
        ImageSeleteAdapter mAdapter = new ImageSeleteAdapter(mContext);
        viewHolder.recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, Object content) {
                switch (ClickType) {
                    case 0:
                        mOnItemClickListener.clickItem(viewHolder.recyclerView, i, position, 0, mList.get(i));
                        break;
                    case 1:
                        mOnItemClickListener.clickItem(viewHolder.recyclerView, i, position, 1, mList.get(i));
                        break;
                    case 2:
                        mOnItemClickListener.clickItem(viewHolder.recyclerView, i, position, 2, mList.get(i));
                        break;
                }
            }
        });
        if (mList.get(i).getInfoList() != null && mList.get(i).getInfoList().size() > 0) {
            mAdapter.onReference(mList.get(i).getInfoList());
        }
        if (mBooleanMap.get(i)) {
            viewHolder.item.setVisibility(View.VISIBLE);
        } else {
            viewHolder.item.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public ArrayList<GroupBean> getList() {
        return mList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView titleView;
        @BindView(R.id.tv_card_content)
        TextView tv_card_content;
        @BindView(R.id.tv_open)
        ImageView openView;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;
        @BindView(R.id.item)
        LinearLayout item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setOnItemClickListener(ConstmOnItemOnclickListener<GroupBean> listener) {
        this.mOnItemClickListener = listener;
    }
}
