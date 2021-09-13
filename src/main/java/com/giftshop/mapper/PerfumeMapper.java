package com.giftshop.mapper;

import com.giftshop.domain.Perfume;
import com.giftshop.domain.Product;
import com.giftshop.dto.perfume.PerfumeRequest;
import com.giftshop.dto.perfume.PerfumeResponse;
import com.giftshop.dto.perfume.ProductResponse;
import com.giftshop.repository.ProductRepository;
import com.giftshop.service.PerfumeService;
import com.giftshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PerfumeMapper {

    private final ModelMapper modelMapper;
    private final PerfumeService perfumeService;
    private final ProductService productService;

    private Perfume convertToEntity(PerfumeRequest perfumeRequest) {
        return modelMapper.map(perfumeRequest, Perfume.class);
    }

    PerfumeResponse convertToResponseDto(Perfume perfume) {
        return modelMapper.map(perfume, PerfumeResponse.class);
    }

    ProductResponse convertToResponseDto2(Product product) {
        return modelMapper.map(product, ProductResponse.class);
    }

    List<PerfumeResponse> convertListToResponseDto(List<Perfume> perfumes) {
        return perfumes.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }
    List<ProductResponse> convertListToProductResponseDto(List<Product> products) {
        return products.stream()
                .map(this::convertToResponseDto2)
                .collect(Collectors.toList());
    }

    public PerfumeResponse findPerfumeById(Long perfumeId) {
        return convertToResponseDto(perfumeService.findPerfumeById(perfumeId));
    }

    public ProductResponse findProductById(Long productId) {
        return convertToResponseDto2(productService.findProductById(productId));
    }

    public List<PerfumeResponse> findPerfumesByIds(List<Long> perfumesId) {
        return convertListToResponseDto(perfumeService.findPerfumesByIds(perfumesId));
    }

    public List<PerfumeResponse> findAllPerfumes() {
        return convertListToResponseDto(perfumeService.findAllPerfumes());
    }

    public List<ProductResponse> findAllProducts() {
        return convertListToProductResponseDto(productService.findAllProductByPriceDesc());
    }

    public List<PerfumeResponse> filter(List<String> perfumers, List<String> genders, List<Integer> prices, boolean sortByPrice) {
        return convertListToResponseDto(perfumeService.filter(perfumers, genders, prices, sortByPrice));
    }

    public List<ProductResponse> filterProduct(List<String> perfumers, List<String> genders, List<Integer> prices, List<Boolean> categories, boolean sortByPrice) {
        return convertListToProductResponseDto(productService.filter(perfumers, genders, prices, categories, sortByPrice));
    }

    public List<PerfumeResponse> findByPerfumerOrderByPriceDesc(String perfumer) {
        return convertListToResponseDto(perfumeService.findByPerfumerOrderByPriceDesc(perfumer));
    }

    public List<PerfumeResponse> findByPerfumeGenderOrderByPriceDesc(String perfumeGender) {
        return convertListToResponseDto(perfumeService.findByPerfumeGenderOrderByPriceDesc(perfumeGender));
    }

    public PerfumeResponse savePerfume(PerfumeRequest perfumeRequest, MultipartFile file) {
        return convertToResponseDto(perfumeService.savePerfume(convertToEntity(perfumeRequest), file));
    }

    public List<PerfumeResponse> deleteOrder(Long perfumeId) {
        return convertListToResponseDto(perfumeService.deletePerfume(perfumeId));
    }
}
