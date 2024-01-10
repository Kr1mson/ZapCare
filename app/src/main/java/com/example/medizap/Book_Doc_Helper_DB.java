package com.example.medizap;

public class Book_Doc_Helper_DB {
    String uname, dname, hname, dept, fee,date,month;

    public Book_Doc_Helper_DB() {
    }

    public Book_Doc_Helper_DB(String uname, String dname, String hname, String dept, String fee,String date, String month) {
        this.uname = uname;
        this.dname = dname;
        this.hname = hname;
        this.dept = dept;
        this.fee = fee;
        this.date = date;
        this.month = month;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

}
