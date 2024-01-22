package com.ousmane.springbooturlshortener.bitlyshortener.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UrlData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String longUrl;
    private String shortUrl;

    @CreationTimestamp
    private LocalDateTime creationDate;

    private LocalDateTime expirationDate;
}
