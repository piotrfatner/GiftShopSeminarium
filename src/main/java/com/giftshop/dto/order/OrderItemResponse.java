package com.giftshop.dto.order;

import com.giftshop.dto.perfume.PerfumeResponse;
import com.giftshop.dto.perfume.ProductResponse;
import lombok.Data;

@Data
public class OrderItemResponse {
    private Long id;
    private Long amount;
    private Long quantity;
    private ProductResponse perfume;
}
