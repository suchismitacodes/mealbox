package com.suchismitacodes.mealbox.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.suchismitacodes.mealbox.entity.Orders;
import com.suchismitacodes.mealbox.entity.User;
import com.suchismitacodes.mealbox.repository.OrderRepository;

@Component
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Orders> getOrders() {
        return orderRepository.findAll();
    }

    public void saveOrder(Orders order) {
        orderRepository.save(order);
    }

    public void updateOrder(int id, Orders order) {
        order.setOId(id);
        orderRepository.save(order);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    public List<Orders> getOrdersForUser(User user) {
        return orderRepository.findOrdersByUser(user);
    }
}
