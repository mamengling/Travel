// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SpecListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private SpecListAdapter.ViewHolder target;

  @UiThread
  public SpecListAdapter$ViewHolder_ViewBinding(SpecListAdapter.ViewHolder target, View source) {
    this.target = target;

    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.tv_show = Utils.findRequiredViewAsType(source, R.id.tv_show, "field 'tv_show'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.tv_content = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tv_content'", TextView.class);
    target.btn_to_pay_money = Utils.findRequiredViewAsType(source, R.id.btn_to_pay_money, "field 'btn_to_pay_money'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SpecListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_name = null;
    target.tv_show = null;
    target.tv_money = null;
    target.tv_content = null;
    target.btn_to_pay_money = null;
  }
}
