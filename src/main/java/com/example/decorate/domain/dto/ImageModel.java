package com.example.decorate.domain.dto;

import com.example.decorate.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ImageModel {

    private Long id;

    private String imageType;

    private String imgUrl;

    public ImageModel(Image image) {
        this.imageType = image.getImageType().toString();
        this.imgUrl = image.getImgUrl();
    }

    public ImageModel(String imageType, String imgUrl) {
        this.imageType = imageType;
        this.imgUrl = imgUrl;
    }
}
