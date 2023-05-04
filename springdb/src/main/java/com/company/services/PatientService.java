package com.company.services;

import com.company.entities.Patient;
import com.company.repos.PatientRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    public List<Patient> getAll(){
        return (List<Patient>)(Object)patientRepository.getAll();
    }
    public Patient getById(int id){
        return (Patient)patientRepository.getReferenceById(id);
    }
    public void insertAndUpdate(Patient p){
        patientRepository.saveAndFlush(p);
    }
    public void delete(Object o){
        if (o instanceof Integer){
            patientRepository.deleteById((Integer) o);
        }
        else if(o instanceof Patient){
            patientRepository.delete((Patient)o);
        }
        else{
            System.out.println("error in delete function");
        }
    }
}
