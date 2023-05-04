package com.company.services;

import com.company.entities.Admin;
import com.company.repos.AdminRepository;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;


public class AdminServiceUnitTest {
    @Mock
    private AdminRepository adminRepository;
    @InjectMocks
    private AdminService adminService;
    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        ArrayList<Admin> adminList=new ArrayList<>();
        Admin admin1=new Admin(1, "username", "password","USER", "name", "surname");
        Admin admin2=new Admin(1, "username2", "password2","USER2", "name2", "surname2");
        adminList.add(admin1);
        adminList.add(admin2);
        Mockito.when(adminService.getAll()).thenReturn(adminList);
        Mockito.when(adminService.getAdmin("username")).thenReturn(adminList.get(0));

    }
    @Test
    public void getAll(){
        Assert.assertEquals("must be 2",adminService.getAll().size(), 2);
        Mockito.verify(adminRepository, Mockito.atLeastOnce()).findAll();
    }
    @Test
    public void getAdmin(){
        Assert.assertEquals("must be first object", adminService.getAdmin("username").getUsername(), "username");
        Mockito.verify(adminRepository, Mockito.atLeastOnce()).getAdmin("username");

    }
    @Test
    public void insertAdmin(){
        Admin admin=new Admin(null, null, "password",null, null, null );
        adminService.insertAdmin(admin);
        Mockito.verify(adminRepository, Mockito.atLeastOnce()).saveAndFlush(admin);
    }
    @Test
    public void deleteAdmin(){

        adminService.deleteAdmin(1);

        Mockito.verify(adminRepository, Mockito.atLeastOnce()).deleteById(1);
    }
}
