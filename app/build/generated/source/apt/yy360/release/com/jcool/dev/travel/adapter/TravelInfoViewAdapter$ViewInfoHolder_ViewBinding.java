// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.view.FixedGridView;
import com.jcool.dev.travel.view.rollviewpage.RollPagerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TravelInfoViewAdapter$ViewInfoHolder_ViewBinding implements Unbinder {
  private TravelInfoViewAdapter.ViewInfoHolder target;

  @UiThread
  public TravelInfoViewAdapter$ViewInfoHolder_ViewBinding(TravelInfoViewAdapter.ViewInfoHolder target,
      View source) {
    this.target = target;

    target.rollPagerView = Utils.findRequiredViewAsType(source, R.id.normal_view_pager, "field 'rollPagerView'", RollPagerView.class);
    target.tv_info = Utils.findRequiredViewAsType(source, R.id.tv_info, "field 'tv_info'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.tv_number = Utils.findRequiredViewAsType(source, R.id.tv_number, "field 'tv_number'", TextView.class);
    target.tv_more_1 = Utils.findRequiredViewAsType(source, R.id.tv_more_1, "field 'tv_more_1'", TextView.class);
    target.tv_more_01 = Utils.findRequiredViewAsType(source, R.id.tv_more_01, "field 'tv_more_01'", TextView.class);
    target.tv_more_2 = Utils.findRequiredViewAsType(source, R.id.tv_more_2, "field 'tv_more_2'", TextView.class);
    target.tv_more_02 = Utils.findRequiredViewAsType(source, R.id.tv_more_02, "field 'tv_more_02'", TextView.class);
    target.line_01 = Utils.findRequiredViewAsType(source, R.id.line_01, "field 'line_01'", LinearLayout.class);
    target.line_02 = Utils.findRequiredViewAsType(source, R.id.line_02, "field 'line_02'", LinearLayout.class);
    target.tv_more = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'tv_more'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'mRecyclerView'", RecyclerView.class);
    target.fixedGridView = Utils.findRequiredViewAsType(source, R.id.fixedGridView, "field 'fixedGridView'", FixedGridView.class);
    target.radiogroup_full = Utils.findRequiredViewAsType(source, R.id.radiogroup_full, "field 'radiogroup_full'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TravelInfoViewAdapter.ViewInfoHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rollPagerView = null;
    target.tv_info = null;
    target.tv_money = null;
    target.tv_number = null;
    target.tv_more_1 = null;
    target.tv_more_01 = null;
    target.tv_more_2 = null;
    target.tv_more_02 = null;
    target.line_01 = null;
    target.line_02 = null;
    target.tv_more = null;
    target.mRecyclerView = null;
    target.fixedGridView = null;
    target.radiogroup_full = null;
  }
}
