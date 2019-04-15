package com.jcool.dev.travel.bean;

public class OrderInfoPayWx {

    /**
     * msg : 订单生成成功
     * nonce_str : TaxBDKJlZWptLZmZ
     * sign : EBA5808A4910CC312FC4892AD7909881
     * return_msg : OK
     * mch_id : 1485163922
     * nonceStr : 7b34c58e64a64ed78a0fc1475e813e79
     * prepay_id : wx14164748564221b7b60af2c50296319294
     * timeStamp : 1555231653
     * paySign : F2AAA259F98F2A22CFC20C990DBF81B5
     * appid : wxd31e1a84a800e3ae
     * trade_type : APP
     * result_code : SUCCESS
     * partnerid : 1485163922
     * return_code : SUCCESS
     * status : 1
     */

    private String msg;
    private String nonce_str;
    private String sign;
    private String return_msg;
    private String mch_id;
    private String nonceStr;
    private String prepay_id;
    private String timeStamp;
    private String paySign;
    private String appid;
    private String trade_type;
    private String result_code;
    private String partnerid;
    private String return_code;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
