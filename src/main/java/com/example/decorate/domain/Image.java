package com.example.decorate.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Image {

    @Id
    @Column(name = "image_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "prod_type")
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type")
    private ImageType imageType;

    @Column(name = "img_url")
    private String imgUrl;
}
