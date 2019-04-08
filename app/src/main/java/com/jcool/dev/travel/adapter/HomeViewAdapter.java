package com.jcool.dev.travel.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.BannerBean;
import com.jcool.dev.travel.bean.HomeViewBean;
import com.jcool.dev.travel.fragment.HomeFragment;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.jcool.dev.travel.view.rollviewpage.OnItemClickListener;
import com.jcool.dev.travel.view.rollviewpage.RollPagerView;
import com.jcool.dev.travel.view.rollviewpage.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

public class HomeViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeViewBean> mList = new ArrayList<>();
    private HomeFragment mContext;
    private ConstmOnItemOnclickListener<HomeViewBean> mItemOnclickListener;
    private ViewpagerAdapter mPagerAdapter;

    public HomeViewAdapter(HomeFragment mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<HomeViewBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == HomeViewBean.HOME_VIEW_BANNER) {
            view = LayoutInflater.from(mContext.getContext()).inflate(R.layout.xml_item_home_banner, parent, false);
            return new ViewHolderBanner(view);
        } else if (viewType == HomeViewBean.HOME_VIEW_ICON) {
            view = LayoutInflater.from(mContext.getContext()).inflate(R.layout.xml_item_home_icon, parent, false);
            return new ViewHolderIcon(view);
        } else if (viewType == HomeViewBean.HOME_VIEW_GOODS_SALES) {
            view = LayoutInflater.from(mContext.getContext()).inflate(R.layout.xml_item_home_goods_sales, parent, false);
            return new ViewHolderGoods(view);
        } else if (viewType == HomeViewBean.HOME_VIEW_TAB) {
            view = LayoutInflater.from(mContext.getContext()).inflate(R.layout.xml_item_home_tab, parent, false);
            return new ViewHolderTab(view);
        } else {
            view = LayoutInflater.from(mContext.getContext()).inflate(R.layout.xml_item_home_tab_list, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == HomeViewBean.HOME_VIEW_BANNER) {
            mPagerAdapter = new ViewpagerAdapter(mContext.getContext());
            ((ViewHolderBanner) holder).rollPagerView.setPlayDelay(3000);
            ((ViewHolderBanner) holder).rollPagerView.setAdapter(mPagerAdapter);
            ((ViewHolderBanner) holder).rollPagerView.setHintView(new ColorPointHintView(mContext.getContext(), Color.parseColor("#5ac5b3"), Color.WHITE));
            ((ViewHolderBanner) holder).rollPagerView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int positionChild) {
                    mItemOnclickListener.clickItem(((ViewHolderBanner) holder).rollPagerView, position, positionChild, AppConfigStatic.HOME_VIEW_GOODS_BANNER, mList.get(position));
                }
            });
            mPagerAdapter.onReference(mList.get(position).getBannerBeanList());
        } else if (getItemViewType(position) == HomeViewBean.HOME_VIEW_ICON) {
            ((ViewHolderIcon) holder).btn_search.setOnClickListener(new ConstomOnclickListenter(position, 0, AppConfigStatic.HOME_VIEW_USER_SEARCH));
            ((ViewHolderIcon) holder).btn_vise.setOnClickListener(new ConstomOnclickListenter(position, 0, AppConfigStatic.HOME_VIEW_VISE));
            ((ViewHolderIcon) holder).btn_go_out.setOnClickListener(new ConstomOnclickListenter(position, 0, AppConfigStatic.HOME_VIEW_GOOUT_TRAVEL));
            ((ViewHolderIcon) holder).btn_in.setOnClickListener(new ConstomOnclickListenter(position, 0, AppConfigStatic.HOME_VIEW_IN_TRAVEL));
            ((ViewHolderIcon) holder).btn_mine.setOnClickListener(new ConstomOnclickListenter(position, 0, AppConfigStatic.HOME_VIEW_FREE_TRAVEL));
            ((ViewHolderIcon) holder).btn_company.setOnClickListener(new ConstomOnclickListenter(position, 0, AppConfigStatic.HOME_VIEW_COMPANY_TRAVEL));
            ((ViewHolderIcon) holder).btn_user.setOnClickListener(new ConstomOnclickListenter(position, 0, AppConfigStatic.HOME_VIEW_USER_TRAVEL));

        } else if (getItemViewType(position) == HomeViewBean.HOME_VIEW_GOODS_SALES) {
            ((ViewHolderGoods) holder).itemView.setOnClickListener(new ConstomOnclickListenter(position, 0, AppConfigStatic.HOME_VIEW_GOODS_SALSE));
        } else if (getItemViewType(position) == HomeViewBean.HOME_VIEW_TAB) {
            ((ViewHolderTab) holder).radiogroup_full.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radiobutton0:
                            mItemOnclickListener.clickItem(((ViewHolderTab) holder).radiobutton0, position, 0, AppConfigStatic.HOME_VIEW_GOODS_TAB, mList.get(position));
                            break;
                        case R.id.radiobutton1:
                            mItemOnclickListener.clickItem(((ViewHolderTab) holder).radiobutton1, position, 1, AppConfigStatic.HOME_VIEW_GOODS_TAB, mList.get(position));
                            break;
                        case R.id.radiobutton2:
                            mItemOnclickListener.clickItem(((ViewHolderTab) holder).radiobutton2, position, 2, AppConfigStatic.HOME_VIEW_GOODS_TAB, mList.get(position));
                            break;
                    }
                }
            });
        } else {
            ((ViewHolder) holder).itemView.setOnClickListener(new ConstomOnclickListenter(position, 0, AppConfigStatic.HOME_VIEW_GOODS_SALSE));
        }
    }

    public void setBannerData(List<BannerBean> bannerData) {
        mList.get(0).getBannerBeanList().clear();
        mList.get(0).getBannerBeanList().addAll(bannerData);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ViewHolderBanner extends RecyclerView.ViewHolder {
        RollPagerView rollPagerView;

        public ViewHolderBanner(@NonNull View itemView) {
            super(itemView);
            rollPagerView = itemView.findViewById(R.id.normal_view_pager);
        }
    }

    public class ViewHolderIcon extends RecyclerView.ViewHolder {
        LinearLayout btn_search;
        LinearLayout btn_vise;
        TextView btn_go_out;
        TextView btn_all_around;
        TextView btn_in;
        TextView btn_mine;
        LinearLayout btn_company;
        LinearLayout btn_user;

        public ViewHolderIcon(@NonNull View itemView) {
            super(itemView);
            btn_search = itemView.findViewById(R.id.btn_search);
            btn_vise = itemView.findViewById(R.id.btn_vise);
            btn_go_out = itemView.findViewById(R.id.btn_go_out);
            btn_all_around = itemView.findViewById(R.id.btn_all_around);
            btn_in = itemView.findViewById(R.id.btn_in);
            btn_mine = itemView.findViewById(R.id.btn_mine);
            btn_company = itemView.findViewById(R.id.btn_company);
            btn_user = itemView.findViewById(R.id.btn_user);
        }
    }

    public class ViewHolderGoods extends RecyclerView.ViewHolder {


        public ViewHolderGoods(@NonNull View itemView) {
            super(itemView);

        }
    }

    public class ViewHolderTab extends RecyclerView.ViewHolder {
        RadioGroup radiogroup_full;
        RadioButton radiobutton0;
        RadioButton radiobutton1;
        RadioButton radiobutton2;

        public ViewHolderTab(@NonNull View itemView) {
            super(itemView);
            radiogroup_full = itemView.findViewById(R.id.radiogroup_full);
            radiobutton0 = itemView.findViewById(R.id.radiobutton0);
            radiobutton1 = itemView.findViewById(R.id.radiobutton1);
            radiobutton2 = itemView.findViewById(R.id.radiobutton2);

        }
    }


    public void setOnItemOnclickListener(ConstmOnItemOnclickListener<HomeViewBean> onItemOnclickListener) {
        this.mItemOnclickListener = onItemOnclickListener;
    }


    private class ConstomOnclickListenter implements View.OnClickListener {
        private int position;
        private int positionChild;
        private int ClickType;

        public ConstomOnclickListenter(int position, int positionChild, int clickType) {
            this.position = position;
            this.positionChild = positionChild;
            ClickType = clickType;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_search:
                    mItemOnclickListener.clickItem(v, position, positionChild, ClickType, mList.get(position));
                    break;
                case R.id.btn_vise:
                    mItemOnclickListener.clickItem(v, position, positionChild, ClickType, mList.get(position));
                    break;
                case R.id.btn_go_out:
                    mItemOnclickListener.clickItem(v, position, positionChild, ClickType, mList.get(position));
                    break;
                case R.id.btn_all_around:
                    mItemOnclickListener.clickItem(v, position, positionChild, ClickType, mList.get(position));
                    break;
                case R.id.btn_in:
                    mItemOnclickListener.clickItem(v, position, positionChild, ClickType, mList.get(position));
                    break;
                case R.id.btn_mine:
                    mItemOnclickListener.clickItem(v, position, positionChild, ClickType, mList.get(position));
                    break;
                case R.id.btn_company:
                    mItemOnclickListener.clickItem(v, position, positionChild, ClickType, mList.get(position));
                    break;
                case R.id.btn_user:
                    mItemOnclickListener.clickItem(v, position, positionChild, ClickType, mList.get(position));
                    break;
            }
        }
    }
}
