// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.fragment;

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

public class VisaFragment_ViewBinding implements Unbinder {
  private VisaFragment target;

  @UiThread
  public VisaFragment_ViewBinding(VisaFragment target, View source) {
    this.target = target;

    target.edt_search = Utils.findRequiredViewAsType(source, R.id.edt_search, "field 'edt_search'", TextView.class);
    target.tv_visa_hot = Utils.findRequiredViewAsType(source, R.id.tv_visa_hot, "field 'tv_visa_hot'", TextView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edt_search = null;
    target.tv_visa_hot = null;
    target.refreshLayout = null;
    target.recyclerView = null;
  }
}
