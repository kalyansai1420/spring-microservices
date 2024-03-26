package com.microservice.productservice.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repository.ProductRepo;
import com.microservice.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepo productRepo;

    @Override
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
            .name(productRequest.getName())
            .description(productRequest.getDescription())
            .price(productRequest.getPrice())
            .build();
        
        productRepo.save(product);
        log.info("Product created with id: {}", product.getId());
    }

    @Override
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepo.findAll();

        return products.stream().map(this::mapToProductReponse).toList();
    }

    private ProductResponse mapToProductReponse(Product product){
        return ProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .build();
    }
}
