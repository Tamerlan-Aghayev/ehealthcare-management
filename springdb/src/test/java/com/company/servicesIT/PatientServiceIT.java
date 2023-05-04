package com.company.servicesIT;

import com.company.entities.Patient;
import com.company.entities.Doctor;
import com.company.services.PatientService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceIT {

    @Autowired
    PatientService patientService;

    @Test
    @Transactional
    public void getPatient(){
        Patient patient= patientService.getById(1);
        Assert.assertEquals("must be name",patient.getName(),"name" );
    }
    @Test
    public void getAll(){
        List<Patient> list =patientService.getAll();
        Assert.assertEquals("must be 1", list.size(), 1);
    }
    @Test
    public void insertPatient(){
       Patient p=new Patient(2, "test", "test",1, "test");
       p.setDoctorId(new Doctor(1));
        patientService.insertAndUpdate(p);

        List<Patient>list=patientService.getAll();
        Assert.assertEquals("must be 2", list.size(), 2);
    }
    @Test
    public void deletePatient(){
        patientService.delete(2);
        List<Patient> list= patientService.getAll();
        Assert.assertEquals("must be 1", list.size(), 1);
    }
}
