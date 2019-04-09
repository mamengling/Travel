package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaInfoDtoList;

import java.util.List;

public interface VisaFragmentView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<List<VisaInfoDtoList>> mCallBackVo);
}
