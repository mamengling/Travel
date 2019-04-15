package com.jcool.dev.travel.bean;

public class OrderInfoPay {

    /**
     * msg : 支付宝订单生成成功
     * orderStr : alipay_sdk=alipay-sdk-java-3.3.87.ALL&app_id=2019031563532753&biz_content=%7B%22out_trade_no%22%3A%221107557806241800194%22%2C%22passback_params%22%3A%2201%22%2C%22product_code%22%3A%22FAST_INSTANT_TRADE_PAY%22%2C%22subject%22%3A%22%E3%80%90%E5%85%A8%E6%95%B0%E6%8D%AE%E3%80%91%E6%97%A5%E6%9C%AC%E4%B8%9C%E4%BA%AC%2B%E5%AF%8C%E5%A3%AB%E5%B1%B1%2B%E4%BA%AC%E9%83%BD%2B%E5%A4%A7%E9%98%AA7%E6%97%A56%E6%99%9A%E5%8D%8A%E8%87%AA%E5%8A%A9%E6%B8%B8%284%E9%92%BB%29%C2%B7%E5%93%81%E6%80%80%E7%9F%B3%E6%B3%A1%E6%B8%A9%E6%B3%89%C2%B79%E4%BA%BA%E5%9B%A2%E3%80%90%E6%96%B0%E6%98%A5%E5%A4%A7%E4%BF%83%E3%80%91%E5%B8%82%E5%8C%BA4%E6%98%9F%2B%E7%AE%B1%E6%A0%B9%E6%B8%A9%E6%B3%89%C2%B7%E9%83%A8%E5%88%86%E7%A7%81%E6%B1%A4%E3%80%90%E5%B2%9A%E5%B1%B1%E5%B0%8F%E7%81%AB%E8%BD%A6%2B%E6%96%B0%E5%B9%B2%E7%BA%BF%E3%80%91%E8%9F%B9%E9%81%93%E4%B9%90%2B%E5%B1%B1%E6%A2%A8%E7%89%B9%E8%86%B3%E3%80%90B%E7%BA%BF%E8%B5%8F%E6%A8%B1%E4%B8%93%E7%BA%BF%C2%B7C%E7%BA%BF%E6%B5%B7%E9%99%86%E7%A9%BA%E8%A7%82%E5%AF%8C%E5%A3%AB%E5%B1%B1%E3%80%91%E5%8D%83%E6%9C%AC%E9%B8%9F%E5%B1%85%C2%B7%E5%A5%88%E8%89%AF%E9%80%97%E9%B9%BF%C2%B72%E6%97%A5%E8%87%AA%E7%94%B1%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F47.105.195.201%3A1025%2Fapi%2Fpay%2Falipay%2FcallBack&sign=jKRIMBR%2Bt9W1xUL%2Bx6NUwKhhTftq2AH%2F82i1ajag2OoCFth0c3Ii1xl7RNYriTXuRpjiRah7oG60af5qoghzB13ZpuhPrSP7CHJRcSm%2BmlLPCWKIwyxvAmWUqyBPY9lt%2FHGZbnaXmY9elUR%2B8Ec7nfW%2FM2f%2Bs1h%2BJuQbmmtv2WNvZE%2Fmu%2FaCJ0Udw4%2FU61HdUqVsdQGqnF%2Fy4qpOniePL3BuACgksnW4XvK3OaUk91toL5FRh19YDXGbS0oe9F%2FAzlkb6K2m7fBZ%2FEDt%2FJ4WQ%2Ftnw2hx599bxoc7rgVcghpATcOZ2Kzn7MUmTV5Rx3h%2BESTIsSBejIQS2OKPeJb1ZA%3D%3D&sign_type=RSA2&timestamp=2019-04-14+14%3A27%3A26&version=1.0
     * APPID : 2019031563532753
     * status : 1
     */

    private String msg;
    private String orderStr;
    private String APPID;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
