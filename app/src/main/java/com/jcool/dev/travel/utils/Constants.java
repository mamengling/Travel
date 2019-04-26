package com.jcool.dev.travel.utils;

public class Constants {
    public static final String BASE_ROOT = "mock/14/";
    //服务器ip地址
    public static final String BASE_URL = "http://39.98.51.93/";
//    public static final String BASE_URL = "http://192.168.0.121:1025/";
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
     * 商品是否收藏
     */
    public static final String APP_HOME_API_JOURNEY_GET_COLLECT_STATUS = "api/collection/collectionInfo/isCollection/";
    /**
     * 添加收藏(旅游商品)
     */
    public static final String APP_HOME_API_JOURNEY_COLLECT_TRAVEL = "api/collection/collectionInfo/journey/add";
    /**
     * 添加收藏(签证商品)
     */
    public static final String APP_HOME_API_JOURNEY_COLLECT_VISA = "api/collection/collectionInfo/visa/add";
    /**
     * 删除收藏
     */
    public static final String APP_HOME_API_JOURNEY_COLLECT_DELETE = "api/collection/collectionInfo/delete";
    /**
     * 新增定制旅游
     */
    public static final String APP_HOME_API_JOURNEY_ADD = "api/customized/personalTailor/add";
    /**
     * 新增旅客信息
     */
    public static final String APP_HOME_API_COSTOMER_PERSON_ADD = "api/customer/customerInfo/add";
    /**
     * 前端签证大厅数据获取
     */
    public static final String APP_HOME_API_JOURNEY_VISA_VISAINFO_QUERY_SORT_REGION = "api/visa/visaInfo/querySortRegion";
    /**
     * 查询目的地一级分类
     */
    public static final String APP_HOME_API_JOURNEY_VISA_TARGET_PLAACE_QUERY_FIRST_SORT = "api/place/targetPlace/queryFirstSort";
    /**
     * 查询目的地二级分类
     */
    public static final String APP_HOME_API_JOURNEY_VISA_TARGET_PLAACE_QUERY_SECOND_SORT = "api/place/targetPlace/querySecond";
    /**
     * 前端其它渠道提交签证审核资料
     */
    public static final String APP_HOME_API_JOURNEY_VISA_OTHER_ORDER_DATA_ADD = "api/visa/otherOrderData/add";
    /**
     * 根据目的地id获取签证下拉列表
     */
    public static final String APP_HOME_API_JOURNEY_VISA_INFO_BY_PLACE = "api/visa/visaInfo/queryVisaByPlaceId";
    /**
     * 获取旅客信息-需登录
     */
    public static final String APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY = "api/customer/customerInfo/getCustomerBy";
    /**
     * 根据id修改旅客信息
     */
    public static final String APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY_UPDATE = "api/customer/customerInfo/update";
    /**
     * 根据id删除旅客信息表
     */
    public static final String APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY_DELETE = "api/customer/customerInfo/delete/";
    /**
     * 发送验证码
     */
    public static final String APP_HOME_API_VCODE_SEND_CODE = "api/vcode/send";
    /**
     * 注册
     */
    public static final String APP_HOME_API_VCODE_REGISTER = "api/user/register";
    /**
     * 验证验证码
     */
    public static final String APP_HOME_API_VCODE_CALIDATE = "api/vcode/validate";
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
    /**
     * 通过ID获取用户信息
     */
    public static final String APP_HOME_API_USER_INFO_UPDATE = "api/user/update";
    /**
     * 获取签证订单主表
     */
    public static final String APP_HOME_API_GET_ORDER_VISA_INFO = "api/visa/visaOrder/get/";
    /**
     * 前端根据订单id获取其它渠道资料上传所需数据（一步到位有所需材料）
     */
    public static final String APP_HOME_API_GET_OTHER_ORDER_VISA_INFO = "api/visa/otherOrderData/getOtherOrderInfo/";
    /**
     * 前端根据订单id获取其它渠道资料上传所需数据（一步到位有所需材料）
     */
    public static final String APP_HOME_API_GET_OTHER_ORDER_VISA_INFO_MAIN = "api/visa/visaOrder/getOrderInfo/";
    /**
     * 提交记录查询
     */
    public static final String APP_HOME_API_GET_OTHER_ORDER_VISA_LIST = "api/visa/otherOrderData/commitRecords";
    /**
     * 上传图片 单个上传
     */
    public static final String APP_HOME_API_UPLOAD_IMAGE = "attachment/upload/image";
    /**
     * 上传附件 单个上传
     */
    public static final String APP_HOME_API_UPLOAD_FILES = "attachment/upload";
    /**
     * 其它途径订单资料提交和驳回再提交（测试集合中有实例；修改时加id字段）
     */
    public static final String APP_HOME_API_VISA_OTHER_ORDER_COMMIT_DATA = "api/visa/otherOrderData/commitData";
    /**
     * 【APP】查询我的订单
     */
    public static final String APP_HOME_API_VISA_ORDER_JOURNEY_GOODS_FORM_QUERY_APP = "api/journeyForm/journeyGoodsForm/query/app";
    /**
     * 订单详情 旅游订单详情
     */
    public static final String APP_HOME_API_TRAVEL_ORDER_JOURNEY_GOODS_FORM_QUERY_APP = "api/journeyForm/journeyGoodsForm/get/";
    /**
     * 创建支付宝预订单
     */
    public static final String APP_HOME_API_ALIPAY_CREATE_PRE_ORDER = "api/pay/alipay/createPreOrder";
    /**
     * 创建微信预订单
     */
    public static final String APP_HOME_API_WX_CREATE_PRE_ORDER = "api/pay/wxpay/createPreOrder";
    /**
     * 前端分页查询个人签证订单
     */
    public static final String APP_HOME_API_VISA_VISA_ORDER_QUERY_PERSONAL = "api/visa/visaOrder/queryPersonal";
    /**
     * 取消订单 签证订单
     */
    public static final String APP_HOME_API_VISA_VISA_ORDER_CANCLE = "api/visa/visaOrder/cancleOrder";
    /**
     * 退款接口 签证订单
     */
    public static final String APP_HOME_API_VISA_VISA_ORDER_REFUND = "api/visa/visaOrder/refund";
    /**
     * 取消订单 旅游订单
     */
    public static final String APP_HOME_API_TRAVEL_ORDER_CANCLE = "api/journeyForm/journeyGoodsForm/close";
    /**
     * 退款接口 旅游订单
     */
    public static final String APP_HOME_API_TRAVEL_ORDER_REFUND = "api/journeyForm/journeyGoodsForm/refund";
    /**
     * 出行接口 旅游订单
     */
    public static final String APP_HOME_API_TRAVEL_ORDER_FORM = "api/journeyForm/journeyGoodsForm/used";
    /**
     * 新增签证订单主表 （前端购买签证产品）
     */
    public static final String APP_HOME_API_VISA_ADD_ORDER = "api/visa/visaOrder/add";
    /**
     * 根据时间段查询价格排期
     */
    public static final String APP_HOME_API_TRAVEL_TIME_LIST = "api/journeyGoods/journeyGoodsDate/queryByGoodsAndDate";
    /**
     * 获取旅游商品价格排期
     */
    public static final String APP_HOME_API_TRAVEL_TIME_LIST_INFO = "api/journeyGoods/journeyGoodsDate/get/";
    /**
     * 下单（普通订单）
     */
    public static final String APP_HOME_API_TRAVEL_TORDER = "api/journeyForm/journeyGoodsForm/add";
    /**
     * 查询收藏(签证商品)
     */
    public static final String APP_HOME_API_VISA_COLLECTION = "api/collection/collectionInfo/visa/query";
    /**
     * 查询收藏(旅游商品)
     */
    public static final String APP_HOME_API_TRAVEL_COLLECTION = "api/collection/collectionInfo//journey/query";
    /**
     * 新增反馈
     */
    public static final String APP_HOME_API_FACE_BACK = "api/feedback/add";
    /**
     * 修改手机号 验证当前手机号接口
     */
    public static final String APP_HOME_API_PASSWORD_BACK = "api/user/password/getBack";
    /**
     * 修改手机号
     */
    public static final String APP_HOME_API_USER_UPDATE_PHONE = "api/user/update";
    /**
     * checkPassword
     */
    public static final String APP_HOME_API_USER_CHECK_PASS_WORD = "api/user/check-password";
    /**
     * updatePassword
     */
    public static final String APP_HOME_API_USER_UPDATE_PASS_WORD = "/api/user/password";
}
