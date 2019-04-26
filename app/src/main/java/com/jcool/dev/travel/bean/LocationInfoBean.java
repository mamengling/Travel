package com.jcool.dev.travel.bean;

public class LocationInfoBean {
    private String province;
    private String city;
    /**区县*/
    private String area;
    private String address;
    private String addressSearch;
    private double longitude;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddressSearch() {
        return addressSearch;
    }

    public void setAddressSearch(String addressSearch) {
        this.addressSearch = addressSearch;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private double latitude;
}
