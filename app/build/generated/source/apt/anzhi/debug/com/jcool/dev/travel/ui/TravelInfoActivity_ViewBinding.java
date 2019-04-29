// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.view.FixedGridView;
import com.jcool.dev.travel.view.rollviewpage.RollPagerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TravelInfoActivity_ViewBinding implements Unbinder {
  private TravelInfoActivity target;

  @UiThread
  public TravelInfoActivity_ViewBinding(TravelInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TravelInfoActivity_ViewBinding(TravelInfoActivity target, View source) {
    this.target = target;

    target.rollPagerView = Utils.findRequiredViewAsType(source, R.id.normal_view_pager, "field 'rollPagerView'", RollPagerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_info = Utils.findRequiredViewAsType(source, R.id.tv_info, "field 'tv_info'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.tv_number = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tv_number'", TextView.class);
    target.tv_collect = Utils.findRequiredViewAsType(source, R.id.tv_collect, "field 'tv_collect'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'mRecyclerView'", RecyclerView.class);
    target.fixedGridView = Utils.findRequiredViewAsType(source, R.id.fixedGridView, "field 'fixedGridView'", FixedGridView.class);
    target.radiogroup_full = Utils.findRequiredViewAsType(source, R.id.radiogroup_full, "field 'radiogroup_full'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TravelInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rollPagerView = null;
    target.refreshLayout = null;
    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_info = null;
    target.tv_money = null;
    target.tv_number = null;
    target.tv_collect = null;
    target.mRecyclerView = null;
    target.fixedGridView = null;
    target.radiogroup_full = null;
  }
}
