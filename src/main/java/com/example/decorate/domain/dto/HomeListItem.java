package com.example.decorate.domain.dto;

import com.example.decorate.domain.Home;

public class HomeListItem {

    private Long id;

    private String imgUrl;

    private String type;


    public HomeListItem() {
    }

    public HomeListItem(Home home) {
        this.imgUrl = home.getImgUrl();
        this.type = home.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
