package com.example.xPrice.domain;

import com.example.xPrice.dto.WebsiteDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="website")
public class Website {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="url")
    private String url;

    public WebsiteDto toDTO() {
        return WebsiteDto.builder().id(getId()).name(getName()).url(getUrl()).build();
    }
}
