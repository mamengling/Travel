// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PersonVipActivity_ViewBinding implements Unbinder {
  private PersonVipActivity target;

  @UiThread
  public PersonVipActivity_ViewBinding(PersonVipActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PersonVipActivity_ViewBinding(PersonVipActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.edt_place_in = Utils.findRequiredViewAsType(source, R.id.edt_place_in, "field 'edt_place_in'", EditText.class);
    target.edt_place_out = Utils.findRequiredViewAsType(source, R.id.edt_place_out, "field 'edt_place_out'", EditText.class);
    target.tv_out_day = Utils.findRequiredViewAsType(source, R.id.tv_out_day, "field 'tv_out_day'", TextView.class);
    target.edt_children_person = Utils.findRequiredViewAsType(source, R.id.edt_children_person, "field 'edt_children_person'", EditText.class);
    target.tv_person_number = Utils.findRequiredViewAsType(source, R.id.tv_person_number, "field 'tv_person_number'", EditText.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", EditText.class);
    target.edt_other = Utils.findRequiredViewAsType(source, R.id.edt_other, "field 'edt_other'", EditText.class);
    target.edt_phone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edt_phone'", EditText.class);
    target.edt_name_user = Utils.findRequiredViewAsType(source, R.id.edt_name_user, "field 'edt_name_user'", EditText.class);
    target.edt_email = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edt_email'", EditText.class);
    target.btn_commit = Utils.findRequiredViewAsType(source, R.id.btn_commit, "field 'btn_commit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PersonVipActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.edt_place_in = null;
    target.edt_place_out = null;
    target.tv_out_day = null;
    target.edt_children_person = null;
    target.tv_person_number = null;
    target.tv_money = null;
    target.edt_other = null;
    target.edt_phone = null;
    target.edt_name_user = null;
    target.edt_email = null;
    target.btn_commit = null;
  }
}
