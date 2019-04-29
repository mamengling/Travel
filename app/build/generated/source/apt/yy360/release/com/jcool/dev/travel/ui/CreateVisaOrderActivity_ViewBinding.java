// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateVisaOrderActivity_ViewBinding implements Unbinder {
  private CreateVisaOrderActivity target;

  @UiThread
  public CreateVisaOrderActivity_ViewBinding(CreateVisaOrderActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CreateVisaOrderActivity_ViewBinding(CreateVisaOrderActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_day = Utils.findRequiredViewAsType(source, R.id.tv_day, "field 'tv_day'", TextView.class);
    target.tv_date_time = Utils.findRequiredViewAsType(source, R.id.tv_date_time, "field 'tv_date_time'", TextView.class);
    target.tv_change_person = Utils.findRequiredViewAsType(source, R.id.tv_change_person, "field 'tv_change_person'", TextView.class);
    target.person_list = Utils.findRequiredViewAsType(source, R.id.person_list, "field 'person_list'", LinearLayout.class);
    target.line_address = Utils.findRequiredViewAsType(source, R.id.line_address, "field 'line_address'", LinearLayout.class);
    target.edt_user_name = Utils.findRequiredViewAsType(source, R.id.edt_user_name, "field 'edt_user_name'", EditText.class);
    target.edt_phone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edt_phone'", EditText.class);
    target.edt_email = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edt_email'", EditText.class);
    target.radiobutton0 = Utils.findRequiredViewAsType(source, R.id.radiobutton0, "field 'radiobutton0'", RadioButton.class);
    target.radiobutton2 = Utils.findRequiredViewAsType(source, R.id.radiobutton2, "field 'radiobutton2'", RadioButton.class);
    target.ed_address = Utils.findRequiredViewAsType(source, R.id.ed_address, "field 'ed_address'", TextView.class);
    target.tv_give_type = Utils.findRequiredViewAsType(source, R.id.tv_give_type, "field 'tv_give_type'", TextView.class);
    target.tv_give_address = Utils.findRequiredViewAsType(source, R.id.tv_give_address, "field 'tv_give_address'", TextView.class);
    target.tv_address = Utils.findRequiredViewAsType(source, R.id.tv_address, "field 'tv_address'", TextView.class);
    target.check_box = Utils.findRequiredViewAsType(source, R.id.check_box, "field 'check_box'", CheckBox.class);
    target.btn_to_pay = Utils.findRequiredViewAsType(source, R.id.btn_to_pay, "field 'btn_to_pay'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CreateVisaOrderActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_day = null;
    target.tv_date_time = null;
    target.tv_change_person = null;
    target.person_list = null;
    target.line_address = null;
    target.edt_user_name = null;
    target.edt_phone = null;
    target.edt_email = null;
    target.radiobutton0 = null;
    target.radiobutton2 = null;
    target.ed_address = null;
    target.tv_give_type = null;
    target.tv_give_address = null;
    target.tv_address = null;
    target.check_box = null;
    target.btn_to_pay = null;
  }
}
