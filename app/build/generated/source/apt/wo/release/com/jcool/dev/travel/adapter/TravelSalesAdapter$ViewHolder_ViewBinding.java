// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TravelSalesAdapter$ViewHolder_ViewBinding implements Unbinder {
  private TravelSalesAdapter.ViewHolder target;

  @UiThread
  public TravelSalesAdapter$ViewHolder_ViewBinding(TravelSalesAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.image_goods = Utils.findRequiredViewAsType(source, R.id.image_goods, "field 'image_goods'", ImageView.class);
    target.tv_goods_title = Utils.findRequiredViewAsType(source, R.id.tv_goods_title, "field 'tv_goods_title'", TextView.class);
    target.tv_goods_time = Utils.findRequiredViewAsType(source, R.id.tv_goods_time, "field 'tv_goods_time'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.tv_cunt = Utils.findRequiredViewAsType(source, R.id.tv_cunt, "field 'tv_cunt'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TravelSalesAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image_goods = null;
    target.tv_goods_title = null;
    target.tv_goods_time = null;
    target.tv_money = null;
    target.tv_cunt = null;
  }
}
