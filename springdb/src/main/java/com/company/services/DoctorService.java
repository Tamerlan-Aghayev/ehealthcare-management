package com.company.services;

import com.company.entities.Doctor;
import com.company.repos.DoctorRepository;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAll(){
        return doctorRepository.getAll();
    }
    public Doctor getById(int id){
        return (Doctor)doctorRepository.getReferenceById(id);
    }
    public Doctor getByNameSurnameField(String name, String surname, String field){
        return doctorRepository.getByNameSurnameField(name, surname, field);
    }
    public void insertAndUpdate(Doctor p){
        doctorRepository.saveAndFlush(p);
    }
    public void delete(int o) {

            doctorRepository.deleteById(o);
    }
}
