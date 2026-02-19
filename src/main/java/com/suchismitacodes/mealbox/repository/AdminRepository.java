package com.suchismitacodes.mealbox.repository;

import org.springframework.data.repository.CrudRepository;

import com.suchismitacodes.mealbox.entity.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

    Admin findByAdminEmail(String email);
}
