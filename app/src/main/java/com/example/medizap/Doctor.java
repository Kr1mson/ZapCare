package com.example.medizap;

public class Doctor {
    private String name;
    private String specialization;
    private String fee;

    public Doctor(String name, String specialization, String fee) {
        this.name = name;
        this.specialization = specialization;
        this.fee = fee;
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
}
