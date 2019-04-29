package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.IconAdapter;
import com.jcool.dev.travel.adapter.ListIconAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.HomeIconBean;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.iactivityview.UserInfoGetView;
import com.jcool.dev.travel.persenter.UserInfoGetPresenter;
import com.jcool.dev.travel.ui.CollectionActivity;
import com.jcool.dev.travel.ui.LoginActivity;
import com.jcool.dev.travel.ui.MianhisToryActivity;
import com.jcool.dev.travel.ui.OrderTabActivity;
import com.jcool.dev.travel.ui.PeopleListActivity;
import com.jcool.dev.travel.ui.SettingActivity;
import com.jcool.dev.travel.ui.UserInfoActivity;
import com.jcool.dev.travel.ui.VisaCommitActivity;
import com.jcool.dev.travel.ui.YijianDefulitActivity;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.FixedGridView;
import com.jcool.dev.travel.view.ItemListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心
 */
public class UserFragment extends BaseFragment implements View.OnClickListener, UserInfoGetView {
    private UserInfoGetPresenter mPresenter;
    private IconAdapter mAdapterIcon;
    private ListIconAdapter mAdapterIconList;
    private String iconArr[] = {"全部订单", "待付款", "待出行", "待评价"};
//    private String listArr[] = {"常用联系人", "我的收藏", "浏览历史", "关于我们", "意见反馈", "其他渠道签证资料提交"};
    private String listArr[] = {"常用联系人", "我的收藏", "浏览历史",  "意见反馈", "其他渠道签证资料提交"};
    private int iconImage[] = {R.mipmap.icon_order_logo_all, R.mipmap.icon_order_logo_dfk, R.mipmap.icon_order_logo_dcx, R.mipmap.icon_order_logo_dpj};
    private int iconBg[] = {R.drawable.x_image_icon_bg_round_yellow, R.drawable.x_image_icon_bg_round_red, R.drawable.x_image_icon_bg_round_green, R.drawable.x_image_icon_bg_round_blue};
    private List<HomeIconBean> mListIcon;
    private List<HomeIconBean> mListIconList;
    private FixedGridView fixedGridView;
    private ItemListView itemListView;
    private SmartRefreshLayout refreshLayout;
    private TextView tv_user_name;
    private TextView tv_login;
    private ImageView image_head;
    private TextView icon_back;
    private TextView icon_right;
    private TextView tv_title;

