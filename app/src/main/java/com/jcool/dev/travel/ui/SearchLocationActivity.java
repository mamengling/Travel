package com.jcool.dev.travel.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.SearchAddressAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.LocationInfoBean;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.AppTravelLocation;
import com.jcool.dev.travel.utils.CityUtil;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.log.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class SearchLocationActivity extends BaseActivity implements View.OnClickListener, Inputtips.InputtipsListener, AMapLocationListener, PoiSearch.OnPoiSearchListener {
    List<String> data = new ArrayList<>();
    List<String> ed_data = new ArrayList<>();
    private RecyclerView myResultList;
    private SearchAddressAdapter mAdapter;
    private CityUtil cityUtil = new CityUtil();
    private EditText ed_search;
    private TextView emptyView, tv_cancel;
    private ImageView im_cancel;
    private PoiSearch search;
    private String city;
    private double isFirstLoc = 0;
    private ArrayList<LocationInfoBean> locationList = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (locationList.size() >= 0) {
                        myResultList.setVisibility(View.VISIBLE);
                        myResultList.scrollToPosition(0);
                        mAdapter.onReference(locationList);
                    } else {
                        myResultList.setVisibility(View.GONE);
                    }
                    break;
                case 1:
                    break;
            }
        }
    };
    private String district;


    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_search_loaction;
    }

    @Override
    protected void getExtra() {
        AppTravelLocation.getInstance(SearchLocationActivity.this).InitLocation(this);
    }

    @Override
    protected void initView() {
        ed_search = findViewById(R.id.ed_search);
        myResultList = findViewById(R.id.recycler_view_poi);
        tv_cancel = findViewById(R.id.tv_cancel);
    }

    @Override
    protected void initTools() {

    }

    @Override
    protected void setListener() {
        tv_cancel.setOnClickListener(this);
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchPoi(ed_search.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myResultList.setLayoutManager(layoutManager);
        mAdapter = new SearchAddressAdapter(this);
        myResultList.setAdapter(mAdapter);
        mAdapter.setOnclick(new SearchAddressAdapter.ClickInterface() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("locationAddress", city + district + locationList.get(position).getAddress());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    /**
     * 进行搜索
     */
    private void searchPoi(String searchInfo) {
        if (TextUtils.isEmpty(city)) {
        } else {
            InputtipsQuery query = new InputtipsQuery(searchInfo, city);
            Inputtips inputTips = new Inputtips(this, query);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
        }
    }

    @Override
    public void onGetInputtips(List<Tip> list, int i) {
        switch (i) {
            //正常
            case 1000:
                locationList.clear();
                for (Tip item : list) {
                    //过滤没有经纬度的 和 没有地址描述的
                    if (item.getPoint() != null && !TextUtils.isEmpty(item.getAddress())) {
                        LocationInfoBean info = new LocationInfoBean();
                        info.setAddress(item.getName());
                        info.setAddressSearch(item.getAddress());
                        LatLonPoint point = item.getPoint();
                        info.setLatitude(point.getLatitude());
                        info.setLongitude(point.getLongitude());
                        locationList.add(info);
                        Log.e("haha   GetInput", item.toString());
                    }
                }
                handler.sendEmptyMessage(0);
                break;
                /*
                1802 连接超时 - SocketTimeoutException
                1803 url异常 - MalformedURLException
                1804 未知主机 - UnKnowHostException
                1806 http或socket连接失败 - ConnectionException
                 */
            case 1802:
            case 1803:
            case 1804:
            case 1806:
                break;

            default:
                break;

        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                AppConfigStatic.APP_AMAP_LATITUDE = aMapLocation.getLatitude();//获取纬度
                AppConfigStatic.APP_AMAP_LONGITUDE = aMapLocation.getLongitude();//获取经度
                AppConfigStatic.APP_AMAP_CITY = aMapLocation.getCity();//获取城市
                city = aMapLocation.getCity();
                district = aMapLocation.getDistrict();
                Log.d("haha  onLocation", "isFirstLoc   getLatitude" + aMapLocation.getLatitude() + "getLongitude" + aMapLocation.getLongitude()+aMapLocation.toString());
                if (isFirstLoc == 0) {
                    isFirstLoc = 1;
                    Log.d("haha  onLocation", "isFirstLoc   getLatitude" + aMapLocation.getLatitude() + "getLongitude" + aMapLocation.getLongitude());
                    PoiSearch.Query query = new PoiSearch.Query("", "生活服务", "");
                    query.setPageSize(20);
                    search = new PoiSearch(this, query);
                    search.setBound(new PoiSearch.SearchBound(new LatLonPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 10000));
                    search.setOnPoiSearchListener(this);
                    search.searchPOIAsyn();
                }
            }
            LogUtil.i("LOCATION", "\n*****\n當前经度：" + aMapLocation.getLongitude() + "\n当前维度：" + aMapLocation.getLatitude() + "\n当前城市：" + aMapLocation.getCity() + "\n城市代码：" + aMapLocation.getCityCode() + "\n******");
        } else {

        }
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        locationList.clear();
        PoiSearch.Query query = poiResult.getQuery();
        ArrayList<PoiItem> pois = poiResult.getPois();
        for (PoiItem poi : pois) {
            String name = poi.getCityName();
            String snippet = poi.getSnippet();
            LocationInfoBean info = new LocationInfoBean();
            info.setAddress(poi.getTitle());
            info.setAddressSearch(poi.getSnippet());
            LatLonPoint point = poi.getLatLonPoint();

            info.setLatitude(point.getLatitude());
            info.setLongitude(point.getLongitude());
            locationList.add(info);
            Log.d("haha PoiSearch", "poi  snippet" + snippet + "getTitle" + poi.getTitle() + "getDistance" + poi.getDistance() + "getPostcode" + poi.getPostcode());
        }
        handler.sendEmptyMessage(0);
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }
}
