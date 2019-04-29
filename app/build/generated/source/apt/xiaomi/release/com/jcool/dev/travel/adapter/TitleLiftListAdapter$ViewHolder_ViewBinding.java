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

public class TitleLiftListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private TitleLiftListAdapter.ViewHolder target;

  @UiThread
  public TitleLiftListAdapter$ViewHolder_ViewBinding(TitleLiftListAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_name'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TitleLiftListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_name = null;
  }
}
