package com.crocostaud.stockmanagement.controller;


import com.crocostaud.stockmanagement.dto.ProductDto;
import com.crocostaud.stockmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto){
        ProductDto savedProduct = productService.createProduct(productDto);
        return ResponseEntity.ok(savedProduct);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> get(@PathVariable Long productId){
        ProductDto product = productService.getProduct(productId);

        if (product == null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll(){
        return ResponseEntity.ok( productService.getAllProducts() );
    }
}
