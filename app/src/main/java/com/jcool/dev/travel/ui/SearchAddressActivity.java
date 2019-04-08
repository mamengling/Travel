package com.jcool.dev.travel.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.ListAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.utils.CityUtil;
import com.jcool.dev.travel.view.ItemListView;

import java.util.ArrayList;
import java.util.List;

public class SearchAddressActivity extends BaseActivity {
    List<String> data = new ArrayList<>();
    List<String> ed_data = new ArrayList<>();
    private ListView myResultList;
    private ListAdapter myResultAdapter;
    private CityUtil cityUtil = new CityUtil();
    private EditText ed_search;
    private TextView emptyView, tv_cancel;
    private ImageView im_cancel;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (ed_data.size() == 0) {
                        myResultList.setVisibility(View.GONE);
                    } else {
                        myResultAdapter = new ListAdapter(SearchAddressActivity.this, ed_data);
                        myResultList.setAdapter(myResultAdapter);
                    }
                    break;
            }
        }
    };


    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected void getExtra() {

    }
    @Override
    protected void initView() {
        tv_cancel = findViewById(R.id.tv_cancel);
        im_cancel = findViewById(R.id.im_cancel);
        emptyView = findViewById(R.id.emptyView);
        myResultList = findViewById(R.id.myResultList);
        myResultAdapter = new ListAdapter(this, data);
        myResultList.setAdapter(myResultAdapter);
        ed_search = findViewById(R.id.ed_search);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] shuju = cityUtil.getmCitiesStrings();
                for (int i = 0; i < shuju.length; i++) {
                    data.add(shuju[i]);
                }
            }
        }).start();
    }

    @Override
    protected void initTools() {

    }

    @Override
    protected void setListener() {
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String keyWord = editable.toString();
                if (TextUtils.isEmpty(keyWord)) {
                    myResultAdapter = new ListAdapter(SearchAddressActivity.this, data);
                    myResultList.setAdapter(myResultAdapter);
                    im_cancel.setVisibility(View.GONE);
                    myResultList.setVisibility(View.VISIBLE);
                } else {
                    ed_data.clear();
                    im_cancel.setVisibility(View.VISIBLE);
                    myResultList.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < data.size(); i++) {
                                if (data.get(i).contains(keyWord)) {
                                    ed_data.add(data.get(i));
                                }
                            }
                            handler.sendEmptyMessage(0);
                        }
                    }).start();
                }
            }
        });
        im_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_search.setText("");
                myResultList.setVisibility(View.VISIBLE);
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        myResultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.putExtra("city", myResultAdapter.getItem(i).toString());
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

}
