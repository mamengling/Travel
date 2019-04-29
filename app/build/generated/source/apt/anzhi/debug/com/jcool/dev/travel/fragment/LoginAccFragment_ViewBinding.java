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

public class LoginAccFragment_ViewBinding implements Unbinder {
  private LoginAccFragment target;

  @UiThread
  public LoginAccFragment_ViewBinding(LoginAccFragment target, View source) {
    this.target = target;

    target.edt_phone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edt_phone'", EditText.class);
    target.edt_password = Utils.findRequiredViewAsType(source, R.id.edt_password, "field 'edt_password'", EditText.class);
    target.tv_register = Utils.findRequiredViewAsType(source, R.id.tv_register, "field 'tv_register'", TextView.class);
    target.find_psw = Utils.findRequiredViewAsType(source, R.id.find_psw, "field 'find_psw'", TextView.class);
    target.btn_commit = Utils.findRequiredViewAsType(source, R.id.btn_commit, "field 'btn_commit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginAccFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edt_phone = null;
    target.edt_password = null;
    target.tv_register = null;
    target.find_psw = null;
    target.btn_commit = null;
  }
}
