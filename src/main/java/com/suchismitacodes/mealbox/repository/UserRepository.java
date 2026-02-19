package com.suchismitacodes.mealbox.repository;

import org.springframework.data.repository.CrudRepository;

import com.suchismitacodes.mealbox.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUemail(String email);
}
