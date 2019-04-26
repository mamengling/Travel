package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;

public interface UploadImageFilesView extends IBaseActView {

    void excuteSuccessUploadCallBack(CallBackVo<String> mCallBackVo);
}
