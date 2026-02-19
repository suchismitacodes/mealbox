package com.suchismitacodes.mealbox.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.suchismitacodes.mealbox.entity.Admin;
import com.suchismitacodes.mealbox.repository.AdminRepository;

@Component
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> getAll() {
        List<Admin> admins = new ArrayList<>();
        adminRepository.findAll().forEach(admins::add);
        return admins;
    }

    public Admin getAdmin(int id) {
        return adminRepository.findById(id).orElseThrow();
    }

    public void update(Admin admin, int id) {
        admin.setAdminId(id);
        for (Admin a : getAll()) {
            if (a.getAdminId() == id) {
                adminRepository.save(admin);
                break;
            }
        }
    }

    public void delete(int id) {
        adminRepository.deleteById(id);
    }

    public void addAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin validateAdminCredentials(String email, String password) {
        Admin admin = adminRepository.findByAdminEmail(email);
        if (admin != null && admin.getAdminPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}
