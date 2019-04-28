package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.iactivityview.CalendarActivityView;
import com.jcool.dev.travel.persenter.CalendarActivityPresenter;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarActivity extends BaseActivity implements View.OnClickListener, CalendarActivityView, CalendarView.OnDateSelectedListener {
    private CalendarActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.radiogroup_full)
    RadioGroup radiogroup_full;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_number_da)
    TextView tv_number_da;
    @BindView(R.id.tv_jian_da)
    TextView tv_jian_da;
    @BindView(R.id.tv_jia_da)
    TextView tv_jia_da;
    @BindView(R.id.tv_jian)
    TextView tv_jian;
    @BindView(R.id.tv_jia)
    TextView tv_jia;
    @BindView(R.id.tv_number_xiaohai)
    TextView tv_number_xiaohai;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.tv_buy)
    TextView tv_buy;
    @BindView(R.id.math1)
    RadioButton math1;
    @BindView(R.id.math2)
    RadioButton math2;
    @BindView(R.id.math3)
    RadioButton math3;
    private String travelId;
    private String onDay = "";
    private String dateBegin;
    private String dateEnd;
    private double money = 0;
    private int bigNumber = 1;
    private int xiaoNumber = 1;
    private double priceOne;
    private String goodsDate;
    private String travelName;
    private String priceNow;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_calender;
    }

    @Override
    protected void getExtra() {
        travelId = getIntent().getStringExtra("travelId");
        travelName = getIntent().getStringExtra("travelName");
        priceNow = getIntent().getStringExtra("priceNow");
        goodsDate = getIntent().getStringExtra("goodsDate");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        dateBegin = AppUtils.getTimeAtt("yyyy-MM-dd");
        dateEnd = AppUtils.dayAdd(100);
        mPresenter = new CalendarActivityPresenter(this, this);
        if (TextUtils.isEmpty(priceNow)) {
            math1.setText(AppUtils.getTimeAtt("yyyy-MM-dd"));
            math2.setText((Integer.parseInt(AppUtils.getTimeAtt("MM")) + 1) + "月");
            math3.setText((Integer.parseInt(AppUtils.getTimeAtt("MM")) + 2) + "月");
        } else {
            String strExpress = "<font color=\"#333333\">" + AppUtils.getTimeAtt("yyyy-MM-dd") + "<br/>" + "  </font><font color=\"#ff0000\">" + "¥" + priceNow + "</font>";
            math1.setText(Html.fromHtml(strExpress));

            String strExpress1 = "<font color=\"#333333\">" + (Integer.parseInt(AppUtils.getTimeAtt("MM")) + 1) + "月" + "<br/>" + "  </font><font color=\"#ff0000\">" + "¥" + priceNow + "</font>";
            math2.setText(Html.fromHtml(strExpress1));

            String strExpress2 = "<font color=\"#333333\">" + (Integer.parseInt(AppUtils.getTimeAtt("MM")) + 2) + "月" + "<br/>" + "  </font><font color=\"#ff0000\">" + "¥" + priceNow + "</font>";
            math3.setText(Html.fromHtml(strExpress2));
        }
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        tv_jian_da.setOnClickListener(this);
        tv_jia_da.setOnClickListener(this);
        tv_jian.setOnClickListener(this);
        tv_jia.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        tv_message.setOnClickListener(this);
        calendarView.setOnDateSelectedListener(this);
        radiogroup_full.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.math1:
                        calendarView.scrollToCurrent();
                        break;
                    case R.id.math2:
                        calendarView.scrollToNext();
                        break;
                    case R.id.math3:
                        calendarView.scrollToNext();
                        calendarView.scrollToNext();
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.journeyTravelTimeList(travelId);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_title_back:
                finish();
                break;
            case R.id.tv_buy:
                if (TextUtils.isEmpty(goodsDate) || priceOne == 0) {
                    ToastUtils.showShortToast("请选择班期");
                } else {
                    Intent intent = new Intent(CalendarActivity.this, CreateTravelOrderActivity.class);
                    intent.putExtra("travelId", travelId);
                    intent.putExtra("goodsDate", goodsDate + "");
                    intent.putExtra("childrenNumber", xiaoNumber + "");
                    intent.putExtra("adultNumber", bigNumber + "");
                    intent.putExtra("money", money);
                    startActivity(intent);
                }
                break;
            case R.id.tv_jian_da:
                int jian = Integer.parseInt(tv_number_da.getText().toString());
                if (jian > 0) {
                    jian -= 1;
                    tv_number_da.setText(jian + "");
                }
                getMoney();
                break;
            case R.id.tv_jia_da:
                int big = Integer.parseInt(tv_number_da.getText().toString());
                big += 1;
                tv_number_da.setText(big + "");
                getMoney();
                break;
            case R.id.tv_jian:
                int jianxiao = Integer.parseInt(tv_number_xiaohai.getText().toString());
                if (jianxiao > 0) {
                    jianxiao -= 1;
                    tv_number_xiaohai.setText(jianxiao + "");
                }
                getMoney();
                break;
            case R.id.tv_jia:
                int xiaojia = Integer.parseInt(tv_number_xiaohai.getText().toString());
                xiaojia += 1;
                tv_number_xiaohai.setText(xiaojia + "");
                getMoney();
                break;
            case R.id.tv_call_phone:
                callPhone(getUserPhone());
                break;
            case R.id.tv_chat:
                Intent intentChat = new Intent(this, WebviewDefulitActivity.class);
                intentChat.putExtra("loadUrl", "http://p.qiao.baidu.com/cps/chatIndex");
                intentChat.putExtra("title", "在线客服");
                startActivity(intentChat);
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("goodsId", travelId);
            object.put("dateBegin", dateBegin);
            object.put("dateEnd", dateEnd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public void excuteSuccessGoodsCallBack(CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo) {
        List<Calendar> schemes = new ArrayList<>();
        int year = calendarView.getCurYear();
        int month = calendarView.getCurMonth();
        for (int i = 0; i < mCallBackVo.getData().size(); i++) {
            int day = Integer.parseInt(AppUtils.getDate2String(AppUtils.getString2Date(mCallBackVo.getData().get(i).getGoodsDate(), "yyyy-MM-dd"), "dd"));
            schemes.add(getSchemeCalendar(year, month, day, 0xFFFFD665, "班"));
            calendarView.setSchemeDate(schemes);
        }
        calendarView.setSchemeDate(schemes);
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            priceOne = Double.parseDouble(mCallBackVo.getData().get(0).getPriceNow());
            goodsDate = mCallBackVo.getData().get(0).getId();
            getMoney();
        } else {
            priceOne = 0;
            getMoney();
        }

    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setLunar("十二");
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        if (isClick) {
            dateBegin = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay() + "";
            dateEnd = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay() + "";
            mPresenter.journeyTravelTime(travelId);
        }
    }

    private void getMoney() {
        bigNumber = Integer.parseInt(tv_number_da.getText().toString());
        xiaoNumber = Integer.parseInt(tv_number_xiaohai.getText().toString());
        money = priceOne * (bigNumber + xiaoNumber);
        tv_money.setText("金额：" + money);
    }

}
