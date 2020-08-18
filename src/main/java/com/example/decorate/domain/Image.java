package com.example.decorate.domain;

import com.example.decorate.domain.dto.ImageData;

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

    public Image() {
    }

    public Image(ImageData imageData) {
        for (ImageType imageType : ImageType.values()) {
            if (imageType.toString().equals(imageData.getImageType())) {
                this.imageType = imageType;
            }
        }
        for (ProductType productType : ProductType.values()) {
            if (productType.toString().equals(imageData.getProductType())) {
                this.productType = productType;
            }
        }
        this.imgUrl = imageData.getImgUrl();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdKey() {
        return prodKey;
    }

    public void setProdKey(Long prodKey) {
        this.prodKey = prodKey;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
