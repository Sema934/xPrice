package com.example.xPrice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class PriceDetailRequest {

    @NotNull
    private Integer productId;

    @NotNull
    private Integer websiteId;

    @NotNull
    private double salePrice;

    @NotNull
    private Integer currencyId;

}
