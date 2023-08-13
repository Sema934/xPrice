package com.example.xPrice.controller;

import com.example.xPrice.dto.CurrencyDto;
import com.example.xPrice.request.CurrencyRequest;
import com.example.xPrice.service.CurrencyService;
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
@WebMvcTest(controllers = CurrencyController.class)
public class CurrencyControllerTest {

    @MockBean
    private CurrencyService currencyService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getCurrency() throws Exception {
        CurrencyDto currencyDto = CurrencyDto.builder().build();

        Mockito.when(currencyService.getCurrency(1)).thenReturn(currencyDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/currency/{currencyId}", "1"));

        verify(currencyService).getCurrency(1);
    }

    @Test
    public void getCurrencies() throws Exception {
        CurrencyDto currencyDto = CurrencyDto.builder().build();
        List<CurrencyDto> currencyDtoList = List.of(currencyDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/currency"));

        verify(currencyService).getCurrencies();
    }

    @Test
    public void createCurrency() throws Exception {
        CurrencyRequest currencyRequest = CurrencyRequest.builder().name("Turkish Lira").code("TRY").build();

        ArgumentCaptor<CurrencyRequest> currencyRequestArgumentCaptor = ArgumentCaptor.forClass(CurrencyRequest.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currencyRequest)));

        verify(currencyService).createCurrency(currencyRequestArgumentCaptor.capture());
        assertThat(currencyRequestArgumentCaptor.getValue()).usingRecursiveComparison()
                .ignoringFields("fieldToIgnore")
                .isEqualTo(currencyRequest);
    }
}