package com.company.services;

import com.company.entities.Admin;
import com.company.repos.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private static BCryptPasswordEncoder crypt=new BCryptPasswordEncoder();
    public Admin getAdmin(String username){
        return adminRepository.getAdmin(username);
    }

    public List<Admin> getAll(){
        return adminRepository.findAll();
    }
    public void insertAdmin(Admin admin){
        admin.setPassword(crypt.encode(admin.getPassword()));
        adminRepository.saveAndFlush(admin);
    }
    public void deleteAdmin(int id){
        adminRepository.deleteById(id);
    }
}

