package com.ousmane.springbooturlshortener.customshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public class UrlResponse {

    private String longUrl;
    private String shortenUrl;
    private LocalDateTime expirationDate;

    public UrlResponse() {
    }

    public UrlResponse(String longUrl, String shortenUrl, LocalDateTime expirationDate) {
        this.longUrl = longUrl;
        this.shortenUrl = shortenUrl;
        this.expirationDate = expirationDate;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortenUrl() {
        return shortenUrl;
    }

    public void setShortenUrl(String shortenUrl) {
        this.shortenUrl = shortenUrl;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "UrlResponse{" +
                "longUrl='" + longUrl + '\'' +
                ", shortenUrl='" + shortenUrl + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
