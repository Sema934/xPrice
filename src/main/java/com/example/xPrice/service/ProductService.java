package com.example.xPrice.service;

import com.example.xPrice.dto.ProductDto;
import com.example.xPrice.request.ProductRequest;

import java.util.List;

public interface ProductService {

    ProductDto getProduct(Integer productId);

    List<ProductDto> getProducts();

    void createProduct(ProductRequest productDto);

}
