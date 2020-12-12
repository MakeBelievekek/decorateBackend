package com.example.decorate.domain;

import com.example.decorate.domain.dto.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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

    @Column(name = "time_stamp")
    private Instant timeStamp = Instant.now();

    public Image(ImageModel imageModel) {
        this.imageType = ImageType.valueOf(imageModel.getImageType());
        this.imgUrl = imageModel.getImgUrl();
        this.timeStamp = Instant.now();
    }

    public Image(ImageModel imageModel, Long productId) {
        this.prodKey = productId;
        this.imageType = ImageType.valueOf(imageModel.getImageType());
        this.imgUrl = imageModel.getImgUrl();
        this.timeStamp = Instant.now();
    }
}
