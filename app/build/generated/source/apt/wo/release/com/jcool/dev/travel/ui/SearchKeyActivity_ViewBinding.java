// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchKeyActivity_ViewBinding implements Unbinder {
  private SearchKeyActivity target;

  @UiThread
  public SearchKeyActivity_ViewBinding(SearchKeyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchKeyActivity_ViewBinding(SearchKeyActivity target, View source) {
    this.target = target;

    target.title_bar1_edt = Utils.findRequiredViewAsType(source, R.id.title_bar1_edt, "field 'title_bar1_edt'", EditText.class);
    target.tv_close = Utils.findRequiredViewAsType(source, R.id.tv_close, "field 'tv_close'", TextView.class);
    target.recycler_view = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recycler_view'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchKeyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title_bar1_edt = null;
    target.tv_close = null;
    target.recycler_view = null;
  }
}
