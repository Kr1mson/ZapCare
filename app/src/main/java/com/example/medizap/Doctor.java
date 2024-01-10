package com.example.medizap;

public class Doctor {
    private String name;
    private String hospital_name;
    private String specialization;
    private String fee;
    private String btime;
    private String etime;

    public Doctor(String name,String hospital_name, String specialization, String fee,String btime, String etime) {
        this.name = name;
        this.hospital_name = hospital_name;
        this.specialization = specialization;
        this.fee = fee;
        this.btime = btime;
        this.etime = etime;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getFee() {
        return fee;
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
