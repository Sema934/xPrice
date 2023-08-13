package com.example.xPrice.service;

import com.example.xPrice.domain.PriceDetail;
import com.example.xPrice.dto.PriceDetailDto;
import com.example.xPrice.repository.CurrencyRepository;
import com.example.xPrice.repository.PriceDetailRepository;
import com.example.xPrice.repository.ProductRepository;
import com.example.xPrice.repository.WebsiteRepository;
import com.example.xPrice.request.PriceDetailRequest;
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
public class PriceDetailServiceImplTest {

    @InjectMocks
    private PriceDetailServiceImpl priceDetailService;

    @Mock
    private PriceDetailRepository priceDetailRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private WebsiteRepository websiteRepository;
    @Mock
    private CurrencyRepository currencyRepository;

    @Test
    public void getPriceDetails() {
        Integer productId = 1;
        Integer currencyId = 1;
        PriceDetail priceDetail = new PriceDetail();
        List<PriceDetail> priceDetailList = List.of(priceDetail);

        when(priceDetailRepository.findByProduct_IdAndCurrency_IdOrderBySalePriceAsc(productId, currencyId))
                .thenReturn(priceDetailList);

        List<PriceDetailDto> priceDetailDtoList = priceDetailService.getPriceDetails(productId, currencyId);

        verify(priceDetailRepository).findByProduct_IdAndCurrency_IdOrderBySalePriceAsc(productId, currencyId);
        assertThat(priceDetailDtoList).isNotNull();
    }

    @Test
    public void getPriceDetailsWithLowestPrice() {
        Integer productId = 1;
        Integer currencyId = 1;
        PriceDetail priceDetail = new PriceDetail();

        when(priceDetailRepository.findFirstByProduct_IdAndCurrency_IdOrderBySalePriceAsc(productId, currencyId))
                .thenReturn(priceDetail);

        PriceDetailDto priceDetailDto = priceDetailService.getPriceDetailsWithLowestPrice(productId, currencyId);

        verify(priceDetailRepository).findFirstByProduct_IdAndCurrency_IdOrderBySalePriceAsc(productId, currencyId);
        assertThat(priceDetailDto).isNotNull();
    }

    @Test
    public void createPriceDetail() {
        PriceDetailRequest priceDetailRequest = PriceDetailRequest.builder()
                .productId(1)
                .websiteId(1)
                .salePrice(90000)
                .currencyId(1).build();

        priceDetailService.createPriceDetail(priceDetailRequest);

        ArgumentCaptor<PriceDetail> priceDetailArgumentCaptor = ArgumentCaptor.forClass(PriceDetail.class);
        verify(priceDetailRepository).save(priceDetailArgumentCaptor.capture());
    }

    @Test
    public void updatePriceDetailPrice() {
        Integer priceDetailId = 1;
        double salePrice = 80000;
        PriceDetail priceDetail = new PriceDetail();

        when(priceDetailRepository.findById(priceDetailId)).thenReturn(Optional.of(priceDetail));

        priceDetailService.updatePriceDetailPrice(priceDetailId, salePrice);

        ArgumentCaptor<PriceDetail> priceDetailArgumentCaptor = ArgumentCaptor.forClass(PriceDetail.class);
        verify(priceDetailRepository).findById(priceDetailId);
        verify(priceDetailRepository).save(priceDetailArgumentCaptor.capture());
    }
}