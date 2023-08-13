package com.example.xPrice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WebsiteRequest {

    private String name;
    private String url;

}
