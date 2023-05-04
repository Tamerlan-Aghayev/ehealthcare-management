package com.company.controllers;

import com.company.DTOs.PatientDTO;
import com.company.DTOs.ResponseDTO;

import com.company.entities.Patient;
import com.company.services.DoctorService;
import com.company.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @GetMapping("/patients")
    public ResponseEntity<ResponseDTO> getDoctors() {
        List<Patient> list =patientService.getAll();
        List<PatientDTO> dtoList=new ArrayList<>();
        for(Patient d: list){
            dtoList.add(new PatientDTO(d));
        }
        return ResponseEntity.ok(ResponseDTO.of(dtoList));
    }
    @GetMapping("/patient")
    public ResponseEntity<ResponseDTO> getPatientById(@RequestParam("id")int id){
        try {
            PatientDTO d = new PatientDTO(patientService.getById(id));
            return ResponseEntity.ok(ResponseDTO.of(d));
        }catch(Exception ex){
            return ResponseEntity.ok(ResponseDTO.of("not found"));
        }
    }
    @PostMapping("/patient")
    public ResponseEntity<ResponseDTO> insertAndUpdate(@RequestBody PatientDTO dto){
        try{
            Patient patient=new Patient();
            patient.setName(dto.getName());
            patient.setSurname(dto.getSurname());
            patient.setAge(dto.getAge());
            patient.setDiagnose(dto.getDiagnose());
            patient.setDoctorId(doctorService.getByNameSurnameField(dto.getDoctorName(), dto.getDoctorSurname(), dto.getDoctorField()));
            patientService.insertAndUpdate(patient);
            return ResponseEntity.ok(ResponseDTO.of("successfully Updated"));
        }
        catch(Exception x){
            return ResponseEntity.ok(ResponseDTO.of("an error occured"));
        }
    }
    @DeleteMapping("/patient")
    public ResponseEntity<ResponseDTO> deleteById(@RequestParam("id")int id) {
        try {
            patientService.delete(id);
            return ResponseEntity.ok(ResponseDTO.of("successfully deleted"));
        } catch (Exception ex) {
            return ResponseEntity.ok(ResponseDTO.of("an error occured"));
        }
    }
}
