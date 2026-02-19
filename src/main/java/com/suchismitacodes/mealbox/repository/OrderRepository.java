package com.suchismitacodes.mealbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suchismitacodes.mealbox.entity.Orders;
import com.suchismitacodes.mealbox.entity.User;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findOrdersByUser(User user);
}
