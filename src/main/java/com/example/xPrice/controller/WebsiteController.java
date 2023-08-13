package com.example.xPrice.controller;

import com.example.xPrice.dto.WebsiteDto;
import com.example.xPrice.request.WebsiteRequest;
import com.example.xPrice.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/website")
public class WebsiteController {

    @Autowired
    private final WebsiteService websiteService;

    @GetMapping(value = "/{websiteId}")
    @ResponseStatus(HttpStatus.OK)
    public WebsiteDto getWebsite(@NotNull @PathVariable("websiteId") Integer websiteId) {
        return websiteService.getWebsite(websiteId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<WebsiteDto> getWebsites() {
        return websiteService.getWebsites();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void createWebsite(@RequestBody WebsiteRequest request) {
        websiteService.createWebsite(request);
    }
}
