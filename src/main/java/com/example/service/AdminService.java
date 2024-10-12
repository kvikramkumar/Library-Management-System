package com.example.service;

import com.example.model.Admin;
import com.example.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public void registerAdmin(Admin admin){
        adminRepository.save(admin);
    }

    public boolean verifyAdmin(Admin admin) {

        Admin existingAdmin = adminRepository.findByUsername(admin.getUsername());
        if(existingAdmin != null){
            if(admin.getPassword().equals(existingAdmin.getPassword())){
                return true;
            }
        }
        return false;
    }
}
