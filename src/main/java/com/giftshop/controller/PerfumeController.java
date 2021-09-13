package com.giftshop.controller;

import com.giftshop.dto.perfume.PerfumeResponse;
import com.giftshop.dto.perfume.ProductResponse;
import com.giftshop.mapper.PerfumeMapper;
import com.giftshop.dto.GraphQLRequest;
import com.giftshop.dto.perfume.PerfumeSearchRequest;
import com.giftshop.service.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/perfumes")
public class PerfumeController {

    private final PerfumeMapper perfumeMapper;
    private final GraphQLProvider graphQLProvider;

    @GetMapping
    public ResponseEntity<List<PerfumeResponse>> getAllPerfumes() {
        return ResponseEntity.ok(perfumeMapper.findAllPerfumes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfumeResponse> getPerfume(@PathVariable("id") Long perfumeId) {
        return ResponseEntity.ok(perfumeMapper.findPerfumeById(perfumeId));
    }

    @GetMapping("/idNew/{idNew}")
    public ResponseEntity<ProductResponse> getProducts(@PathVariable("idNew") Long perfumeId) {
        return ResponseEntity.ok(perfumeMapper.findProductById(perfumeId));
    }

    @GetMapping("/idNeww")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(perfumeMapper.findAllProducts());
    }

    @PostMapping("/ids")
    public ResponseEntity<List<PerfumeResponse>> getPerfumesByIds(@RequestBody List<Long> perfumesIds) {
        return ResponseEntity.ok(perfumeMapper.findPerfumesByIds(perfumesIds));
    }

    @PostMapping("/search")
    public ResponseEntity<List<PerfumeResponse>> findPerfumesByFilterParams(@RequestBody PerfumeSearchRequest filter) {
        return ResponseEntity.ok(perfumeMapper.filter(filter.getPerfumers(), filter.getGenders(), filter.getPrices(), filter.isSortByPrice()));
    }

    @PostMapping("/searchProduct")
    public ResponseEntity<List<ProductResponse>> findProductsByFilterParams(@RequestBody PerfumeSearchRequest filter) {
        return ResponseEntity.ok(perfumeMapper.filterProduct(filter.getPerfumers(), filter.getGenders(), filter.getPrices(), filter.getCategories(), filter.isSortByPrice()));
    }

    @PostMapping("/search/gender")
    public ResponseEntity<List<PerfumeResponse>> findByPerfumeGender(@RequestBody PerfumeSearchRequest filter) {
        return ResponseEntity.ok(perfumeMapper.findByPerfumeGenderOrderByPriceDesc(filter.getPerfumeGender()));
    }

    @PostMapping("/search/perfumer")
    public ResponseEntity<List<PerfumeResponse>> findByPerfumer(@RequestBody PerfumeSearchRequest filter) {
        return ResponseEntity.ok(perfumeMapper.findByPerfumerOrderByPriceDesc(filter.getPerfumer()));
    }

    @PostMapping("/graphql/ids")
    public ResponseEntity<ExecutionResult> getPerfumesByIdsQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/perfumes")
    public ResponseEntity<ExecutionResult> getAllPerfumesByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }

    @PostMapping("/graphql/perfume")
    public ResponseEntity<ExecutionResult> getPerfumeByQuery(@RequestBody GraphQLRequest request) {
        return ResponseEntity.ok(graphQLProvider.getGraphQL().execute(request.getQuery()));
    }
}
