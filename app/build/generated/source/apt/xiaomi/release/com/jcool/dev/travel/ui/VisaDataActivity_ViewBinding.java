// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisaDataActivity_ViewBinding implements Unbinder {
  private VisaDataActivity target;

  @UiThread
  public VisaDataActivity_ViewBinding(VisaDataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VisaDataActivity_ViewBinding(VisaDataActivity target, View source) {
    this.target = target;

    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'mRecyclerView'", RecyclerView.class);
    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_title_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.icon_title, "field 'icon_title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaDataActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.refreshLayout = null;
    target.mRecyclerView = null;
    target.icon_title_back = null;
    target.icon_title = null;
  }
}
