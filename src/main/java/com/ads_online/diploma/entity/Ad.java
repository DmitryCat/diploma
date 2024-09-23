package com.ads_online.diploma.entity;

import com.ads_online.diploma.dto.CommentDTO;
import com.ads_online.diploma.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

// Сущность объявления
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "comments"})
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Image image;
    private Integer price;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDTO user;

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CommentDTO> comments;
}