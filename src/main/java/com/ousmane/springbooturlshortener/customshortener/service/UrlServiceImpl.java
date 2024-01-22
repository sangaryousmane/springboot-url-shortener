package com.ousmane.springbooturlshortener.customshortener.service;

import com.google.common.hash.Hashing;
import com.ousmane.springbooturlshortener.bitlyshortener.entities.UrlData;
import com.ousmane.springbooturlshortener.customshortener.dto.UrlDto;
import com.ousmane.springbooturlshortener.customshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlShortenerService {

    private final UrlRepository urlRepo;

    @Override
    public UrlData saveUrl(UrlData link) {
        return null;
    }

    @Override
    public UrlData generateUrl(UrlDto link) {
        if (StringUtils.isNotEmpty(link.getUrl())) {
            String encodedUrl = encodeUrl(link.getUrl());
            UrlData urlToPersist = new UrlData();
            urlToPersist.setCreationDate(LocalDateTime.now());
            urlToPersist.setExpirationDate(getExpirationDate(link.getExpirationDate(),
                    urlToPersist.getCreationDate()));
            urlToPersist.setLongUrl(link.getUrl());
            urlToPersist.setShortUrl(encodedUrl);

            return persistUrl(urlToPersist);
        }
        return null;
    }

    private UrlData persistUrl(UrlData urlToPersist) {
        return urlRepo.save(urlToPersist);
    }

    private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {
        if (StringUtils.isBlank(expirationDate)){
            return creationDate.plusSeconds(60);
        }
        return LocalDateTime.parse(expirationDate);
    }

    private String encodeUrl(String url){
        String encodedUrl = "";
        LocalDateTime currentTime = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(currentTime.toString()), StandardCharsets.UTF_8)
                .toString();
        return encodedUrl;
    }
    @Override
    public UrlData getEncodedUrl(String url) {
        return urlRepo.findByShortUrl(url);
    }

    @Override
    public void deleteShortUrl(UrlData shortLink) {
        urlRepo.delete(shortLink);
    }
}
