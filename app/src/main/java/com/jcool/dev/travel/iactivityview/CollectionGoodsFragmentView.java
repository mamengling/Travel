package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelBean;

public interface CollectionGoodsFragmentView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<TravelBean> mCallBackVo);
}
