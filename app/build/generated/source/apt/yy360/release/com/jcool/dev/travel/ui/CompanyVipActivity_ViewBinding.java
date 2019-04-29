// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CompanyVipActivity_ViewBinding implements Unbinder {
  private CompanyVipActivity target;

  @UiThread
  public CompanyVipActivity_ViewBinding(CompanyVipActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CompanyVipActivity_ViewBinding(CompanyVipActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.edt_place_in = Utils.findRequiredViewAsType(source, R.id.edt_place_in, "field 'edt_place_in'", EditText.class);
    target.edt_place_out = Utils.findRequiredViewAsType(source, R.id.edt_place_out, "field 'edt_place_out'", EditText.class);
    target.tv_out_day = Utils.findRequiredViewAsType(source, R.id.tv_out_day, "field 'tv_out_day'", TextView.class);
    target.radiogroup_full = Utils.findRequiredViewAsType(source, R.id.radiogroup_full, "field 'radiogroup_full'", RadioGroup.class);
    target.radiobutton0 = Utils.findRequiredViewAsType(source, R.id.radiobutton0, "field 'radiobutton0'", RadioButton.class);
    target.radiobutton1 = Utils.findRequiredViewAsType(source, R.id.radiobutton1, "field 'radiobutton1'", RadioButton.class);
    target.edt_other = Utils.findRequiredViewAsType(source, R.id.edt_other, "field 'edt_other'", EditText.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", EditText.class);
    target.edt_phone = Utils.findRequiredViewAsType(source, R.id.edt_phone, "field 'edt_phone'", EditText.class);
    target.edt_email = Utils.findRequiredViewAsType(source, R.id.edt_email, "field 'edt_email'", EditText.class);
    target.tv_person_number = Utils.findRequiredViewAsType(source, R.id.tv_person_number, "field 'tv_person_number'", EditText.class);
    target.edt_company_name = Utils.findRequiredViewAsType(source, R.id.edt_company_name, "field 'edt_company_name'", EditText.class);
    target.btn_commit = Utils.findRequiredViewAsType(source, R.id.btn_commit, "field 'btn_commit'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CompanyVipActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.edt_place_in = null;
    target.edt_place_out = null;
    target.tv_out_day = null;
    target.radiogroup_full = null;
    target.radiobutton0 = null;
    target.radiobutton1 = null;
    target.edt_other = null;
    target.tv_money = null;
    target.edt_phone = null;
    target.edt_email = null;
    target.tv_person_number = null;
    target.edt_company_name = null;
    target.btn_commit = null;
  }
}
