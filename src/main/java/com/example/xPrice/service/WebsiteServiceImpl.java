package com.example.xPrice.service;

import com.example.xPrice.domain.Website;
import com.example.xPrice.dto.WebsiteDto;
import com.example.xPrice.repository.WebsiteRepository;
import com.example.xPrice.request.WebsiteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WebsiteServiceImpl implements WebsiteService {

    private final WebsiteRepository websiteRepository;

    @Override
    public WebsiteDto getWebsite(Integer websiteId) {
        Optional<Website> website = websiteRepository.findById(websiteId);

        if (website.isPresent()) {
            return website.get().toDTO();
        }

        return WebsiteDto.builder().build();
    }

    @Override
    public List<WebsiteDto> getWebsites() {
        List<Website> websiteList = (List<Website>) websiteRepository.findAll();

        if (!CollectionUtils.isEmpty(websiteList)) {
            return websiteList.stream().map(website -> website.toDTO()).collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    @Override
    public void createWebsite(WebsiteRequest request) {
        Website website = new Website();
        website.setName(request.getName());
        website.setUrl(request.getUrl());

        websiteRepository.save(website);
    }
}
