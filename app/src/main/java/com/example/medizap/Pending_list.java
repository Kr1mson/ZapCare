package com.example.medizap;

public class Pending_list {
    String uname,date,dname,month;

    public Pending_list() {
    }

    public Pending_list(String uname, String date, String dname,String month) {
        this.uname = uname;
        this.date = date;
        this.dname = dname;
        this.month = month;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
