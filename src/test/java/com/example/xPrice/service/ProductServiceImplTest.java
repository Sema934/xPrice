package com.example.xPrice.service;

import com.example.xPrice.domain.Product;
import com.example.xPrice.dto.ProductDto;
import com.example.xPrice.repository.ProductRepository;
import com.example.xPrice.request.ProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProduct() {
        Product product = new Product();

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        ProductDto productDto = productService.getProduct(1);

        verify(productRepository).findById(1);
        assertThat(productDto).isNotNull();
    }

    @Test
    public void getProducts() {
        Product product = new Product();
        List<Product> productList = List.of(product);

        when(productRepository.findAll()).thenReturn(productList);

        List<ProductDto> productDtos = productService.getProducts();

        verify(productRepository).findAll();
        assertThat(productDtos).isNotNull();
    }

    @Test
    public void createProduct() {
        ProductRequest productRequest = ProductRequest.builder().name("MacBook Air M2").brand("Apple").build();

        productService.createProduct(productRequest);

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productArgumentCaptor.capture());
    }
}