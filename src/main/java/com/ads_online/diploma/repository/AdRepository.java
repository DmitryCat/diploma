package com.ads_online.diploma.repository;

import com.ads_online.diploma.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query(value = "select * from ads a where a.pk =:id", nativeQuery = true)
    Ad findAdById(@Param("id") Long id);
}
