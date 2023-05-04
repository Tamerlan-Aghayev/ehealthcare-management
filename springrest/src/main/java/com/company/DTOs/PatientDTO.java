package com.company.DTOs;

import com.company.entities.Patient;

public class PatientDTO {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String diagnose;
    private String doctorName;
    private String doctorSurname;
    private String doctorField;
    public PatientDTO(){}
    public PatientDTO(Patient p){
        this.id= p.getId();
        this.name=p.getName();
        this.surname=p.getSurname();
        this.age=p.getAge();
        this.diagnose=p.getDiagnose();
        this.doctorName=p.getDoctorId().getName();
        this.doctorSurname=p.getDoctorId().getSurname();
        this.doctorField=p.getDoctorId().getField();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorSurname() {
        return doctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        this.doctorSurname = doctorSurname;
    }

    public String getDoctorField() {
        return doctorField;
    }

    public void setDoctorField(String field) {
        this.doctorField = field;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }
}
