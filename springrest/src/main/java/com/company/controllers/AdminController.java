package com.company.controllers;

import com.company.DTOs.AdminDTO;
import com.company.DTOs.ResponseDTO;
import com.company.entities.Admin;
import com.company.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdminController{
    @Autowired
    private AdminService adminService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @GetMapping("/admins")
    public ResponseEntity<ResponseDTO> getAdmins() {
        List<Admin> list =adminService.getAll();
        List<AdminDTO> dtoList=new ArrayList<>();
        for(Admin d: list){
            dtoList.add(new AdminDTO(d));
        }
        return ResponseEntity.ok(ResponseDTO.of(dtoList));
    }
    @GetMapping("/admin")
    public ResponseEntity<ResponseDTO> getAdmin(@RequestParam("username")String username){
        AdminDTO d=new AdminDTO(adminService.getAdmin(username));
        return ResponseEntity.ok(ResponseDTO.of(d));
    }
    @PostMapping("/admin")
    public ResponseEntity<ResponseDTO> insertAdmin(@RequestBody Admin d){
        try{
            adminService.insertAdmin(d);
            return ResponseEntity.ok(ResponseDTO.of("successfully Updated"));
        }
        catch(Exception x){
            return ResponseEntity.ok(ResponseDTO.of("an error occured"));
        }
    }
    @DeleteMapping("/admin")
    public ResponseEntity<ResponseDTO> deleteAdmin(@RequestParam("id")int id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.ok(ResponseDTO.of("successfully deleted"));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ResponseDTO.of("an error occured"));

        }
    }
}
