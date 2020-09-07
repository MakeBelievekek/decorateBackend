package com.example.decorate.service;

import com.example.decorate.domain.Home;
import com.example.decorate.domain.dto.HomeListItem;
import com.example.decorate.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HomeService {

    private HomeRepository homeRepository;

    @Autowired
    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<HomeListItem> getAllHomeImg() {
        List<HomeListItem> images = new ArrayList<>();
        for (Home home : homeRepository.findAll()) {
            images.add(new HomeListItem(home));
        }
        return images;
    }
}
