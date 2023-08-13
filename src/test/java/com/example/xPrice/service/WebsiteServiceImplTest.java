package com.example.xPrice.service;

import com.example.xPrice.domain.Website;
import com.example.xPrice.dto.WebsiteDto;
import com.example.xPrice.repository.WebsiteRepository;
import com.example.xPrice.request.WebsiteRequest;
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
public class WebsiteServiceImplTest {

    @InjectMocks
    private WebsiteServiceImpl websiteService;

    @Mock
    WebsiteRepository websiteRepository;

    @Test
    public void getWebsite() {
        Website website = new Website();

        when(websiteRepository.findById(1)).thenReturn(Optional.of(website));

        WebsiteDto websiteDto = websiteService.getWebsite(1);

        verify(websiteRepository).findById(1);
        assertThat(websiteDto).isNotNull();
    }

    @Test
    public void getWebsites() {
        Website website = new Website();
        List<Website> websiteList = List.of(website);

        when(websiteRepository.findAll()).thenReturn(websiteList);

        List<WebsiteDto> websiteDtos = websiteService.getWebsites();

        verify(websiteRepository).findAll();
        assertThat(websiteDtos).isNotNull();
    }

    @Test
    public void createWebsite() {
        WebsiteRequest websiteRequest = WebsiteRequest.builder()
                .name("Amazon")
                .url("https://www.amazon.com.tr/")
                .build();

        websiteService.createWebsite(websiteRequest);

        ArgumentCaptor<Website> websiteArgumentCaptor = ArgumentCaptor.forClass(Website.class);
        verify(websiteRepository).save(websiteArgumentCaptor.capture());
    }
}