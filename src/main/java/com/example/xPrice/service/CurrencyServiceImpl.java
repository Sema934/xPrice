package com.example.xPrice.service;

import com.example.xPrice.domain.Currency;
import com.example.xPrice.dto.CurrencyDto;
import com.example.xPrice.repository.CurrencyRepository;
import com.example.xPrice.request.CurrencyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyDto getCurrency(Integer currencyId) {
        Optional<Currency> currency = currencyRepository.findById(currencyId);

        if (currency.isPresent()) {
            return currency.get().toDTO();
        }

        return CurrencyDto.builder().build();
    }

    @Override
    public List<CurrencyDto> getCurrencies() {
        List<Currency> currencyList = (List<Currency>) currencyRepository.findAll();

        if (!CollectionUtils.isEmpty(currencyList)) {
            return currencyList.stream().map(currency -> currency.toDTO()).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public void createCurrency(CurrencyRequest currencyRequest) {
        Currency currency = new Currency();
        currency.setName(currencyRequest.getName());
        currency.setCode(currencyRequest.getCode());
        currency.setCountryCode(currencyRequest.getCountryCode());

        currencyRepository.save(currency);
    }
}
