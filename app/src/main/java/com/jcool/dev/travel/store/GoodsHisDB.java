package com.jcool.dev.travel.store;

import android.content.Context;

import com.jcool.dev.travel.bean.GoodsHistoryBean;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

public class GoodsHisDB {
    private DbUtils dbUtils;
    private Context context;
    private GoodsHisDB dbSysCodeBean;

    public GoodsHisDB(Context context) {
        this.context = context;
        dbUtils = DbUtils.create(context);
    }


    public void saveList(List<GoodsHisDB> list) {
        try {
            // ... 加载数据
            dbUtils.saveAll(list);
        } catch (DbException e) {
            LogUtil.i("DbUtils", e.getMessage());
        }
    }

    /**
     * 添加数据
     *
     * @return
     */
    public void saveDbBean(GoodsHistoryBean bean) {
        try {
            dbUtils.save(bean);
//            AppToast.show_LONG(context, "保存成功");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到
     *
     * @param code
     * @return
     */
    public GoodsHistoryBean getSaveOne(String code) {
        GoodsHistoryBean bean = null;
        try {
            bean = dbUtils.findFirst(Selector.from(GoodsHistoryBean.class).where("code", "=", code));
        } catch (DbException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 查询全部
     *
     * @return
     */
    public List<GoodsHistoryBean> getAllData() {
        List<GoodsHistoryBean> list = null;
        try {
            list = dbUtils.findAll(Selector.from(GoodsHistoryBean.class).orderBy("id", true));
            LogUtil.e("查询全部", list.toString());
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 删除全部
     */
    public void deleteAll() {
        List<GoodsHistoryBean> list = getAllData();
        try {
            dbUtils.deleteAll(list);
            ToastUtils.showShortToast("没有任何数据");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

}
