package com.example.xPrice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CurrencyRequest {

    private String code;
    private String name;
    private String countryCode;

}
