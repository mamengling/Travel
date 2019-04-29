// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SetPasswordActivity_ViewBinding implements Unbinder {
  private SetPasswordActivity target;

  @UiThread
  public SetPasswordActivity_ViewBinding(SetPasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SetPasswordActivity_ViewBinding(SetPasswordActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.edt_password = Utils.findRequiredViewAsType(source, R.id.edt_password, "field 'edt_password'", EditText.class);
    target.edt_password_again = Utils.findRequiredViewAsType(source, R.id.edt_password_again, "field 'edt_password_again'", EditText.class);
    target.btn_commit = Utils.findRequiredViewAsType(source, R.id.btn_commit, "field 'btn_commit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SetPasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.edt_password = null;
    target.edt_password_again = null;
    target.btn_commit = null;
  }
}
