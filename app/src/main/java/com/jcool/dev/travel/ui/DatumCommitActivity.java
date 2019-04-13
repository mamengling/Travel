package com.jcool.dev.travel.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.DatumCommitAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.DataImageInfo;
import com.jcool.dev.travel.bean.GroupBean;
import com.jcool.dev.travel.iactivityview.DatumCommitActivityView;
import com.jcool.dev.travel.iactivityview.UploadImageFilesView;
import com.jcool.dev.travel.persenter.DatumCommitActivityPresenter;
import com.jcool.dev.travel.persenter.UploadImageFilesPresenter;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.GlideLoader;
import com.jcool.dev.travel.utils.MDialog;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.lcw.library.imagepicker.ImagePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 材料提交--身份证
 */
public class DatumCommitActivity extends BaseActivity implements View.OnClickListener, UploadImageFilesView, DatumCommitActivityView {
    private UploadImageFilesPresenter mPresenter;
    private DatumCommitActivityPresenter mDatumPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private DatumCommitAdapter mAdapter;
    private ArrayList<GroupBean> list;
    private String orderId;
    private String custId;
    private static final int BAIDU_READ_PHONE_STATE = 100;
    private ArrayList<String> mImagePaths = new ArrayList<>();
    private String filePath;
    private int psion;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_datum_commit;
    }

    @Override
    protected void getExtra() {
        list = getIntent().getParcelableArrayListExtra("list");
        orderId = getIntent().getStringExtra("orderId");
        custId = getIntent().getStringExtra("custId");
//        ToastUtils.showShortToast("有" + (list == null ? 0 : list.size()) + "条数据");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new UploadImageFilesPresenter(this, this);
        mDatumPresenter = new DatumCommitActivityPresenter(this, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mAdapter = new DatumCommitAdapter(this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.onReference(list);
        mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener<GroupBean>() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, GroupBean content) {
                switch (ClickType) {
                    case 0:
                        psion = position;
                        //判断是否为android6.0系统版本，如果是，需要动态添加权限
                        if (Build.VERSION.SDK_INT >= 23) {
                            showContacts();
                        } else {
                            ImagePicker.getInstance()
                                    .setTitle("上传照片或视频")//设置标题
                                    .showCamera(true)//设置是否显示拍照按钮
                                    .showImage(true)//设置是否展示图片
                                    .showVideo(true)//设置是否展示视频
                                    .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                                    .setImageLoader(new GlideLoader())//设置自定义图片加载器
                                    .start(DatumCommitActivity.this, AppConfigStatic.UPLOAD_IMAGE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
                        }
                        break;
                    case 1:
                        break;
                    case 2:
                        list.get(position).getInfoList().remove(positionChild);
                        break;
                }
            }
        });
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        icon_right.setOnClickListener(this);
    }

    @Override
    protected void initData() {

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
            case R.id.icon_right:
//                mDatumPresenter.addVisaData();
                JSONArray array = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    String dataImage = "";
                    if (list.get(i).getInfoList() != null && list.get(i).getInfoList().size() > 0) {
                        for (int j = 0; j < list.get(i).getInfoList().size(); j++) {
                            dataImage += list.get(i).getInfoList() + ",";
                        }
                    }
                    JSONObject object = new JSONObject();
                    try {//[{orderId:"1222",dataName:"cehshi1",dataDesc:"描述一",dataImage:"2222,11111",dataStatus:"02"},{orderId:"1222",dataName:"cehshi1",dataDesc:"描述一",dataImage:"2222,11111",dataStatus:"02"}]
                        object.put("dataImage", dataImage);
                        object.put("orderId", orderId);
                        object.put("custId", custId);
                        object.put("dataName", list.get(i).getTitle());
                        object.put("dataDesc", list.get(i).getContent());
                        object.put("dataStatus", "03");
                        object.put("index", list.get(i).getIndex());
                        array.put(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mDatumPresenter.addVisaData(array, getToken());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case AppConfigStatic.UPLOAD_IMAGE:
                ArrayList<String> mImage = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
                if (mImage.size() > 0) {
                    filePath = mImage.get(0);
                    mPresenter.upLoadImage(filePath, this);
                }
                break;
        }
    }

    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "没有权限,请手动开启权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(DatumCommitActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, BAIDU_READ_PHONE_STATE);
        } else {
            ImagePicker.getInstance()
                    .setTitle("上传照片或视频")//设置标题
                    .showCamera(true)//设置是否显示拍照按钮
                    .showImage(true)//设置是否展示图片
                    .showVideo(true)//设置是否展示视频
                    .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                    .setImageLoader(new GlideLoader())//设置自定义图片加载器
                    .start(DatumCommitActivity.this, AppConfigStatic.UPLOAD_IMAGE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
        }
    }

    @Override
    public JSONObject getParamenters() {
        return null;
    }

    @Override
    public void showProgress() {
        MDialog.getInstance(this).showProgressDialog();
    }

    @Override
    public void closeProgress() {
        MDialog.getInstance(this).closeProgressDialog();
    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        ToastUtils.showShortToast(mCallBackVo.getMsg());
    }

    @Override
    public void excuteSuccessUploadCallBack(CallBackVo<String> mCallBackVo) {
        if (list.get(psion).getInfoList() != null) {
            list.get(psion).getInfoList().add(mCallBackVo.getData());
        } else {
            List<String> itemList = new ArrayList<>();
            itemList.add(mCallBackVo.getData());
            list.get(psion).setInfoList(itemList);
        }
        mAdapter.onReference(list);
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<String> mCallBackVo) {
        ToastUtils.showShortToast(mCallBackVo.getMsg());
        finish();
    }
}
