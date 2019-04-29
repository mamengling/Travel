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
import java.lang.IllegalStateException;
import java.lang.Override;

public class TravelOrderDetailActivity_ViewBinding implements Unbinder {
  private TravelOrderDetailActivity target;

  @UiThread
  public TravelOrderDetailActivity_ViewBinding(TravelOrderDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TravelOrderDetailActivity_ViewBinding(TravelOrderDetailActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right_one = Utils.findRequiredViewAsType(source, R.id.icon_right_one, "field 'icon_right_one'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_order_status = Utils.findRequiredViewAsType(source, R.id.tv_order_status, "field 'tv_order_status'", TextView.class);
    target.tv_order_number = Utils.findRequiredViewAsType(source, R.id.tv_order_number, "field 'tv_order_number'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.tv_travel_name = Utils.findRequiredViewAsType(source, R.id.tv_travel_name, "field 'tv_travel_name'", TextView.class);
    target.tv_data_day01 = Utils.findRequiredViewAsType(source, R.id.tv_data_day01, "field 'tv_data_day01'", TextView.class);
    target.tv_data_day02 = Utils.findRequiredViewAsType(source, R.id.tv_data_day02, "field 'tv_data_day02'", TextView.class);
    target.tv_week_day01 = Utils.findRequiredViewAsType(source, R.id.tv_week_day01, "field 'tv_week_day01'", TextView.class);
    target.tv_week_day = Utils.findRequiredViewAsType(source, R.id.tv_week_day, "field 'tv_week_day'", TextView.class);
    target.tv_data_day = Utils.findRequiredViewAsType(source, R.id.tv_data_day, "field 'tv_data_day'", TextView.class);
    target.tv_user_number = Utils.findRequiredViewAsType(source, R.id.tv_user_number, "field 'tv_user_number'", TextView.class);
    target.tv_link_name = Utils.findRequiredViewAsType(source, R.id.tv_link_name, "field 'tv_link_name'", TextView.class);
    target.tv_link_phone = Utils.findRequiredViewAsType(source, R.id.tv_link_phone, "field 'tv_link_phone'", TextView.class);
    target.tv_link_email = Utils.findRequiredViewAsType(source, R.id.tv_link_email, "field 'tv_link_email'", TextView.class);
    target.tv_btn_right = Utils.findRequiredViewAsType(source, R.id.tv_btn_right, "field 'tv_btn_right'", TextView.class);
    target.tv_btn_life = Utils.findRequiredViewAsType(source, R.id.tv_btn_life, "field 'tv_btn_life'", TextView.class);
    target.tv_btn_center = Utils.findRequiredViewAsType(source, R.id.tv_btn_center, "field 'tv_btn_center'", TextView.class);
    target.line_user = Utils.findRequiredViewAsType(source, R.id.line_user, "field 'line_user'", LinearLayout.class);
    target.rela_travel_info = Utils.findRequiredViewAsType(source, R.id.rela_travel_info, "field 'rela_travel_info'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TravelOrderDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right_one = null;
    target.icon_right = null;
    target.tv_order_status = null;
    target.tv_order_number = null;
    target.tv_money = null;
    target.tv_travel_name = null;
    target.tv_data_day01 = null;
    target.tv_data_day02 = null;
    target.tv_week_day01 = null;
    target.tv_week_day = null;
    target.tv_data_day = null;
    target.tv_user_number = null;
    target.tv_link_name = null;
    target.tv_link_phone = null;
    target.tv_link_email = null;
    target.tv_btn_right = null;
    target.tv_btn_life = null;
    target.tv_btn_center = null;
    target.line_user = null;
    target.rela_travel_info = null;
  }
}
