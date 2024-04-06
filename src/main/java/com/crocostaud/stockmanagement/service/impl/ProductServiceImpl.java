package com.crocostaud.stockmanagement.service.impl;

import com.crocostaud.stockmanagement.dto.ProductDto;
import com.crocostaud.stockmanagement.model.Product;
import com.crocostaud.stockmanagement.repository.ProductRepository;
import com.crocostaud.stockmanagement.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepo = productRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .ref(productDto.getRef())
                .SupplierName(productDto.getSupplierName())
                .description(productDto.getDescription())
                .image(productDto.getImage())
                .build();
        Product savedProduct = productRepo.save(product);
        return mapToDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty())
            return null;
        productRepo.updateRefAndNameAndSupplierNameAndDescriptionById(productDto.getRef(), productDto.getName(), productDto.getSupplierName(), productDto.getDescription(), id);
        return mapToDto(productRepo.findById(id).get());
    }

    @Override
    public ProductDto getProduct(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        return productOptional.map(this::mapToDto).orElse(null);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .ref(product.getRef())
                .name(product.getName())
                .description(product.getDescription())
                .SupplierName(product.getSupplierName())
                .image(product.getImage())
                .build();
    }
}
