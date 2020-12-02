package com.example.decorate.service;

import com.example.decorate.domain.Dummy;
import com.example.decorate.repository.DummyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class DummyService {
    private final DummyRepository dummyRepository;

    public Dummy getById() {
        return this.dummyRepository.findById(1L).get();
    }
}
