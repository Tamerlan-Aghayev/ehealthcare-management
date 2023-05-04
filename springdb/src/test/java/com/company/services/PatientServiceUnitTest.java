package com.company.services;

import com.company.entities.Patient;
import com.company.repos.PatientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class PatientServiceUnitTest {
    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private PatientService patientService;
    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        List<Patient> patientList=new ArrayList<>();
        Patient patient1=new Patient(1);
        Patient patient2=new Patient(2);
        patientList.add(patient1);
        patientList.add(patient2);
        Mockito.when(patientService.getAll()).thenReturn(patientList);
        Mockito.when(patientService.getById(1)).thenReturn(patientList.get(0));

    }
    @Test
    public void getAll(){
        Assert.assertEquals("must be 2",patientService.getAll().size(), 2);
        Mockito.verify(patientRepository, Mockito.atLeastOnce()).getAll();
    }
    @Test
    public void getById(){
        List <Patient> list = patientService.getAll();
        Assert.assertEquals("must be 1", (patientService.getById(1)), list.get(0));
        Mockito.verify(patientRepository, Mockito.atLeastOnce()).getReferenceById(1);
    }
    @Test
    public void insertPatient(){
        Patient patient=new Patient();
        patientService.insertAndUpdate(patient);
        Mockito.verify(patientRepository, Mockito.atLeastOnce()).saveAndFlush(patient);
    }
    @Test
    public void deletePatient(){

        patientService.delete(1);

        Mockito.verify(patientRepository, Mockito.atLeastOnce()).deleteById(1);
    }
}
