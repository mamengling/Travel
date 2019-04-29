// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisaOrderDetailActivity_ViewBinding implements Unbinder {
  private VisaOrderDetailActivity target;

  @UiThread
  public VisaOrderDetailActivity_ViewBinding(VisaOrderDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VisaOrderDetailActivity_ViewBinding(VisaOrderDetailActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_order_status = Utils.findRequiredViewAsType(source, R.id.tv_order_status, "field 'tv_order_status'", TextView.class);
    target.tv_order_number = Utils.findRequiredViewAsType(source, R.id.tv_order_number, "field 'tv_order_number'", TextView.class);
    target.tv_visa_name = Utils.findRequiredViewAsType(source, R.id.tv_visa_name, "field 'tv_visa_name'", TextView.class);
    target.tv_visa_time = Utils.findRequiredViewAsType(source, R.id.tv_visa_time, "field 'tv_visa_time'", TextView.class);
    target.tv_count = Utils.findRequiredViewAsType(source, R.id.tv_count, "field 'tv_count'", TextView.class);
    target.tv_days = Utils.findRequiredViewAsType(source, R.id.tv_days, "field 'tv_days'", TextView.class);
    target.tv_link_name = Utils.findRequiredViewAsType(source, R.id.tv_link_name, "field 'tv_link_name'", TextView.class);
    target.tv_link_phone = Utils.findRequiredViewAsType(source, R.id.tv_link_phone, "field 'tv_link_phone'", TextView.class);
    target.tv_link_email = Utils.findRequiredViewAsType(source, R.id.tv_link_email, "field 'tv_link_email'", TextView.class);
    target.line_user = Utils.findRequiredViewAsType(source, R.id.line_user, "field 'line_user'", LinearLayout.class);
    target.visa_info = Utils.findRequiredViewAsType(source, R.id.visa_info, "field 'visa_info'", RelativeLayout.class);
    target.tv_btn_right = Utils.findRequiredViewAsType(source, R.id.tv_btn_right, "field 'tv_btn_right'", TextView.class);
    target.tv_btn_life = Utils.findRequiredViewAsType(source, R.id.tv_btn_life, "field 'tv_btn_life'", TextView.class);
    target.tv_btn_center = Utils.findRequiredViewAsType(source, R.id.tv_btn_center, "field 'tv_btn_center'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaOrderDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_order_status = null;
    target.tv_order_number = null;
    target.tv_visa_name = null;
    target.tv_visa_time = null;
    target.tv_count = null;
    target.tv_days = null;
    target.tv_link_name = null;
    target.tv_link_phone = null;
    target.tv_link_email = null;
    target.line_user = null;
    target.visa_info = null;
    target.tv_btn_right = null;
    target.tv_btn_life = null;
    target.tv_btn_center = null;
    target.tv_money = null;
    target.refreshLayout = null;
  }
}
