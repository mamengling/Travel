package com.jcool.dev.travel.iactivityview;

import com.jcool.dev.travel.base.IBaseActView;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;

import java.util.List;

public interface PeopleListActivityView extends IBaseActView {
    void excuteSuccessPersonCallBack(CallBackVo<List<PersonInfoBean>> mCallBackVo);

    void excuteSuccessCallBack(CallBackVo<String> mCallBackVo);
}
