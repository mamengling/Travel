// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PayActivity_ViewBinding implements Unbinder {
  private PayActivity target;

  @UiThread
  public PayActivity_ViewBinding(PayActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PayActivity_ViewBinding(PayActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.btn_commit = Utils.findRequiredViewAsType(source, R.id.btn_commit, "field 'btn_commit'", Button.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.image_weixin = Utils.findRequiredViewAsType(source, R.id.image_weixin, "field 'image_weixin'", ImageView.class);
    target.image_alipay = Utils.findRequiredViewAsType(source, R.id.image_alipay, "field 'image_alipay'", ImageView.class);
    target.line_weixin = Utils.findRequiredViewAsType(source, R.id.line_weixin, "field 'line_weixin'", LinearLayout.class);
    target.line_alipay = Utils.findRequiredViewAsType(source, R.id.line_alipay, "field 'line_alipay'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PayActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.btn_commit = null;
    target.tv_money = null;
    target.image_weixin = null;
    target.image_alipay = null;
    target.line_weixin = null;
    target.line_alipay = null;
  }
}
