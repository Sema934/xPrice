package com.example.xPrice.controller;

import com.example.xPrice.dto.ProductDto;
import com.example.xPrice.request.ProductRequest;
import com.example.xPrice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getProduct(@NotNull @PathVariable("productId") Integer productId) {
        return productService.getProduct(productId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void createProduct(@RequestBody ProductRequest request) {
        productService.createProduct(request);
    }
}
