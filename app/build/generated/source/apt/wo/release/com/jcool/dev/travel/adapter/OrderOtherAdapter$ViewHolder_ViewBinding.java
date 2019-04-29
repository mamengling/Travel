// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderOtherAdapter$ViewHolder_ViewBinding implements Unbinder {
  private OrderOtherAdapter.ViewHolder target;

  @UiThread
  public OrderOtherAdapter$ViewHolder_ViewBinding(OrderOtherAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.tv_shop = Utils.findRequiredViewAsType(source, R.id.tv_shop, "field 'tv_shop'", TextView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.tv_order_number = Utils.findRequiredViewAsType(source, R.id.tv_order_number, "field 'tv_order_number'", TextView.class);
    target.tv_order_status = Utils.findRequiredViewAsType(source, R.id.tv_order_status, "field 'tv_order_status'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderOtherAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_time = null;
    target.tv_shop = null;
    target.tv_name = null;
    target.tv_order_number = null;
    target.tv_order_status = null;
  }
}
