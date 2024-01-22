package com.ousmane.springbooturlshortener.bitlyshortener;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BitlyController {

    private final BitlyService bitlyService;

    @PostMapping("/shorten")
    public ResponseEntity<String> createShortener(
            @RequestBody BitlyRequest bitlyRequest) throws IOException {
        return ResponseEntity.ok(bitlyService.shortenURL(bitlyRequest.getLongUrl()));
    }
}
