package com.example.decorate.repository;

import com.example.decorate.domain.Curtain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurtainRepository extends JpaRepository<Curtain, Long> {
}
