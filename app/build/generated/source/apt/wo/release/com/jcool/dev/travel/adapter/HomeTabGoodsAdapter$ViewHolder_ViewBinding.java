// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeTabGoodsAdapter$ViewHolder_ViewBinding implements Unbinder {
  private HomeTabGoodsAdapter.ViewHolder target;

  @UiThread
  public HomeTabGoodsAdapter$ViewHolder_ViewBinding(HomeTabGoodsAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.givImage = Utils.findRequiredViewAsType(source, R.id.image_goods, "field 'givImage'", ImageView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_goods_title, "field 'tvTitle'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.tv_more = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'tv_more'", TextView.class);
    target.info = Utils.findRequiredViewAsType(source, R.id.info, "field 'info'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeTabGoodsAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.givImage = null;
    target.tvTitle = null;
    target.tv_money = null;
    target.tv_more = null;
    target.info = null;
  }
}