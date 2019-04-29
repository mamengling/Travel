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

public class FindPswCodeActivity_ViewBinding implements Unbinder {
  private FindPswCodeActivity target;

  @UiThread
  public FindPswCodeActivity_ViewBinding(FindPswCodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FindPswCodeActivity_ViewBinding(FindPswCodeActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.edt_code = Utils.findRequiredViewAsType(source, R.id.edt_code, "field 'edt_code'", EditText.class);
    target.btn_commit = Utils.findRequiredViewAsType(source, R.id.btn_commit, "field 'btn_commit'", Button.class);
    target.btn_code = Utils.findRequiredViewAsType(source, R.id.btn_code, "field 'btn_code'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FindPswCodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.edt_code = null;
    target.btn_commit = null;
    target.btn_code = null;
  }
}
