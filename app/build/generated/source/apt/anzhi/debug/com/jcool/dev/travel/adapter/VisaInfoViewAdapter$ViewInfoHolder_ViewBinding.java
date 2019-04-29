// Generated code from Butter Knife. Do not modify!
package com.jcool.dev.travel.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.jcool.dev.travel.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VisaInfoViewAdapter$ViewInfoHolder_ViewBinding implements Unbinder {
  private VisaInfoViewAdapter.ViewInfoHolder target;

  @UiThread
  public VisaInfoViewAdapter$ViewInfoHolder_ViewBinding(VisaInfoViewAdapter.ViewInfoHolder target,
      View source) {
    this.target = target;

    target.tv_persone = Utils.findRequiredViewAsType(source, R.id.tv_persone, "field 'tv_persone'", TextView.class);
    target.tv_money = Utils.findRequiredViewAsType(source, R.id.tv_money, "field 'tv_money'", TextView.class);
    target.tv_goods_title = Utils.findRequiredViewAsType(source, R.id.tv_goods_title, "field 'tv_goods_title'", TextView.class);
    target.tv_goods_day = Utils.findRequiredViewAsType(source, R.id.tv_goods_day, "field 'tv_goods_day'", TextView.class);
    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.tv_place = Utils.findRequiredViewAsType(source, R.id.tv_place, "field 'tv_place'", TextView.class);
    target.tv_days = Utils.findRequiredViewAsType(source, R.id.tv_days, "field 'tv_days'", TextView.class);
    target.tv_stay_days = Utils.findRequiredViewAsType(source, R.id.tv_stay_days, "field 'tv_stay_days'", TextView.class);
    target.tv_faces = Utils.findRequiredViewAsType(source, R.id.tv_faces, "field 'tv_faces'", TextView.class);
    target.tv_entry_count = Utils.findRequiredViewAsType(source, R.id.tv_entry_count, "field 'tv_entry_count'", TextView.class);
    target.tv_ertong_number = Utils.findRequiredViewAsType(source, R.id.tv_ertong_number, "field 'tv_ertong_number'", TextView.class);
    target.tv_tuixiu_number = Utils.findRequiredViewAsType(source, R.id.tv_tuixiu_number, "field 'tv_tuixiu_number'", TextView.class);
    target.tv_xuesheng_number = Utils.findRequiredViewAsType(source, R.id.tv_xuesheng_number, "field 'tv_xuesheng_number'", TextView.class);
    target.tv_zhiye_number = Utils.findRequiredViewAsType(source, R.id.tv_zhiye_number, "field 'tv_zhiye_number'", TextView.class);
    target.tv_renyuan_number = Utils.findRequiredViewAsType(source, R.id.tv_renyuan_number, "field 'tv_renyuan_number'", TextView.class);
    target.acceptRange = Utils.findRequiredViewAsType(source, R.id.acceptRange, "field 'acceptRange'", TextView.class);
    target.image_goods = Utils.findRequiredViewAsType(source, R.id.image_goods, "field 'image_goods'", ImageView.class);
    target.radiogroup_full = Utils.findRequiredViewAsType(source, R.id.radiogroup_full, "field 'radiogroup_full'", RadioGroup.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VisaInfoViewAdapter.ViewInfoHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_persone = null;
    target.tv_money = null;
    target.tv_goods_title = null;
    target.tv_goods_day = null;
    target.tv_time = null;
    target.tv_place = null;
    target.tv_days = null;
    target.tv_stay_days = null;
    target.tv_faces = null;
    target.tv_entry_count = null;
    target.tv_ertong_number = null;
    target.tv_tuixiu_number = null;
    target.tv_xuesheng_number = null;
    target.tv_zhiye_number = null;
    target.tv_renyuan_number = null;
    target.acceptRange = null;
    target.image_goods = null;
    target.radiogroup_full = null;
  }
}
