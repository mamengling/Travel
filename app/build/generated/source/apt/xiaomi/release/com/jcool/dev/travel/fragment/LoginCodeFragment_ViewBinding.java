// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.fragment;

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

public class LoginCodeFragment_ViewBinding implements Unbinder {
  private LoginCodeFragment target;

  @UiThread
  public LoginCodeFragment_ViewBinding(LoginCodeFragment target, View source) {
    this.target = target;

    target.edt_phone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edt_phone'", EditText.class);
    target.edt_code = Utils.findRequiredViewAsType(source, R.id.edt_code, "field 'edt_code'", EditText.class);
    target.tv_register = Utils.findRequiredViewAsType(source, R.id.tv_register, "field 'tv_register'", TextView.class);
    target.find_psw = Utils.findRequiredViewAsType(source, R.id.find_psw, "field 'find_psw'", TextView.class);
    target.btn_commit = Utils.findRequiredViewAsType(source, R.id.btn_commit, "field 'btn_commit'", Button.class);
    target.btn_code = Utils.findRequiredViewAsType(source, R.id.btn_code, "field 'btn_code'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginCodeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edt_phone = null;
    target.edt_code = null;
    target.tv_register = null;
    target.find_psw = null;
    target.btn_commit = null;
    target.btn_code = null;
  }
}
