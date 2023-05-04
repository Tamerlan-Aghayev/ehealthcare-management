package com.company.services;

import com.company.entities.Doctor;
import com.company.repos.DoctorRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class DoctorServiceUnitTest {
    @Mock
    private DoctorRepository doctorRepository;
    @InjectMocks
        private DoctorService doctorService;
    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        List<Doctor> doctorList=new ArrayList<>();
        Doctor doctor1=new Doctor(1);
        Doctor doctor2=new Doctor(2);
        doctorList.add(doctor1);
        doctorList.add(doctor2);
        Mockito.when(doctorService.getAll()).thenReturn(doctorList);
        Mockito.when(doctorService.getById(1)).thenReturn(doctorList.get(0));
        Mockito.when(doctorService.getByNameSurnameField(null, null, null)).thenReturn(doctorList.get(0));

    }
    @Test
    public void getAll(){
        Assert.assertEquals("must be 2",doctorService.getAll().size(), 2);
        Mockito.verify(doctorRepository, Mockito.atLeastOnce()).getAll();
    }
    @Test
    public void getByNameSurnameField(){
        List<Doctor> list = doctorService.getAll();
        Assert.assertEquals("must be first", (doctorService.getByNameSurnameField(null, null, null)), list.get(0));
        Mockito.verify(doctorRepository, Mockito.atLeastOnce()).getByNameSurnameField(null, null, null);
    }
    @Test
    public void getById(){
        List<Doctor> list = doctorService.getAll();
        Assert.assertEquals("must be first", (doctorService.getById(1)), list.get(0));
        Mockito.verify(doctorRepository, Mockito.atLeastOnce()).getReferenceById(1);
    }
    @Test
    public void insertDoctor(){
        Doctor doctor=new Doctor();
        doctorService.insertAndUpdate(doctor);
        Mockito.verify(doctorRepository, Mockito.atLeastOnce()).saveAndFlush(doctor);
    }
    @Test
    public void deleteDoctor(){

        doctorService.delete(1);

        Mockito.verify(doctorRepository, Mockito.atLeastOnce()).deleteById(1);
    }
}
