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

public class HistoryGoodsAdapter$ViewHolder_ViewBinding implements Unbinder {
  private HistoryGoodsAdapter.ViewHolder target;

  @UiThread
  public HistoryGoodsAdapter$ViewHolder_ViewBinding(HistoryGoodsAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.image_goods = Utils.findRequiredViewAsType(source, R.id.image_goods, "field 'image_goods'", ImageView.class);
    target.tv_goods_title = Utils.findRequiredViewAsType(source, R.id.tv_goods_title, "field 'tv_goods_title'", TextView.class);
    target.tv_goods_day = Utils.findRequiredViewAsType(source, R.id.tv_goods_day, "field 'tv_goods_day'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HistoryGoodsAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image_goods = null;
    target.tv_goods_title = null;
    target.tv_goods_day = null;
    target.tv_money = null;
  }
}
