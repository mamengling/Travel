package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CityBeanHot;

import java.util.List;

public interface SearchKeyActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<List<CityBeanHot>> mCallBackVo);
}
