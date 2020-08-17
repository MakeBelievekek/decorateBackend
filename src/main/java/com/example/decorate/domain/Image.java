package com.example.decorate.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    @Column(name = "prod_id")
    private Long prodKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "prod_type")
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type")
    private ImageType imageType;

    @Column(name = "img_url")
    private String imgUrl;
}
