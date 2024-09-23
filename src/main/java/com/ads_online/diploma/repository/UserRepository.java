package com.ads_online.diploma.repository;

import com.ads_online.diploma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users u where u.email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);
}