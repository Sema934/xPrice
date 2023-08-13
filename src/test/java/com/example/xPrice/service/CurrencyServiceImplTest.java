package com.example.xPrice.service;

import com.example.xPrice.domain.Currency;
import com.example.xPrice.dto.CurrencyDto;
import com.example.xPrice.repository.CurrencyRepository;
import com.example.xPrice.request.CurrencyRequest;
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
public class CurrencyServiceImplTest {

    @InjectMocks
    private CurrencyServiceImpl currencyService;

    @Mock
    private CurrencyRepository currencyRepository;

    @Test
    public void getCurrency() {
        Currency currency = new Currency();

        when(currencyRepository.findById(1)).thenReturn(Optional.of(currency));

        CurrencyDto currencyDto = currencyService.getCurrency(1);

        verify(currencyRepository).findById(1);
        assertThat(currencyDto).isNotNull();
    }

    @Test
    public void getCurrencies() {
        Currency currency = new Currency();
        List<Currency> currencyList = List.of(currency);

        when(currencyRepository.findAll()).thenReturn(currencyList);

        List<CurrencyDto> currencyDtos = currencyService.getCurrencies();

        verify(currencyRepository).findAll();
        assertThat(currencyDtos).isNotNull();
    }

    @Test
    public void createCurrency() {
        CurrencyRequest currencyRequest = CurrencyRequest.builder().name("Turkish Lira").code("TRY").build();

        currencyService.createCurrency(currencyRequest);

        ArgumentCaptor<Currency> currencyRequestArgumentCaptor = ArgumentCaptor.forClass(Currency.class);
        verify(currencyRepository).save(currencyRequestArgumentCaptor.capture());
    }
}