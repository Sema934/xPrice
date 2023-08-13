package com.example.xPrice.domain;

import com.example.xPrice.dto.CurrencyDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="currency")
public class Currency {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="country_code")
    private String countryCode;

    public CurrencyDto toDTO() {
        return CurrencyDto.builder().id(getId()).code(getCode()).name(getName()).countryCode(countryCode).build();
    }
}
