// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisaWorkDataAdapter$ViewHolder_ViewBinding implements Unbinder {
  private VisaWorkDataAdapter.ViewHolder target;

  @UiThread
  public VisaWorkDataAdapter$ViewHolder_ViewBinding(VisaWorkDataAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.expand_text_view = Utils.findRequiredViewAsType(source, R.id.expand_text_view, "field 'expand_text_view'", ExpandableTextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaWorkDataAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.expand_text_view = null;
  }
}
