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

public class VisaListViewAdapter$ViewImageHolder_ViewBinding implements Unbinder {
  private VisaListViewAdapter.ViewImageHolder target;

  @UiThread
  public VisaListViewAdapter$ViewImageHolder_ViewBinding(VisaListViewAdapter.ViewImageHolder target,
      View source) {
    this.target = target;

    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tvTitle'", TextView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.image_goods_visa = Utils.findRequiredViewAsType(source, R.id.image_goods_visa, "field 'image_goods_visa'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaListViewAdapter.ViewImageHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.tv_name = null;
    target.image_goods_visa = null;
  }
}
