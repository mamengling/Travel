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

public class VisaListViewAdapter$ViewTitleHolder_ViewBinding implements Unbinder {
  private VisaListViewAdapter.ViewTitleHolder target;

  @UiThread
  public VisaListViewAdapter$ViewTitleHolder_ViewBinding(VisaListViewAdapter.ViewTitleHolder target,
      View source) {
    this.target = target;

    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tv_more = Utils.findRequiredViewAsType(source, R.id.tv_more, "field 'tv_more'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaListViewAdapter.ViewTitleHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tv_more = null;
  }
}
