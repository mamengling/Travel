// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DatumCommitAdapter$ViewHolder_ViewBinding implements Unbinder {
  private DatumCommitAdapter.ViewHolder target;

  @UiThread
  public DatumCommitAdapter$ViewHolder_ViewBinding(DatumCommitAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.titleView = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'titleView'", TextView.class);
    target.tv_card_content = Utils.findRequiredViewAsType(source, R.id.tv_card_content, "field 'tv_card_content'", TextView.class);
    target.openView = Utils.findRequiredViewAsType(source, R.id.tv_open, "field 'openView'", ImageView.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.item = Utils.findRequiredViewAsType(source, R.id.item, "field 'item'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DatumCommitAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleView = null;
    target.tv_card_content = null;
    target.openView = null;
    target.recyclerView = null;
    target.item = null;
  }
}
