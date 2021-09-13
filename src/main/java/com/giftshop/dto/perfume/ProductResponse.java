package com.giftshop.dto.perfume;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String description;
    private String filename;
    private String product_name;
    private Double price;
    private String volume;
    private Double rating;
}
