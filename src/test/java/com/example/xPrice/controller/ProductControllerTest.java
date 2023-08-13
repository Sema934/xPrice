package com.example.xPrice.controller;

import com.example.xPrice.dto.ProductDto;
import com.example.xPrice.request.ProductRequest;
import com.example.xPrice.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getProduct() throws Exception {
        ProductDto productDto = ProductDto.builder().build();

        Mockito.when(productService.getProduct(1)).thenReturn(productDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}", "1"));

        verify(productService).getProduct(1);
    }

    @Test
    public void getProducts() throws Exception {
        ProductDto productDto = ProductDto.builder().build();
        List<ProductDto> productDtoList = List.of(productDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/product"));

        verify(productService).getProducts();
    }

    @Test
    public void createProduct() throws Exception {
        ProductRequest productRequest = ProductRequest.builder().name("MacBook Air M2").brand("Apple").build();

        ArgumentCaptor<ProductRequest> productRequestArgumentCaptor = ArgumentCaptor.forClass(ProductRequest.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest)));

        verify(productService).createProduct(productRequestArgumentCaptor.capture());
        assertThat(productRequestArgumentCaptor.getValue()).usingRecursiveComparison()
                .ignoringFields("fieldToIgnore")
                .isEqualTo(productRequest);
    }
}