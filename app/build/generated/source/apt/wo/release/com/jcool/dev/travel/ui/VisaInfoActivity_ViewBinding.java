// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisaInfoActivity_ViewBinding implements Unbinder {
  private VisaInfoActivity target;

  @UiThread
  public VisaInfoActivity_ViewBinding(VisaInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VisaInfoActivity_ViewBinding(VisaInfoActivity target, View source) {
    this.target = target;

    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_persone = Utils.findRequiredViewAsType(source, R.id.tv_persone, "field 'tv_persone'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.tv_goods_title = Utils.findRequiredViewAsType(source, R.id.tv_goods_title, "field 'tv_goods_title'", TextView.class);
    target.tv_goods_day = Utils.findRequiredViewAsType(source, R.id.tv_goods_day, "field 'tv_goods_day'", TextView.class);
    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.tv_place = Utils.findRequiredViewAsType(source, R.id.tv_place, "field 'tv_place'", TextView.class);
    target.tv_days = Utils.findRequiredViewAsType(source, R.id.tv_days, "field 'tv_days'", TextView.class);
    target.tv_stay_days = Utils.findRequiredViewAsType(source, R.id.tv_stay_days, "field 'tv_stay_days'", TextView.class);
    target.tv_faces = Utils.findRequiredViewAsType(source, R.id.tv_faces, "field 'tv_faces'", TextView.class);
    target.tv_entry_count = Utils.findRequiredViewAsType(source, R.id.tv_entry_count, "field 'tv_entry_count'", TextView.class);
    target.image_goods = Utils.findRequiredViewAsType(source, R.id.image_goods, "field 'image_goods'", ImageView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.refreshLayout = null;
    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_persone = null;
    target.tv_money = null;
    target.tv_goods_title = null;
    target.tv_goods_day = null;
    target.tv_time = null;
    target.tv_place = null;
    target.tv_days = null;
    target.tv_stay_days = null;
    target.tv_faces = null;
    target.tv_entry_count = null;
    target.image_goods = null;
    target.recyclerView = null;
  }
}
