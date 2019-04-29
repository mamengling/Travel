// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IconAdapter$ViewHolder_ViewBinding implements Unbinder {
  private IconAdapter.ViewHolder target;

  @UiThread
  public IconAdapter$ViewHolder_ViewBinding(IconAdapter.ViewHolder target, View source) {
    this.target = target;

    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.image_icon = Utils.findRequiredViewAsType(source, R.id.image_icon, "field 'image_icon'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    IconAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_name = null;
    target.image_icon = null;
  }
}
