// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TravelSalesActivity_ViewBinding implements Unbinder {
  private TravelSalesActivity target;

  @UiThread
  public TravelSalesActivity_ViewBinding(TravelSalesActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TravelSalesActivity_ViewBinding(TravelSalesActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
    target.tlMainTabtop = Utils.findRequiredViewAsType(source, R.id.tl_main_tabtop, "field 'tlMainTabtop'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TravelSalesActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.viewpager = null;
    target.tlMainTabtop = null;
  }
}
