// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AccMangerActivity_ViewBinding implements Unbinder {
  private AccMangerActivity target;

  @UiThread
  public AccMangerActivity_ViewBinding(AccMangerActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AccMangerActivity_ViewBinding(AccMangerActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_phone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tv_phone'", TextView.class);
    target.tv_pas = Utils.findRequiredViewAsType(source, R.id.tv_pas, "field 'tv_pas'", TextView.class);
    target.tv_wx_set = Utils.findRequiredViewAsType(source, R.id.tv_wx_set, "field 'tv_wx_set'", TextView.class);
    target.tv_qq_set = Utils.findRequiredViewAsType(source, R.id.tv_qq_set, "field 'tv_qq_set'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AccMangerActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_phone = null;
    target.tv_pas = null;
    target.tv_wx_set = null;
    target.tv_qq_set = null;
  }
}
