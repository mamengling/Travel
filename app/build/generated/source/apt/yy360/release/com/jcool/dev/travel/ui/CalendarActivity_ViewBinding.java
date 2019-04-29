// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.haibin.calendarview.CalendarView;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CalendarActivity_ViewBinding implements Unbinder {
  private CalendarActivity target;

  @UiThread
  public CalendarActivity_ViewBinding(CalendarActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CalendarActivity_ViewBinding(CalendarActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.calendarView = Utils.findRequiredViewAsType(source, R.id.calendarView, "field 'calendarView'", CalendarView.class);
    target.radiogroup_full = Utils.findRequiredViewAsType(source, R.id.radiogroup_full, "field 'radiogroup_full'", RadioGroup.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.tv_number_da = Utils.findRequiredViewAsType(source, R.id.tv_number_da, "field 'tv_number_da'", TextView.class);
    target.tv_jian_da = Utils.findRequiredViewAsType(source, R.id.tv_jian_da, "field 'tv_jian_da'", TextView.class);
    target.tv_jia_da = Utils.findRequiredViewAsType(source, R.id.tv_jia_da, "field 'tv_jia_da'", TextView.class);
    target.tv_jian = Utils.findRequiredViewAsType(source, R.id.tv_jian, "field 'tv_jian'", TextView.class);
    target.tv_jia = Utils.findRequiredViewAsType(source, R.id.tv_jia, "field 'tv_jia'", TextView.class);
    target.tv_number_xiaohai = Utils.findRequiredViewAsType(source, R.id.tv_number_xiaohai, "field 'tv_number_xiaohai'", TextView.class);
    target.tv_phone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'tv_phone'", TextView.class);
    target.tv_message = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'tv_message'", TextView.class);
    target.tv_buy = Utils.findRequiredViewAsType(source, R.id.tv_buy, "field 'tv_buy'", TextView.class);
    target.math1 = Utils.findRequiredViewAsType(source, R.id.math1, "field 'math1'", RadioButton.class);
    target.math2 = Utils.findRequiredViewAsType(source, R.id.math2, "field 'math2'", RadioButton.class);
    target.math3 = Utils.findRequiredViewAsType(source, R.id.math3, "field 'math3'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CalendarActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.calendarView = null;
    target.radiogroup_full = null;
    target.tv_money = null;
    target.tv_number_da = null;
    target.tv_jian_da = null;
    target.tv_jia_da = null;
    target.tv_jian = null;
    target.tv_jia = null;
    target.tv_number_xiaohai = null;
    target.tv_phone = null;
    target.tv_message = null;
    target.tv_buy = null;
    target.math1 = null;
    target.math2 = null;
    target.math3 = null;
  }
}
