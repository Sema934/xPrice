package com.example.xPrice.service;

import com.example.xPrice.dto.CurrencyDto;
import com.example.xPrice.request.CurrencyRequest;

import java.util.List;

public interface CurrencyService {

    CurrencyDto getCurrency(Integer currencyId);

    List<CurrencyDto> getCurrencies();

    void createCurrency(CurrencyRequest currencyDto);
}
