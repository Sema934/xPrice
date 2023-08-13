package com.example.xPrice.controller;

import com.example.xPrice.dto.PriceDetailDto;
import com.example.xPrice.request.CurrencyRequest;
import com.example.xPrice.request.PriceDetailRequest;
import com.example.xPrice.service.PriceDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
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

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PriceDetailController.class)
public class PriceDetailControllerTest extends Assertions {

    @MockBean
    private PriceDetailService priceDetailService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getPriceDetails() throws Exception {
        PriceDetailDto priceDetailDto = PriceDetailDto.builder().build();
        List<PriceDetailDto> priceDetailDtoList = List.of(priceDetailDto);

        Mockito.when(priceDetailService.getPriceDetails(1, 1)).thenReturn(priceDetailDtoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/pricedetail/1/1/all"));

        verify(priceDetailService).getPriceDetails(1, 1);
    }

    @Test
    public void getPriceDetailsWithLowestPrice() throws Exception {
        PriceDetailDto priceDetailDto = PriceDetailDto.builder().build();

        Mockito.when(priceDetailService.getPriceDetailsWithLowestPrice(1, 1))
                .thenReturn(priceDetailDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/pricedetail/1/1"));

        verify(priceDetailService).getPriceDetailsWithLowestPrice(1, 1);
    }

    @Test
    public void createPriceDetail() throws Exception {
        PriceDetailRequest priceDetailRequest = PriceDetailRequest.builder()
                .productId(1)
                .websiteId(1)
                .salePrice(90000)
                .currencyId(1).build();

        ArgumentCaptor<PriceDetailRequest> priceDetailRequestArgumentCaptor =
                ArgumentCaptor.forClass(PriceDetailRequest.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/pricedetail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(priceDetailRequest)));

        verify(priceDetailService).createPriceDetail(priceDetailRequestArgumentCaptor.capture());
        assertThat(priceDetailRequestArgumentCaptor.getValue()).usingRecursiveComparison()
                .ignoringFields("fieldToIgnore")
                .isEqualTo(priceDetailRequest);
    }

    @Test
    public void updatePriceDetailPrice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/pricedetail/1?salePrice=50000"));

        verify(priceDetailService).updatePriceDetailPrice(1, 50000 );
    }

}