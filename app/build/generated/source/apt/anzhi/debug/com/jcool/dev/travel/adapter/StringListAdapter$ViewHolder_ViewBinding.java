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

public class StringListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private StringListAdapter.ViewHolder target;

  @UiThread
  public StringListAdapter$ViewHolder_ViewBinding(StringListAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.tv_content = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tv_content'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StringListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_name = null;
    target.tv_content = null;
  }
}
