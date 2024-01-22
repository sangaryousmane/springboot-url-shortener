package com.ousmane.springbooturlshortener.bitlyshortener.repository;

import com.ousmane.springbooturlshortener.bitlyshortener.entities.UrlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitlyRepository extends JpaRepository<UrlData, Integer> {
}
