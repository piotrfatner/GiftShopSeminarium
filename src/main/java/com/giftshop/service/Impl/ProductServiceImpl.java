package com.giftshop.service.Impl;

import com.giftshop.domain.Product;
import com.giftshop.repository.ProductRepository;
import com.giftshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> findAllPerfumes() {
        return productRepository.findAllByOrderByIdAsc();
    }

    @Override
    public List<Product> findAllProductByPriceDesc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<Product> filter(List<String> perfumers, List<String> genders, List<Integer> prices,List<Boolean> categories, boolean sortByPrice) {
        List<Product> productsList = new ArrayList<>();

        if (!perfumers.isEmpty() || !genders.isEmpty() || !prices.isEmpty() || !categories.isEmpty()) {
            if (!prices.isEmpty()) {
                productsList = productRepository.findByPriceBetween(Double.valueOf(prices.get(0)), Double.valueOf(prices.get(1)));
            }
            else if(!categories.isEmpty()){
                List<Long> categoriesIds = new ArrayList<>();
                for(int i=1; i<categories.size(); i++){
                    if(categories.get(i)){
                        categoriesIds.add((long) i);
                    }
                }
                productsList = productRepository.findByCategoryIdIn(categoriesIds);
            }
        } else {
            productsList = productRepository.findAllByOrderByIdAsc();
        }
        if (sortByPrice) {
            productsList.sort(Comparator.comparing(Product::getPrice));
        } else {
            productsList.sort((product1, product2) -> product2.getPrice().compareTo(product1.getPrice()));
        }
        return productsList;
    }
}
