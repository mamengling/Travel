package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.VisaInfoDtoList;
import com.jcool.dev.travel.bean.VisaTargetInfo;

import java.util.List;

public interface VisaCommitActivityView extends IBaseActView {
    void excuteSuccessCallBack(CallBackVo<List<VisaTargetInfo>> mCallBackVo);
    void excuteSuccessPersonCallBack(CallBackVo<List<PersonInfoBean>> mCallBackVo);

    void excuteSuccessGoodsCallBack(CallBackVo<List<VisaInfoDtoList.VisaInfoDtoListBean>> mCallBackVoGoods);
}
