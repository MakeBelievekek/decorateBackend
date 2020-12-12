package com.example.decorate.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CalculatorService {

    public int getOneToTwoGore() {
        int curtainPrice = 2680;
        int corniceLength = 300;
        int corniceLengthTotal = (corniceLength * 2 + 30) / 10;
        int sewing = corniceLengthTotal * 250;

        return corniceLengthTotal * curtainPrice + sewing;
    }
}
