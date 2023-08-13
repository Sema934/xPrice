package com.example.xPrice.service;

import com.example.xPrice.domain.Product;
import com.example.xPrice.dto.ProductDto;
import com.example.xPrice.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.xPrice.repository.ProductRepository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto getProduct(Integer productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            return product.get().toDTO();
        }

        return ProductDto.builder().build();
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> productList = (List<Product>) productRepository.findAll();

        if (!CollectionUtils.isEmpty(productList)) {
            return productList.stream().map(product -> product.toDTO()).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setBrand(productRequest.getBrand());

        productRepository.save(product);
    }
}
