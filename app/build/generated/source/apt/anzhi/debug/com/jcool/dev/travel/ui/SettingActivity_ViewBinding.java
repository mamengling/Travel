// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target, View source) {
    this.target = target;

    target.btn_login_out = Utils.findRequiredViewAsType(source, R.id.btn_login_out, "field 'btn_login_out'", Button.class);
    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.tv_acc = Utils.findRequiredViewAsType(source, R.id.tv_acc, "field 'tv_acc'", TextView.class);
    target.tv_clear = Utils.findRequiredViewAsType(source, R.id.tv_clear, "field 'tv_clear'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_login_out = null;
    target.icon_title_back = null;
    target.tv_acc = null;
    target.tv_clear = null;
  }
}
