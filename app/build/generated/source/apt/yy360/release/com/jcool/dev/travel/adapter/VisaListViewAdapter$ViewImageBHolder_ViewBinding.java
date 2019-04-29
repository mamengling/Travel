// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisaListViewAdapter$ViewImageBHolder_ViewBinding implements Unbinder {
  private VisaListViewAdapter.ViewImageBHolder target;

  @UiThread
  public VisaListViewAdapter$ViewImageBHolder_ViewBinding(VisaListViewAdapter.ViewImageBHolder target,
      View source) {
    this.target = target;

    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaListViewAdapter.ViewImageBHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image = null;
  }
}
