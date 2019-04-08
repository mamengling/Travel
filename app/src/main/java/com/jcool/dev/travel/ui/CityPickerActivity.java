package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.CYBChangeCityGridViewAdapter;
import com.jcool.dev.travel.adapter.ContactAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.UserEntity;
import com.jcool.dev.travel.utils.CityUtil;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.QGridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableHeaderAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * Created by guce on 2017/11/25.
 */

public class CityPickerActivity extends BaseActivity implements View.OnClickListener {
    private ContactAdapter mAdapter;
    private BannerHeaderAdapter mBannerHeaderAdapter;
    private String[] city = {"北京", "上海", "广州", "深圳", "成都", "杭州"};
    private IndexableLayout indexableLayout;
    private CYBChangeCityGridViewAdapter cybChangeCityGridViewAdapter;
    private ArrayList<String> list;
    private ImageView pic_contact_back;
    private TextView tv_search;
    private LinearLayout ll_dingwei;
    private TextView tv_dingwei;
    private CityUtil cityUtil = new CityUtil();

    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_pick_contact;
    }

    @Override
    protected void getExtra() {

    }
    @Override
    protected void initView() {
        tv_dingwei = findViewById(R.id.tv_dingwei);
        pic_contact_back = findViewById(R.id.pic_contact_back);
        ll_dingwei = findViewById(R.id.ll_dingwei);
        indexableLayout = findViewById(R.id.indexableLayout);
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        tv_search = findViewById(R.id.tv_search);
    }

    @Override
    protected void initTools() {
        initAdapter();
        onlisten();
    }

    @Override
    protected void setListener() {
        ll_dingwei.setOnClickListener(this);
        tv_search.setOnClickListener(this);
        tv_dingwei.setText(getIntent().getStringExtra("city"));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    public void initAdapter() {
        mAdapter = new ContactAdapter(this);
        indexableLayout.setAdapter(mAdapter);
        indexableLayout.setOverlayStyle_Center();
        mAdapter.setDatas(initDatas());
//        indexableLayout.setOverlayStyle_MaterialDesign(Color.RED);
        // 全字母排序。  排序规则设置为：每个字母都会进行比较排序；速度较慢
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
//        indexableLayout.addHeaderAdapter(new SimpleHeaderAdapter<>(mAdapter, "☆",null, null));

//         构造函数里3个参数,分别对应 (IndexBar的字母索引, IndexTitle, 数据源), 不想显示哪个就传null, 数据源传null时,代表add一个普通的View
//        mMenuHeaderAdapter = new MenuHeaderAdapter("↑", null, initMenuDatas());
//        indexableLayout.addHeaderAdapter(mMenuHeaderAdapter);

        // 这里BannerView只有一个Item, 添加一个长度为1的任意List作为第三个参数
        List<String> bannerList = new ArrayList<>();
        bannerList.add("");
        mBannerHeaderAdapter = new BannerHeaderAdapter("↑", null, bannerList);
        indexableLayout.addHeaderAdapter(mBannerHeaderAdapter);
    }


    private void onlisten() {

        pic_contact_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter.setOnItemContentClickListener(new IndexableAdapter.OnItemContentClickListener<UserEntity>() {
            @Override
            public void onItemClick(View v, int originalPosition, int currentPosition, UserEntity entity) {
                if (originalPosition >= 0) {
                    Intent intent = new Intent();
                    intent.putExtra("city", entity.getNick());
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    ToastUtils.showLongToast("选中Header/Footer:" + entity.getNick() + "  当前位置:" + currentPosition);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_dingwei:
                Intent intent = new Intent();
                intent.putExtra("city", tv_dingwei.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_search:
                Intent intentSearch = new Intent(CityPickerActivity.this, SearchAddressActivity.class);
                startActivityForResult(intentSearch, 101);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Intent intent = new Intent();
            intent.putExtra("city", data.getStringExtra("city"));
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    /**
     * 自定义的Banner Header
     */
    class BannerHeaderAdapter extends IndexableHeaderAdapter {
        private static final int TYPE = 1;

        public BannerHeaderAdapter(String index, String indexTitle, List datas) {
            super(index, indexTitle, datas);
        }

        @Override
        public int getItemViewType() {
            return TYPE;
        }

        @Override
        public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(CityPickerActivity.this).inflate(R.layout.item_city_header, parent, false);
            VH holder = new VH(view);
            return holder;
        }

        @Override
        public void onBindContentViewHolder(RecyclerView.ViewHolder holder, Object entity) {
            // 数据源为null时, 该方法不用实现
            final VH vh = (VH) holder;
            list = new ArrayList<>();
            for (int i = 0; i < city.length; i++) {
                list.add(city[i]);
            }
            System.out.println("------------city" + list);
            cybChangeCityGridViewAdapter = new CYBChangeCityGridViewAdapter(CityPickerActivity.this, list);
            vh.head_home_change_city_gridview.setAdapter(cybChangeCityGridViewAdapter);
            vh.head_home_change_city_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra("city", list.get(position));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });

        }

        private class VH extends RecyclerView.ViewHolder {
            GridView head_home_change_city_gridview;

            public VH(View itemView) {
                super(itemView);
                head_home_change_city_gridview = (QGridView) itemView.findViewById(R.id.item_header_city_gridview);

            }
        }
    }

    private List<UserEntity> initDatas() {
        List<UserEntity> list = new ArrayList<>();
        // 初始化数据
        List<String> contactStrings = Arrays.asList(cityUtil.getmCitiesStrings());
        List<String> mobileStrings = Arrays.asList(cityUtil.getmCitiesStrings());
        for (int i = 0; i < contactStrings.size(); i++) {
            UserEntity contactEntity = new UserEntity(contactStrings.get(i), mobileStrings.get(i));
            list.add(contactEntity);
        }
        return list;
    }

}
