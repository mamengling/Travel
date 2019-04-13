package com.jcool.dev.travel.bean;

public class VisaTargetInfo {

    /**
     * id : 1095923544024035330
     * parentId : 0
     * placeName : 日本
     * placeImage : http://cyitsapp.oss-cn-beijing.aliyuncs.com/8b30bdcc53bd4097b82a07514e9755f1.jpg
     * placeType : 02
     * sortId : null
     * createdBy : null
     * createdTime : 1550123509000
     * secondPlace : null
     */

    private String id;
    private String parentId;
    private String placeName;
    private String placeImage;
    private String placeType;
    private int sortId;
    private String createdBy;
    private long createdTime;
    private String secondPlace;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public int getSortId() {
        return sortId;
    }

    public void setSortId(int sortId) {
        this.sortId = sortId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }
}
