package com.ads_online.diploma.entity;

import lombok.*;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.ImageType;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = "ImageType", typeClass = ImageType.class)

@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long fileSize;
    private String filePath;
    private String mediaType;
}