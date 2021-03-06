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

public class TravelDefuiltActivity_ViewBinding implements Unbinder {
  private TravelDefuiltActivity target;

  @UiThread
  public TravelDefuiltActivity_ViewBinding(TravelDefuiltActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TravelDefuiltActivity_ViewBinding(TravelDefuiltActivity target, View source) {
    this.target = target;

    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.icon_title_back = Utils.findRequiredViewAsType(source, R.id.icon_back, "field 'icon_title_back'", TextView.class);
    target.icon_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'icon_title'", TextView.class);
    target.icon_right = Utils.findRequiredViewAsType(source, R.id.icon_right, "field 'icon_right'", TextView.class);
    target.tv_collect = Utils.findRequiredViewAsType(source, R.id.tv_collect, "field 'tv_collect'", TextView.class);
    target.tv_buy = Utils.findRequiredViewAsType(source, R.id.tv_buy, "field 'tv_buy'", TextView.class);
    target.tv_call_phone = Utils.findRequiredViewAsType(source, R.id.tv_call_phone, "field 'tv_call_phone'", TextView.class);
    target.tv_chat = Utils.findRequiredViewAsType(source, R.id.tv_chat, "field 'tv_chat'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TravelDefuiltActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.refreshLayout = null;
    target.icon_title_back = null;
    target.icon_title = null;
    target.icon_right = null;
    target.tv_collect = null;
    target.tv_buy = null;
    target.tv_call_phone = null;
    target.tv_chat = null;
    target.mRecyclerView = null;
  }
}
