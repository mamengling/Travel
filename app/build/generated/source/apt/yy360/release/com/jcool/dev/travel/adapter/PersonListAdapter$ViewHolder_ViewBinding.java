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

public class PersonListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private PersonListAdapter.ViewHolder target;

  @UiThread
  public PersonListAdapter$ViewHolder_ViewBinding(PersonListAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.image_bianji = Utils.findRequiredViewAsType(source, R.id.image_bianji, "field 'image_bianji'", ImageView.class);
    target.check_image = Utils.findRequiredViewAsType(source, R.id.check_image, "field 'check_image'", ImageView.class);
    target.tv_sex = Utils.findRequiredViewAsType(source, R.id.tv_sex, "field 'tv_sex'", TextView.class);
    target.tv_person_type = Utils.findRequiredViewAsType(source, R.id.tv_person_type, "field 'tv_person_type'", TextView.class);
    target.tv_work_type = Utils.findRequiredViewAsType(source, R.id.tv_work_type, "field 'tv_work_type'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PersonListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_name = null;
    target.image_bianji = null;
    target.check_image = null;
    target.tv_sex = null;
    target.tv_person_type = null;
    target.tv_work_type = null;
  }
}
