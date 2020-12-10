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
public class ImageData {

    private String imageType;

    private String imgUrl;

    public ImageData(Image image) {
        this.imageType = image.getImageType().getType();
        this.imgUrl = image.getImgUrl();
    }
}
