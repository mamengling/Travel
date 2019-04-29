// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisaCommitNextActivity_ViewBinding implements Unbinder {
  private VisaCommitNextActivity target;

  @UiThread
  public VisaCommitNextActivity_ViewBinding(VisaCommitNextActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VisaCommitNextActivity_ViewBinding(VisaCommitNextActivity target, View source) {
    this.target = target;

    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaCommitNextActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
  }
}
