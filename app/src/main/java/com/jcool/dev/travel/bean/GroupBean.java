package com.jcool.dev.travel.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class GroupBean implements Parcelable {
    private String title;
    private String content;
    private String index;
    private String type;
    private String initRowIndex;
    private DataImageInfo DataInfo;
    private List<String> infoList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInitRowIndex() {
        return initRowIndex;
    }

    public void setInitRowIndex(String initRowIndex) {
        this.initRowIndex = initRowIndex;
    }

    public List<String> getInfoList() {
        return infoList;
    }

    public DataImageInfo getDataInfo() {
        return DataInfo;
    }

    public void setDataInfo(DataImageInfo dataInfo) {
        DataInfo = dataInfo;
    }

    public void setInfoList(List<String> infoList) {
        this.infoList = infoList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.index);
        dest.writeString(this.type);
        dest.writeString(this.initRowIndex);
        dest.writeParcelable(this.DataInfo, flags);
        dest.writeList(this.infoList);
    }

    public GroupBean() {
    }

    protected GroupBean(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.index = in.readString();
        this.type = in.readString();
        this.initRowIndex = in.readString();
        this.DataInfo = in.readParcelable(DataImageInfo.class.getClassLoader());
        this.infoList = new ArrayList<String>();
        in.readList(this.infoList, DataImageInfo.class.getClassLoader());
    }

    public static final Creator<GroupBean> CREATOR = new Creator<GroupBean>() {
        @Override
        public GroupBean createFromParcel(Parcel source) {
            return new GroupBean(source);
        }

        @Override
        public GroupBean[] newArray(int size) {
            return new GroupBean[size];
        }
    };

    @Override
    public String toString() {
        return "GroupBean{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", index='" + index + '\'' +
                ", type='" + type + '\'' +
                ", initRowIndex='" + initRowIndex + '\'' +
                ", DataInfo=" + DataInfo +
                ", infoList=" + infoList +
                '}';
    }
}
