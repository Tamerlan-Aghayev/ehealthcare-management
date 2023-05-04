package com.company.servicesIT;

import com.company.entities.Admin;

import com.company.services.AdminService;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceIT {

    @Autowired
    AdminService adminService;


    @Test
    public void getAdmin(){
        Admin admin= adminService.getAdmin("username");
        Assert.assertEquals("must be 1",1,(long)admin.getId());
    }
    @Test
    public void getAll(){
        List<Admin>list =adminService.getAll();
        Assert.assertEquals("must be 1", 1,list.size());
    }
    @Test
    public void insertAdmin(){
        adminService.insertAdmin(new Admin(2, "test", "test","test", "test", "test"));
        Admin admin=adminService.getAdmin("test");
        Assert.assertEquals("must be test","test", admin.getRole());
    }
    @Test
    public void deleteAdmin(){
        adminService.deleteAdmin(9);
        List<Admin> list= adminService.getAll();
        Assert.assertEquals("must be 1",1, list.size() );
    }
}
