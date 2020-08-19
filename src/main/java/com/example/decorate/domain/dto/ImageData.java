package com.example.decorate.domain.dto;

import com.example.decorate.domain.Image;

public class ImageData {

    private String imageType;
    private String imgUrl;

    public String getImageType() {
        return imageType;
    }

    public ImageData() {
    }

    public ImageData(String imageType, String imgUrl) {
        this.imageType = imageType;
        this.imgUrl = imgUrl;
    }

    public ImageData(Image image) {
        this.imageType = image.getImageType().getType();
        this.imgUrl = image.getImgUrl();
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "ImageData{" +
                "imageType='" + imageType + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
