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

public class VisaInfoViewAdapter$ViewImageHolder_ViewBinding implements Unbinder {
  private VisaInfoViewAdapter.ViewImageHolder target;

  @UiThread
  public VisaInfoViewAdapter$ViewImageHolder_ViewBinding(VisaInfoViewAdapter.ViewImageHolder target,
      View source) {
    this.target = target;

    target.image_city_head = Utils.findRequiredViewAsType(source, R.id.image, "field 'image_city_head'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaInfoViewAdapter.ViewImageHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image_city_head = null;
  }
}
