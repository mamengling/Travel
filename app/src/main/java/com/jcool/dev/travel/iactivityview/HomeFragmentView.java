package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.BannerBean;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.GoodsBean;

import java.util.List;

public interface HomeFragmentView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<List<BannerBean>> mCallBackVo, CallBackVo<GoodsBean> goodsCallBackVo);
}
