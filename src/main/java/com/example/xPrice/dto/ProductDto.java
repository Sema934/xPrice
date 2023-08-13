package com.example.xPrice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class ProductDto {

    private Integer id;
    private String name;
    private String brand;

}
