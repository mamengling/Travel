package com.jcool.dev.travel.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class DataImageInfo implements Parcelable {
    private String url;
    private String id;
    private String userId;
    private String orderId;
    private String dataName;
    private String dataDesc;
    private String createTime;
    private String dataStatus;
    private String reason;
    private String updateTime;
    private String custId;
    private String dataImage;
    private String dataIndex;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.id);
        dest.writeString(this.userId);
        dest.writeString(this.orderId);
        dest.writeString(this.dataName);
        dest.writeString(this.dataDesc);
        dest.writeString(this.createTime);
        dest.writeString(this.dataStatus);
        dest.writeString(this.reason);
        dest.writeString(this.updateTime);
        dest.writeString(this.custId);
        dest.writeString(this.dataImage);
        dest.writeString(this.dataIndex);
    }

    public DataImageInfo() {
    }

    protected DataImageInfo(Parcel in) {
        this.url = in.readString();
        this.id = in.readString();
        this.userId = in.readString();
        this.orderId = in.readString();
        this.dataName = in.readString();
        this.dataDesc = in.readString();
        this.createTime = in.readString();
        this.dataStatus = in.readString();
        this.reason = in.readString();
        this.updateTime = in.readString();
        this.custId = in.readString();
        this.dataImage = in.readString();
        this.dataIndex = in.readString();
    }

    public static final Creator<DataImageInfo> CREATOR = new Creator<DataImageInfo>() {
        @Override
        public DataImageInfo createFromParcel(Parcel source) {
            return new DataImageInfo(source);
        }

        @Override
        public DataImageInfo[] newArray(int size) {
            return new DataImageInfo[size];
        }
    };
}
