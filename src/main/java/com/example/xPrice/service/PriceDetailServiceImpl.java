package com.example.xPrice.service;

import com.example.xPrice.domain.Currency;
import com.example.xPrice.domain.PriceDetail;
import com.example.xPrice.domain.Product;
import com.example.xPrice.domain.Website;
import com.example.xPrice.dto.PriceDetailDto;
import com.example.xPrice.repository.CurrencyRepository;
import com.example.xPrice.repository.PriceDetailRepository;
import com.example.xPrice.repository.ProductRepository;
import com.example.xPrice.repository.WebsiteRepository;
import com.example.xPrice.request.PriceDetailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PriceDetailServiceImpl implements PriceDetailService{

    private final PriceDetailRepository priceDetailRepository;
    private final ProductRepository productRepository;
    private final WebsiteRepository websiteRepository;
    private final CurrencyRepository currencyRepository;

    @Override
    public List<PriceDetailDto> getPriceDetails(Integer productId, Integer currencyId) {
        List<PriceDetail> priceDetails = priceDetailRepository
                .findByProduct_IdAndCurrency_IdOrderBySalePriceAsc(productId, currencyId);

        if (!CollectionUtils.isEmpty(priceDetails)) {
            return priceDetails.stream().map(priceDetail -> priceDetail.toDto()).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public PriceDetailDto getPriceDetailsWithLowestPrice(Integer productId, Integer currencyId) {
        PriceDetail priceDetail = priceDetailRepository
                .findFirstByProduct_IdAndCurrency_IdOrderBySalePriceAsc(productId, currencyId);

        if (Objects.nonNull(priceDetail)) {
            return priceDetail.toDto();
        }

        return PriceDetailDto.builder().build();
    }

    @Override
    public void createPriceDetail(PriceDetailRequest request) {

        Optional<Product> product = productRepository.findById(request.getProductId());
        Optional<Website> website = websiteRepository.findById(request.getWebsiteId());
        Optional<Currency> currency = currencyRepository.findById(request.getCurrencyId());

        PriceDetail priceDetail = new PriceDetail();

        if (product.isPresent()) {
            priceDetail.setProduct(product.get());
        }
        if (website.isPresent()) {
            priceDetail.setWebsite(website.get());
        }
        if (currency.isPresent()) {
            priceDetail.setCurrency(currency.get());
        }

        priceDetail.setSalePrice(request.getSalePrice());

        priceDetailRepository.save(priceDetail);
    }

    @Override
    public void updatePriceDetailPrice(Integer priceDetailId, double salePrice) {
        Optional<PriceDetail> priceDetail = priceDetailRepository.findById(priceDetailId);

        if(priceDetail.isPresent()) {
            PriceDetail updatedPriceDetail = priceDetail.get();
            updatedPriceDetail.setSalePrice(salePrice);

            priceDetailRepository.save(updatedPriceDetail);
        }
    }
}
