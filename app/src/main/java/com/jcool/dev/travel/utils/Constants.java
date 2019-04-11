package com.jcool.dev.travel.utils;

public class Constants {
    public static final String BASE_ROOT = "mock/14/";
    //服务器ip地址
    public static final String BASE_URL = "http://39.98.51.93/";
    /**
     * 获取首页banner
     */
    public static final String APP_HOME_BANNER = "api/banner/banner/list";
    /**
     * 获取秒杀商品
     */
    public static final String APP_HOME_API_JOURNEY_GOODS_SALES_QUERY = "api/journeyGoodsSales/journeyGoodsSales/query";
    /**
     * 获取旅行商品详情列表-APP使用
     */
    public static final String APP_HOME_API_JOURNEY_GOODS_QUERY = "api/journeyGoods/journeyGoodsInfo/query";
    /**
     * 根据地域模糊查询根据id，精确查询签证信息列表-APP使用
     */
    public static final String APP_HOME_API_VISA_INFO_QUERY_BY = "api/visa/visaInfo/queryVisaBy";
    /**
     * 根据id获取签证信息表
     */
    public static final String APP_HOME_API_VISA_INFO = "api/visa/visaInfo/get/";
    /**
     * 获取旅行商品详情
     */
    public static final String APP_HOME_API_TRAVEL_INFO = "api/journeyGoods/journeyGoodsInfo/get/";
    /**
     * 查询目的地二级分类
     */
    public static final String APP_HOME_API_TARGETPLACE_QUERY_SECOND = "api/place/targetPlace/queryAllSort";
    /**
     * 根据旅游商品查询路线
     */
    public static final String APP_HOME_API_JOURNEY_GOODSSLINE_QUERYBYGOODS = "api/journeyGoods/journeyGoodsLine/queryByGoods";
    /**
     * 获取旅游商品路线
     */
    public static final String APP_HOME_API_JOURNEY_GOODSSLINE_INFO = "api/journeyGoods/journeyGoodsLine/get/";
    /**
     * 添加收藏(旅游商品)
     */
    public static final String APP_HOME_API_JOURNEY_COLLECT_TRAVEL = "api/collection/collectionInfo/journey/add";
    /**
     * 新增定制旅游
     */
    public static final String APP_HOME_API_JOURNEY_ADD = "api/customized/personalTailor/add";
    /**
     * 前端签证大厅数据获取
     */
    public static final String APP_HOME_API_JOURNEY_VISA_VISAINFO_QUERY_SORT_REGION = "api/visa/visaInfo/querySortRegion";
    /**
     * 查询目的地一级分类
     */
    public static final String APP_HOME_API_JOURNEY_VISA_TARGET_PLAACE_QUERY_FIRST_SORT = "api/place/targetPlace/queryFirstSort";
    /**
     * 根据目的地id获取签证下拉列表
     */
    public static final String APP_HOME_API_JOURNEY_VISA_INFO_BY_PLACE = "api/visa/visaInfo/queryVisaByPlaceId";
    /**
     * 获取旅客信息-需登录
     */
    public static final String APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY = "api/customer/customerInfo/getCustomerBy";
    /**
     * 登录
     */
    public static final String APP_HOME_API_LOGIN = "api/front/login";
    /**
     * 验证码登录
     */
    public static final String APP_HOME_API_LOGIN_CODE = "api/front/login/byPhone";
    /**
     * 通过ID获取用户信息
     */
    public static final String APP_HOME_API_USER_INFO = "api/user/";
}
