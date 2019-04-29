// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateTravelOrderActivity_ViewBinding implements Unbinder {
  private CreateTravelOrderActivity target;

  @UiThread
  public CreateTravelOrderActivity_ViewBinding(CreateTravelOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreateTravelOrderActivity_ViewBinding(CreateTravelOrderActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_change_person = Utils.findRequiredViewAsType(source, R.id.tv_change_person, "field 'tv_change_person'", TextView.class);
    target.tv_buy = Utils.findRequiredViewAsType(source, R.id.tv_buy, "field 'tv_buy'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.person_list = Utils.findRequiredViewAsType(source, R.id.person_list, "field 'person_list'", LinearLayout.class);
    target.edt_user_name = Utils.findRequiredViewAsType(source, R.id.edt_user_name, "field 'edt_user_name'", EditText.class);
    target.edt_phone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edt_phone'", EditText.class);
    target.edt_email = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edt_email'", EditText.class);
    target.edt_other = Utils.findRequiredViewAsType(source, R.id.edt_other, "field 'edt_other'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CreateTravelOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_change_person = null;
    target.tv_buy = null;
    target.tv_money = null;
    target.person_list = null;
    target.edt_user_name = null;
    target.edt_phone = null;
    target.edt_email = null;
    target.edt_other = null;
  }
}
