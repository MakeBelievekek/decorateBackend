package com.example.decorate.domain.dto;

import com.example.decorate.domain.Home;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HomeListItem {

    private Long id;

    private String imgUrl;

    private String type;

    public HomeListItem(Home home) {
        this.imgUrl = home.getImgUrl();
        this.type = home.getType();
    }
}
