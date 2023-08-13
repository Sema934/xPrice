package com.example.xPrice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PriceDetailDto {

    private int id;
    private ProductDto product;
    private WebsiteDto website;
    private LocalDate createdDate;
    private LocalDate updatedDate;
    private double salePrice;
    private CurrencyDto currency;

}
