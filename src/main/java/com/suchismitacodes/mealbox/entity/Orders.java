package com.suchismitacodes.mealbox.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int oId;

    private String oName;

    private double oPrice;

    private int oQuantity;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_ammout")
    private double totalAmmout;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_u_id")
    private User user;
}