    public static UserFragment newInstance() {

        Bundle args = new Bundle();

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //隐藏时所作的事情
        } else {
            if (isLogin() && getUserId() != null) {
                tv_login.setVisibility(View.GONE);
                tv_user_name.setVisibility(View.VISIBLE);
                mPresenter.getUserInfo(getUserId() + "");
            } else {
                tv_user_name.setVisibility(View.GONE);
                tv_login.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView(View view) {
        refreshLayout = view.findViewById(R.id.refreshLayout);
        tv_user_name = view.findViewById(R.id.tv_user_name);
        image_head = view.findViewById(R.id.image_head);
        fixedGridView = view.findViewById(R.id.fixedGridView);
        itemListView = view.findViewById(R.id.itemListView);
        icon_back = view.findViewById(R.id.icon_back);
        icon_right = view.findViewById(R.id.icon_right);
        tv_title = view.findViewById(R.id.tv_title);
        tv_login = view.findViewById(R.id.tv_login);

    }

    @Override
    protected void initTools() {
        mPresenter = new UserInfoGetPresenter(this, getContext());
        tv_title.setText("个人中心");
        icon_back.setVisibility(View.GONE);
        if (isAdded()) {
            icon_right.setCompoundDrawablesWithIntrinsicBounds(null, null, getContext().getResources().getDrawable(R.mipmap.icon_user_info_set), null);
        }
//        ImageLoaderUtils.showImageViewToCircle(getContext(), "http://img5.duitang.com/uploads/item/201512/18/20151218165511_AQW4B.jpeg", image_head, R.mipmap.icon_default_head);
        refreshLayout.setEnableRefresh(false);
        mListIcon = new ArrayList<>();
        for (int i = 0; i < iconArr.length; i++) {
            HomeIconBean item = new HomeIconBean();
            item.setName(iconArr[i]);
            item.setBg(iconBg[i]);
            item.setIconId(iconImage[i]);
            mListIcon.add(item);
        }
        mAdapterIcon = new IconAdapter(mListIcon, getContext());
        fixedGridView.setAdapter(mAdapterIcon);
        mListIconList = new ArrayList<>();
        for (int i = 0; i < listArr.length; i++) {
            HomeIconBean item = new HomeIconBean();
            item.setName(listArr[i]);
            mListIconList.add(item);
        }
        mAdapterIconList = new ListIconAdapter(mListIconList, getContext());
        itemListView.setAdapter(mAdapterIconList);
    }

    @Override
    protected void initData() {
        if (isLogin() && getUserId() != null) {
            tv_login.setVisibility(View.GONE);
            tv_user_name.setVisibility(View.VISIBLE);
            mPresenter.getUserInfo(getUserId() + "");
        } else {
            tv_user_name.setVisibility(View.GONE);
            tv_login.setVisibility(View.VISIBLE);
            image_head.setImageResource(R.mipmap.icon_default_head);
        }
    }

    @Override
    protected void setListener() {
        icon_right.setOnClickListener(this);
        image_head.setOnClickListener(this);
        tv_user_name.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        fixedGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isLogin()) {
                    Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                    intentLogin.putExtra("", "");
                    getContext().startActivity(intentLogin);
                    return;
                }
                Intent intent = new Intent(getContext(), OrderTabActivity.class);
                intent.putExtra("number", position);
                startActivity(intent);
            }
        });
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!isLogin()) {
                    Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                    intentLogin.putExtra("", "");
                    getContext().startActivity(intentLogin);
                    return;
                }
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getContext(), PeopleListActivity.class);//联系人
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intentColloct = new Intent(getContext(), CollectionActivity.class);//收藏
                        startActivity(intentColloct);
                        break;
                    case 2:
                        Intent intentH = new Intent(getContext(), MianhisToryActivity.class);//浏览历史
                        startActivity(intentH);
                        break;
//                    case 3:
//                        Intent intentYj = new Intent(getContext(), WebviewDefulitActivity.class);
//                        intentYj.putExtra("loadUrl", getAboutUrl());
//                        intentYj.putExtra("title", "关于我们");
//                        startActivity(intentYj);
//                        break;
                    case 3:
                        Intent intentMess = new Intent(getContext(), YijianDefulitActivity.class);//意见反馈
                        startActivity(intentMess);
                        break;
                    case 4:
                        Intent intentCommit = new Intent(getContext(), VisaCommitActivity.class);//其他渠道
                        startActivity(intentCommit);
                        break;
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_right:
                Intent intentSet = new Intent(getContext(), SettingActivity.class);
                intentSet.putExtra("", "");
                getContext().startActivity(intentSet);
                break;
            case R.id.tv_login:
                Intent intentLogin = new Intent(getContext(), LoginActivity.class);
                intentLogin.putExtra("", "");
                getContext().startActivity(intentLogin);
                break;
            case R.id.image_head:
            case R.id.tv_user_name:
                Intent intentUser = new Intent(getContext(), UserInfoActivity.class);
                intentUser.putExtra("", "");
                getContext().startActivity(intentUser);
                break;
            default:
                break;
        }
    }


    @Override
    public JSONObject getParamenters() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        ToastUtils.showShortToast("失败：" + mCallBackVo.getMsg());
    }

    @Override
    public void excuteSuccessUserCallBack(CallBackVo<UserInfo.UserInfoBean.SysUserBean> mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            setUserInfo(mCallBackVo.getData());
            if (TextUtils.isEmpty(mCallBackVo.getData().getNickname())) {
                tv_user_name.setText(mCallBackVo.getData().getUsername());
            } else {
                tv_user_name.setText(mCallBackVo.getData().getNickname());
            }
            ImageLoaderUtils.showImageViewToCircle(getContext(), mCallBackVo.getData().getAvatar(), image_head, R.mipmap.icon_default_head);
        }
    }

    @Override
    public void excuteSuccessUpdateCallBack(CallBackVo mCallBackVo) {

    }
}
