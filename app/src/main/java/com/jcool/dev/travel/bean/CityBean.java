package com.jcool.dev.travel.bean;

import com.google.gson.Gson;

import java.util.List;

public class CityBean {

    /**
     * area_id : 110000
     * area_name : 北京市
     * sub : [{"area_id":100,"area_name":"全城"},{"area_id":110101,"area_name":"东城区"},{"area_id":110102,"area_name":"西城区"},{"area_id":110105,"area_name":"朝阳区"},{"area_id":110106,"area_name":"丰台区"},{"area_id":110107,"area_name":"石景山区"},{"area_id":110108,"area_name":"海淀区"},{"area_id":110109,"area_name":"门头沟区"},{"area_id":110111,"area_name":"房山区"},{"area_id":110112,"area_name":"通州区"},{"area_id":110113,"area_name":"顺义区"},{"area_id":110114,"area_name":"昌平区"},{"area_id":110115,"area_name":"大兴区"},{"area_id":110116,"area_name":"怀柔区"},{"area_id":110117,"area_name":"平谷区"},{"area_id":110118,"area_name":"密云区"},{"area_id":110119,"area_name":"延庆区"}]
     */

    private int area_id;
    private String area_name;
    private List<SubBean> sub;

    public static CityBean objectFromData(String str) {

        return new Gson().fromJson(str, CityBean.class);
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public List<SubBean> getSub() {
        return sub;
    }

    public void setSub(List<SubBean> sub) {
        this.sub = sub;
    }

    public static class SubBean {
        /**
         * area_id : 100
         * area_name : 全城
         */

        private int area_id;
        private String area_name;

        public static SubBean objectFromData(String str) {

            return new Gson().fromJson(str, SubBean.class);
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }
    }
}
