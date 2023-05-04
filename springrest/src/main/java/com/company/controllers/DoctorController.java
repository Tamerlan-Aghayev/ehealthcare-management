package com.company.controllers;

import com.company.DTOs.DoctorDTO;
import com.company.DTOs.ResponseDTO;
import com.company.entities.Doctor;
import com.company.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @GetMapping("/doctors")
    public ResponseEntity<ResponseDTO> getDoctors() {
        List<Doctor> list =doctorService.getAll();
        List< DoctorDTO> dtoList=new ArrayList<>();
        for(Doctor d: list){
            dtoList.add(new DoctorDTO(d));
        }
        return ResponseEntity.ok(ResponseDTO.of(dtoList));
    }
    @GetMapping("/doctor")
    public ResponseEntity<ResponseDTO> getDoctorById(@RequestParam("id")int id) {
        try {
            DoctorDTO d = new DoctorDTO(doctorService.getById(id));
            return ResponseEntity.ok(ResponseDTO.of(d));
        }catch(Exception e){
            return ResponseEntity.ok(ResponseDTO.of("not found"));
        }
    }
    @PostMapping("/doctor")
    public ResponseEntity<ResponseDTO> insertAndUpdate(@RequestBody DoctorDTO dto){
        try{
            Doctor doctor=new Doctor();
            doctor.setName(dto.getName());
            doctor.setSurname(dto.getSurname());
            doctor.setField(dto.getField());
            doctorService.insertAndUpdate(doctor);
            return ResponseEntity.ok(ResponseDTO.of("successfully Updated"));
        }
        catch(Exception x){
            return ResponseEntity.ok(ResponseDTO.of("an error occured"));
        }
    }
    @DeleteMapping("/doctor")
    public ResponseEntity<ResponseDTO> deleteById(@RequestParam("id")int id) {
        try {
            doctorService.delete(id);
            return ResponseEntity.ok(ResponseDTO.of("successfully deleted"));
        } catch (Exception ex) {
            return ResponseEntity.ok(ResponseDTO.of("an error occured"));
        }
    }

}