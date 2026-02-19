package com.suchismitacodes.mealbox.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    private String pname;

    private double pprice;

    private String pdescription;

    private String category;

    private String imageUrl;

    private int rating;

    public Product(String pname, double pprice, String pdescription, String category, String imageUrl, int rating) {
        this.pname = pname;
        this.pprice = pprice;
        this.pdescription = pdescription;
        this.category = category;
        this.imageUrl = imageUrl;
        this.rating = rating;
    }
}
