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

public class ListIconAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ListIconAdapter.ViewHolder target;

  @UiThread
  public ListIconAdapter$ViewHolder_ViewBinding(ListIconAdapter.ViewHolder target, View source) {
    this.target = target;

    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ListIconAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_name = null;
  }
}
