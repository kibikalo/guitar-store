package com.kibikalo.store.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String name;

    @Column( nullable = false)
    private String brand;

    @Column( nullable = false)
    private String description;

    @Column(name = "price_usd", nullable = false)
    private int priceUsd;

    @Column(name = "image_path", nullable = false)
    private String imagePath;
}
