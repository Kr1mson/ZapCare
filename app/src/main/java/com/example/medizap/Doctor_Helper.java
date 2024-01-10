package com.example.medizap;

public class Doctor_Helper {
    String name, hname, dept, fee,btime,etime;

    public Doctor_Helper() {
    }

    public Doctor_Helper(String name, String hname, String dept, String fee,String btime, String etime) {
        this.name = name;
        this.hname = hname;
        this.dept = dept;
        this.fee = fee;
        this.btime = btime;
        this. etime = etime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBtime() {
        return btime;
    }

    public void setBtime(String btime) {
        this.btime = btime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }
}
