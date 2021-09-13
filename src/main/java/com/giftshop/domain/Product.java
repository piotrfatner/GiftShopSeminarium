package com.giftshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 109, allocationSize = 1)
    private Long id;
    private String description;
    private String filename;
    private String product_name;
    private Double price;
    private String volume;
    private Double rating;
    private Long categoryId;
/*
    @OneToMany
    private List<Review> reviews;*/
}
