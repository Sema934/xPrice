package com.example.xPrice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CurrencyDto {

    private int id;
    private String code;
    private String name;
    private String countryCode;

}
