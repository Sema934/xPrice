package com.example.xPrice.controller;

import com.example.xPrice.dto.WebsiteDto;
import com.example.xPrice.request.WebsiteRequest;
import com.example.xPrice.service.WebsiteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = WebsiteController.class)
public class WebsiteControllerTest {

    @MockBean
    private WebsiteService websiteService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getWebsite() throws Exception {
        WebsiteDto websiteDto = WebsiteDto.builder().build();

        Mockito.when(websiteService.getWebsite(1)).thenReturn(websiteDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/website/{websiteId}", "1"));

        verify(websiteService).getWebsite(1);
    }

    @Test
    public void getWebsites() throws Exception {
        WebsiteDto websiteDto = WebsiteDto.builder().build();
        List<WebsiteDto> websiteDtoList = List.of(websiteDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/website"));

        verify(websiteService).getWebsites();
    }

    @Test
    public void createWebsite() throws Exception {
        WebsiteRequest websiteRequest = WebsiteRequest.builder()
                .name("Amazon")
                .url("https://www.amazon.com.tr/")
                .build();

        ArgumentCaptor<WebsiteRequest> websiteRequestArgumentCaptor = ArgumentCaptor.forClass(WebsiteRequest.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/website")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(websiteRequest)));

        verify(websiteService).createWebsite(websiteRequestArgumentCaptor.capture());
        assertThat(websiteRequestArgumentCaptor.getValue()).usingRecursiveComparison()
                .ignoringFields("fieldToIgnore")
                .isEqualTo(websiteRequest);
    }
}