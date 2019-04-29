// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderTabActivity_ViewBinding implements Unbinder {
  private OrderTabActivity target;

  @UiThread
  public OrderTabActivity_ViewBinding(OrderTabActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderTabActivity_ViewBinding(OrderTabActivity target, View source) {
    this.target = target;

    target.radiogroup_full = Utils.findRequiredViewAsType(source, R.id.radiogroup_full, "field 'radiogroup_full'", RadioGroup.class);
    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderTabActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.radiogroup_full = null;
    target.icon_title_back = null;
  }
}
