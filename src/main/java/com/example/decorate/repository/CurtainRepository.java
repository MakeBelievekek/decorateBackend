package com.example.decorate.repository;

import com.example.decorate.domain.Curtain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurtainRepository extends JpaRepository<Curtain, Long> {
    @Query("SELECT curtain FROM Curtain curtain ")
    List<Curtain> getAllCurtains();

    Optional<Curtain> findById(Long curtainId);

}
