package com.ousmane.springbooturlshortener.bitlyshortener;

import com.opsmatters.bitly.Bitly;
import com.opsmatters.bitly.api.model.v4.CreateBitlinkResponse;
import com.ousmane.springbooturlshortener.bitlyshortener.entities.UrlData;
import com.ousmane.springbooturlshortener.bitlyshortener.repository.BitlyRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class BitlyService {

    @Value("${bitly.token}")
    private String bitlyToken;

    private BitlyRepository bitlyRepo;
    private Bitly client;

    @PostConstruct
    private void setUp() {
        client = new Bitly(bitlyToken);
    }

    public String shortenURL(String longUrl) throws IOException {

        String link = "error";
        try {
            CreateBitlinkResponse response = client.bitlinks().shorten(longUrl).get();
            link = response.getLink();
            log.info("Complete shortening");
            UrlData data = UrlData.builder()
                    .longUrl(longUrl)
                    .shortUrl(link)
                    .build();
            bitlyRepo.save(data);
        } catch (IOException e) {
            log.error("Error: {}", e.getMessage());
        }
        return link;
    }
}
