package com.example.xPrice.service;

import com.example.xPrice.dto.PriceDetailDto;
import com.example.xPrice.request.PriceDetailRequest;

import java.util.List;

public interface PriceDetailService {

    List<PriceDetailDto> getPriceDetails(Integer productId, Integer currencyId);

    PriceDetailDto getPriceDetailsWithLowestPrice(Integer productId, Integer currencyId);

    void createPriceDetail(PriceDetailRequest request);

    void updatePriceDetailPrice(Integer priceDetailId, double salePrice);

}
