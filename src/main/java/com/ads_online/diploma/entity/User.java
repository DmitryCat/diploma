package com.ads_online.diploma.entity;

import com.ads_online.diploma.dto.AdDTO;
import com.ads_online.diploma.dto.CommentDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"ads", "comments"})
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "image_id")
    private Image image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AdDTO> ads;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommentDTO> comments;
}