package com.example.xPrice.repository;

import com.example.xPrice.domain.PriceDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceDetailRepository extends CrudRepository<PriceDetail, Integer> {

    List<PriceDetail> findByProduct_IdAndCurrency_IdOrderBySalePriceAsc(Integer productId, Integer currencyId);

    PriceDetail findFirstByProduct_IdAndCurrency_IdOrderBySalePriceAsc(Integer productId, Integer currencyId);

}
