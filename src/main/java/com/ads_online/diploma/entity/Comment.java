package com.ads_online.diploma.entity;

import com.ads_online.diploma.dto.AdDTO;
import com.ads_online.diploma.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "ad"})
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    private LocalDateTime createdAt;
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDTO user;

    @ManyToOne
    @JoinColumn(name = "ad_pk", nullable = false)
    private AdDTO ad;
}