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

public class TravelInfoLineAdapter$ViewHolder_ViewBinding implements Unbinder {
  private TravelInfoLineAdapter.ViewHolder target;

  @UiThread
  public TravelInfoLineAdapter$ViewHolder_ViewBinding(TravelInfoLineAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tv_line_lable = Utils.findRequiredViewAsType(source, R.id.tv_line_lable, "field 'tv_line_lable'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TravelInfoLineAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_line_lable = null;
  }
}
