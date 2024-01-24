package com.ousmane.springbooturlshortener.customshortener.controller;

import com.ousmane.springbooturlshortener.bitlyshortener.entities.UrlData;
import com.ousmane.springbooturlshortener.customshortener.dto.UrlDto;
import com.ousmane.springbooturlshortener.customshortener.dto.UrlResponse;
import com.ousmane.springbooturlshortener.customshortener.exceptions.UrlErrorResponseDto;
import com.ousmane.springbooturlshortener.customshortener.service.UrlServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UrlShortenerController {

    private final UrlServiceImpl urlService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateShortUrl(
            @RequestBody UrlDto urlDto) {
        UrlData generatedUrl = urlService.generateUrl(urlDto);
        if (generatedUrl != null) {
            UrlResponse response = UrlResponse.builder()
                    .shortenUrl(generatedUrl.getShortUrl())
                    .longUrl(generatedUrl.getLongUrl())
                    .expirationDate(generatedUrl.getExpirationDate())
                    .build();
            return ResponseEntity.ok(response);
        }
        UrlErrorResponseDto error = UrlErrorResponseDto.builder()
                .error("There was an error creating the link, please trye again")
                .status("404")
                .build();
        return ResponseEntity.ok(error);
    }

    @GetMapping("/{shortLink}")
    public ResponseEntity<?> redirectToOriginalLink(
            @PathVariable String shortLink, HttpServletResponse response) throws IOException {
        if (StringUtils.isEmpty(shortLink)) {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setError("Invalid Url");
            urlErrorResponseDto.setStatus("400");
            return ResponseEntity.ok(urlErrorResponseDto);
        }
        UrlData urlToRet = urlService.getEncodedUrl(shortLink);

        if (urlToRet == null) {
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setError("Url does not exist or it might have expired!");
            urlErrorResponseDto.setStatus("400");
            return ResponseEntity.ok(urlErrorResponseDto);
        }

        if (urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
            urlService.deleteShortUrl(urlToRet);
            UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
            urlErrorResponseDto.setError("Url Expired. Please try generating a fresh one.");
            urlErrorResponseDto.setStatus("200");
            return ResponseEntity.ok(urlErrorResponseDto);
        }

        response.sendRedirect(urlToRet.getLongUrl());
        return null;
    }
}
