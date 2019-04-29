// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserInfoActivity_ViewBinding implements Unbinder {
  private UserInfoActivity target;

  @UiThread
  public UserInfoActivity_ViewBinding(UserInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserInfoActivity_ViewBinding(UserInfoActivity target, View source) {
    this.target = target;

    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_city = Utils.findRequiredViewAsType(source, R.id.tv_city, "field 'tv_city'", TextView.class);
    target.tv_phone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tv_phone'", TextView.class);
    target.tv_user_birthday = Utils.findRequiredViewAsType(source, R.id.tv_user_birthday, "field 'tv_user_birthday'", TextView.class);
    target.tv_sex = Utils.findRequiredViewAsType(source, R.id.tv_sex, "field 'tv_sex'", TextView.class);
    target.tv_user_name = Utils.findRequiredViewAsType(source, R.id.tv_user_name, "field 'tv_user_name'", TextView.class);
    target.tv_head = Utils.findRequiredViewAsType(source, R.id.tv_head, "field 'tv_head'", TextView.class);
    target.image_head = Utils.findRequiredViewAsType(source, R.id.image_head, "field 'image_head'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UserInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.refreshLayout = null;
    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_city = null;
    target.tv_phone = null;
    target.tv_user_birthday = null;
    target.tv_sex = null;
    target.tv_user_name = null;
    target.tv_head = null;
    target.image_head = null;
  }
}
