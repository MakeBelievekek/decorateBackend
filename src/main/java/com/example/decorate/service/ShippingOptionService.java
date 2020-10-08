package com.example.decorate.service;

import com.example.decorate.domain.ShippingOption;
import com.example.decorate.domain.dto.ShippingOptionListItem;
import com.example.decorate.repository.ShippingOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

public class ShippingOptionService {

    private ShippingOptionRepository shippingOptionRepository;


    @Autowired
    public ShippingOptionService(ShippingOptionRepository shippingOptionRepository) {
        this.shippingOptionRepository = shippingOptionRepository;
    }

    public List<ShippingOptionListItem> getAllOption() {
        List<ShippingOptionListItem> options = new ArrayList<>();
        for (ShippingOption shippingOption : this.shippingOptionRepository.findAll()) {
            options.add(new ShippingOptionListItem(shippingOption));
        }
        return options;
    }
}
