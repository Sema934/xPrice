package com.example.xPrice.controller;

import com.example.xPrice.dto.PriceDetailDto;
import com.example.xPrice.request.PriceDetailRequest;
import com.example.xPrice.service.PriceDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pricedetail")
public class PriceDetailController {

    private final PriceDetailService priceDetailService;

    @GetMapping(value = "/{productId}/{currencyId}/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PriceDetailDto> getPriceDetails(@NotNull @PathVariable("productId") Integer productId,
                                                @NotNull @PathVariable("currencyId") Integer currencyId) {
        return priceDetailService.getPriceDetails(productId, currencyId);
    }

    @GetMapping(value = "/{productId}/{currencyId}")
    @ResponseStatus(HttpStatus.OK)
    public PriceDetailDto getPriceDetailsWithLowestPrice(
            @NotNull @PathVariable("productId") Integer productId,
            @NotNull @PathVariable("currencyId") Integer currencyId) {
        return priceDetailService.getPriceDetailsWithLowestPrice(productId, currencyId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void createPriceDetail(@Valid @RequestBody PriceDetailRequest request) {
        priceDetailService.createPriceDetail(request);
    }

    @PutMapping("/{priceDetailId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePriceDetailPrice(@PathVariable("priceDetailId") Integer priceDetailId,
                                       @RequestParam double salePrice) {
        priceDetailService.updatePriceDetailPrice(priceDetailId, salePrice);
    }

}
