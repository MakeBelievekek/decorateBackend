package com.example.decorate.repositorys;

import com.example.decorate.domain.KeyHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyHolderRepository extends JpaRepository<KeyHolder, Long> {
}