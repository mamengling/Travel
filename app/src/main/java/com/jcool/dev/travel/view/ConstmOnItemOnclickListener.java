package com.jcool.dev.travel.view;

import android.view.View;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/6/29 15:57
 * $DESE$
 */
public interface ConstmOnItemOnclickListener<T> {
    public void clickItem(View view, int position, int positionChild, int ClickType, T content);
}
