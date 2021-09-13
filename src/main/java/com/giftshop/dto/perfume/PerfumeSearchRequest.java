package com.giftshop.dto.perfume;

import lombok.Data;

import java.util.List;

@Data
public class PerfumeSearchRequest {
    private List<String> perfumers;
    private List<String> genders;
    private List<Integer> prices;
    private List<Boolean> categories;
    private boolean sortByPrice;
    private String perfumer;
    private String perfumeGender;
}
