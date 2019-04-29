// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.view.YRecycleview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TravelSalesFragment_ViewBinding implements Unbinder {
  private TravelSalesFragment target;

  @UiThread
  public TravelSalesFragment_ViewBinding(TravelSalesFragment target, View source) {
    this.target = target;

    target.yrecycleView = Utils.findRequiredViewAsType(source, R.id.yrecycleView, "field 'yrecycleView'", YRecycleview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TravelSalesFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.yrecycleView = null;
  }
}
