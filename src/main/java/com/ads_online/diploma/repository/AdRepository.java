package com.ads_online.diploma.repository;

import com.ads_online.diploma.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
}