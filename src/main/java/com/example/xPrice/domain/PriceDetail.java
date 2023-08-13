package com.example.xPrice.domain;

import com.example.xPrice.dto.PriceDetailDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="price_detail")
public class PriceDetail {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(targetEntity=Product.class)
    @JoinColumn(name = "product_id", referencedColumnName="id")
    private Product product;

    @ManyToOne(targetEntity=Website.class)
    @JoinColumn(name = "website_id", referencedColumnName= "id")
    private Website website;

    @CreationTimestamp
    @Column(name="created_date")
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name="updated_date")
    private LocalDate updatedDate;

    @Column(name="sale_price")
    private double salePrice;

    @ManyToOne(targetEntity=Currency.class)
    @JoinColumn(name="currency_id", referencedColumnName="id")
    private Currency currency;

    public PriceDetailDto toDto() {
        PriceDetailDto dto = PriceDetailDto.builder()
                .id(getId())
                .createdDate(getCreatedDate())
                .salePrice(getSalePrice())
                .updatedDate(getUpdatedDate())
                .build();

        if (Objects.nonNull(getProduct())) {
            dto.setProduct(getProduct().toDTO());
        }
        if (Objects.nonNull(getWebsite())) {
            dto.setWebsite(getWebsite().toDTO());
        }
        if (Objects.nonNull(getCurrency())) {
            dto.setCurrency(getCurrency().toDTO());
        }

        return dto;
    }

}
