package com.crocostaud.stockmanagement.service;

import com.crocostaud.stockmanagement.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto, Long id);

    ProductDto getProduct(Long id);
    void delete(Long id);

    List<ProductDto> getAllProducts();
}
