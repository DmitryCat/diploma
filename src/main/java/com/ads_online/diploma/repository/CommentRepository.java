package com.ads_online.diploma.repository;

import com.ads_online.diploma.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from \"comments\" c where c.ad_pk = :id", nativeQuery = true)
    List<Comment> findCommentsByIdAd(@Param("id") Long id);
}
