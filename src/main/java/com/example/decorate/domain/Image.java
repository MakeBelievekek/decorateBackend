package com.example.decorate.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductKey productKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "prod_type")
    private ProductType productType;

    @Enumerated(EnumType.STRING)
    @Column(name = "image_type")
    private ImageType imageType;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

   /* public Image(ImageModel imageModel) {
        this.imageType = ImageType.valueOf(imageModel.getImageType());
        this.imgUrl = imageModel.getImgUrl();
        this.created = Instant.now();
    }

    public Image(ImageModel imageModel, Long productId) {
        this.productKey = productId;
        this.imageType = ImageType.valueOf(imageModel.getImageType());
        this.imgUrl = imageModel.getImgUrl();
        this.created = Instant.now();
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id.equals(image.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
