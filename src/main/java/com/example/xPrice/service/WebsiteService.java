package com.example.xPrice.service;

import com.example.xPrice.dto.WebsiteDto;
import com.example.xPrice.request.WebsiteRequest;

import java.util.List;

public interface WebsiteService {

    WebsiteDto getWebsite(Integer websiteId);

    List<WebsiteDto> getWebsites();

    void createWebsite(WebsiteRequest request);

}
