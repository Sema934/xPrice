package com.example.xPrice.controller;

import com.example.xPrice.dto.CurrencyDto;
import com.example.xPrice.request.CurrencyRequest;
import com.example.xPrice.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping(value = "/{currencyId}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyDto getCurrency(@NotNull @PathVariable("currencyId") Integer currencyId) {
        return currencyService.getCurrency(currencyId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyDto> getCurrencies() {
        return currencyService.getCurrencies();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void createCurrency(@RequestBody CurrencyRequest request) {
        currencyService.createCurrency(request);
    }
}
