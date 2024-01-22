package com.ousmane.springbooturlshortener.customshortener.repository;

import com.ousmane.springbooturlshortener.bitlyshortener.entities.UrlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlData, Integer> {

    @Query("SELECT u FROM UrlData u WHERE u.shortUrl=:shortUrl")
    UrlData findByShortUrl(@Param("shortUrl") String shortUrl);
}
