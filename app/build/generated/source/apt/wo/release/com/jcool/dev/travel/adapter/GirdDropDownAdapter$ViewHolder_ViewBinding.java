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

public class GirdDropDownAdapter$ViewHolder_ViewBinding implements Unbinder {
  private GirdDropDownAdapter.ViewHolder target;

  @UiThread
  public GirdDropDownAdapter$ViewHolder_ViewBinding(GirdDropDownAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.mText = Utils.findRequiredViewAsType(source, R.id.text, "field 'mText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GirdDropDownAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mText = null;
  }
}
