// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisaCommitActivity_ViewBinding implements Unbinder {
  private VisaCommitActivity target;

  @UiThread
  public VisaCommitActivity_ViewBinding(VisaCommitActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VisaCommitActivity_ViewBinding(VisaCommitActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_shop_type = Utils.findRequiredViewAsType(source, R.id.tv_shop_type, "field 'tv_shop_type'", TextView.class);
    target.tv_tag_place = Utils.findRequiredViewAsType(source, R.id.tv_tag_place, "field 'tv_tag_place'", TextView.class);
    target.edt_goods_name = Utils.findRequiredViewAsType(source, R.id.edt_goods_name, "field 'edt_goods_name'", TextView.class);
    target.tv_change_person = Utils.findRequiredViewAsType(source, R.id.tv_change_person, "field 'tv_change_person'", TextView.class);
    target.tv_order_number = Utils.findRequiredViewAsType(source, R.id.tv_order_number, "field 'tv_order_number'", EditText.class);
    target.edt_user_name = Utils.findRequiredViewAsType(source, R.id.edt_user_name, "field 'edt_user_name'", EditText.class);
    target.edt_phone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edt_phone'", EditText.class);
    target.edt_email = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edt_email'", EditText.class);
    target.btn_commit = Utils.findRequiredViewAsType(source, R.id.btn_commit, "field 'btn_commit'", Button.class);
    target.person_list = Utils.findRequiredViewAsType(source, R.id.person_list, "field 'person_list'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaCommitActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_shop_type = null;
    target.tv_tag_place = null;
    target.edt_goods_name = null;
    target.tv_change_person = null;
    target.tv_order_number = null;
    target.edt_user_name = null;
    target.edt_phone = null;
    target.edt_email = null;
    target.btn_commit = null;
    target.person_list = null;
  }
}
