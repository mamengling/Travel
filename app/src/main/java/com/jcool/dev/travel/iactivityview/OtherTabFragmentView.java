package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderInfoOthBean;

import java.util.List;

public interface OtherTabFragmentView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<List<OrderInfoOthBean>> mCallBackVo);
}
