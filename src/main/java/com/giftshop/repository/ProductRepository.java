package com.giftshop.repository;

import com.giftshop.domain.Perfume;
import com.giftshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIdIn(List<Long> productIds);

    List<Product> findAllByOrderByIdAsc();

    List<Product> findAllByOrderByPriceAsc();

    List<Product> findByPriceBetween(Double startingPrice, Double endingPrice);

    List<Product> findByCategoryIdIn(List<Long> categoriesIds);
}
