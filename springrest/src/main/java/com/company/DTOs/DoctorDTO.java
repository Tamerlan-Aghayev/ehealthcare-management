package com.company.DTOs;

import com.company.entities.Doctor;
import com.company.entities.Patient;

import java.util.ArrayList;
import java.util.List;

public class DoctorDTO {
    private int id;
    private String name;
    private String surname;
    private String field;
    private List<PatientDTO> patientDTOList;
    public DoctorDTO(){

    }
    public DoctorDTO(Doctor d){
        this.id=d.getId();
        this.name=d.getName();
        this.surname=d.getSurname();
        this.field=d.getField();
        List<Patient> list=d.getPatientList();
        List<PatientDTO> result = new ArrayList<>();
        for(Patient p :list){
            result.add(new PatientDTO(p));
        }
        this.patientDTOList=result;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<PatientDTO> getPatientDTOList() {
        return patientDTOList;
    }

    public void setPatientDTOList(List<PatientDTO> patientDTOList) {
        this.patientDTOList = patientDTOList;
    }
}
