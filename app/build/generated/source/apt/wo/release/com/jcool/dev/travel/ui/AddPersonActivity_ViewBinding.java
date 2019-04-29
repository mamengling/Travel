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

public class AddPersonActivity_ViewBinding implements Unbinder {
  private AddPersonActivity target;

  @UiThread
  public AddPersonActivity_ViewBinding(AddPersonActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AddPersonActivity_ViewBinding(AddPersonActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_age = Utils.findRequiredViewAsType(source, R.id.tv_age, "field 'tv_age'", TextView.class);
    target.tv_person_type = Utils.findRequiredViewAsType(source, R.id.tv_person_type, "field 'tv_person_type'", TextView.class);
    target.tv_card_type = Utils.findRequiredViewAsType(source, R.id.tv_card_type, "field 'tv_card_type'", TextView.class);
    target.edt_code_number = Utils.findRequiredViewAsType(source, R.id.edt_code_number, "field 'edt_code_number'", EditText.class);
    target.edt_china_name = Utils.findRequiredViewAsType(source, R.id.edt_china_name, "field 'edt_china_name'", EditText.class);
    target.radiogroup_full = Utils.findRequiredViewAsType(source, R.id.radiogroup_full, "field 'radiogroup_full'", RadioGroup.class);
    target.btn_commit = Utils.findRequiredViewAsType(source, R.id.btn_commit, "field 'btn_commit'", Button.class);
    target.radiobutton0 = Utils.findRequiredViewAsType(source, R.id.radiobutton0, "field 'radiobutton0'", RadioButton.class);
    target.radiobutton1 = Utils.findRequiredViewAsType(source, R.id.radiobutton1, "field 'radiobutton1'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddPersonActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_age = null;
    target.tv_person_type = null;
    target.tv_card_type = null;
    target.edt_code_number = null;
    target.edt_china_name = null;
    target.radiogroup_full = null;
    target.btn_commit = null;
    target.radiobutton0 = null;
    target.radiobutton1 = null;
  }
}
