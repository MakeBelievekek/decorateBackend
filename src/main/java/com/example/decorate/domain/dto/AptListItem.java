package com.example.decorate.domain.dto;

import java.util.List;

public class AptListItem {

    String city;

    List<String> names;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
