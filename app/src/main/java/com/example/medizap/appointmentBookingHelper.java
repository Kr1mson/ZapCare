package com.example.medizap;

public class appointmentBookingHelper {
    private String name;
    private String hospital_name;
    private String specialization;
    private String fee;

    public appointmentBookingHelper(String name, String hospital_name, String specialization, String fee) {
        this.name = name;
        this.hospital_name = hospital_name;
        this.specialization = specialization;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
