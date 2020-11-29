package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Atp {

    private int place_id;

    private String operator_id;

    private String name;

    private String vapt;

    private String olapt;

    private String japt;

    private String ssapt;

    private String sdapt;

    private String group;

    private String address;

    private int zip;

    private String city;

    private String street;

    private String findme;

    private double geolat;

    private double geolng;

    private AtpOpen open;
}
