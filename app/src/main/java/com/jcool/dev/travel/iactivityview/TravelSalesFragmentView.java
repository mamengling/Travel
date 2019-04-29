package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.GoodsBean;

public interface TravelSalesFragmentView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<GoodsBean> mCallBackVo);
}
