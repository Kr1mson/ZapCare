package com.example.medizap;

public class Appointment_List {
    String uname,dname, hname, dept, fee,date,time, approval;
    public Appointment_List() {
    };
    public Appointment_List(String uname, String dname, String hname, String dept, String fee, String date, String time,String approval) {
        this.uname = uname;
        this.dname=dname;
        this.hname = hname;
        this.dept = dept;
        this.fee = fee;
        this.date=date;
        this.time=time;
        this.approval =approval;
    }
    public String getUName() {
        return uname;
    }

    public void setUName(String uname) {
        this.uname = uname;
    }
    public String getDName() {
        return dname;
    }

    public void setDName(String dname) {
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
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }
}
