package com.ousmane.springbooturlshortener.customshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlDto {
    private String url;
    private String expirationDate; // Optional


    public UrlDto(String url, String expirationDate) {
        this.url = url;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlDto{" +
                "url='" + url + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
