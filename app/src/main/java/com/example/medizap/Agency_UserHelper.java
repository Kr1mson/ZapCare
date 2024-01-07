package com.example.medizap;

public class Agency_UserHelper {
    String ag_name, h_no;
    String latitude, longitude;
    public Agency_UserHelper() {
        // Default constructor required for calls to DataSnapshot.getValue(Agency_UserHelper.class)
    }

    public Agency_UserHelper(String ag_name, String h_no, String latitude, String longitude) {
        this.ag_name = ag_name;
        this.h_no = h_no;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAg_name() {
        return ag_name;
    }

    public void setAg_name(String ag_name) {
        this.ag_name = ag_name;
    }

    public String getH_no() {
        return h_no;
    }

    public void setH_no(String h_no) {
        this.h_no = h_no;
    }

}
