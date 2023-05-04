package com.company.servicesIT;

import com.company.entities.Doctor;

import com.company.services.DoctorService;
import org.junit.*;
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
public class DoctorServiceIT {

    @Autowired
    DoctorService doctorService;

    @Test
    @Transactional
    public void getDoctor(){
        Doctor doctor= doctorService.getById(1);
        System.out.println(doctor);
        Assert.assertEquals("must be name","name",doctor.getName() );
    }
    @Test
    public void getAll(){
        List<Doctor> list =doctorService.getAll();
        Assert.assertEquals("must be 1", 1,list.size());
    }
    @Test

    public void insertDoctor(){
        doctorService.insertAndUpdate(new Doctor(2, "test", "test","test"));
        List<Doctor> list= doctorService.getAll();
        Assert.assertEquals("must be 2",2, list.size());
    }
    @Test

    public void deleteDoctor(){
        doctorService.delete(11);
        List<Doctor> list= doctorService.getAll();
        Assert.assertEquals("must be 1",1, list.size());
    }
}
