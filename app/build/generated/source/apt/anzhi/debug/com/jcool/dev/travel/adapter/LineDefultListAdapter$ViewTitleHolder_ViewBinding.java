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

public class LineDefultListAdapter$ViewTitleHolder_ViewBinding implements Unbinder {
  private LineDefultListAdapter.ViewTitleHolder target;

  @UiThread
  public LineDefultListAdapter$ViewTitleHolder_ViewBinding(LineDefultListAdapter.ViewTitleHolder target,
      View source) {
    this.target = target;

    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LineDefultListAdapter.ViewTitleHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
  }
}
