package com.giftshop.service;

import com.giftshop.domain.Product;

import java.util.List;

public interface ProductService {

    Product findProductById(Long productId);

    List<Product> findAllPerfumes();

    List<Product> findAllProductByPriceDesc();
    List<Product> filter(List<String> perfumers, List<String> genders, List<Integer> prices, List<Boolean> categories, boolean sortByPrice);
}
