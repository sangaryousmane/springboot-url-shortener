package com.ousmane.springbooturlshortener.customshortener.service;

import com.ousmane.springbooturlshortener.bitlyshortener.entities.UrlData;
import com.ousmane.springbooturlshortener.customshortener.dto.UrlDto;
import org.springframework.stereotype.Service;


public interface UrlShortenerService {

    UrlData saveUrl(UrlData link);
    UrlData generateUrl(UrlDto link);


    UrlData getEncodedUrl(String url);
    void deleteShortUrl(UrlData  shortLink);
}
