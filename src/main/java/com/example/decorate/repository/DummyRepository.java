package com.example.decorate.repository;

import com.example.decorate.domain.Dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DummyRepository extends JpaRepository<Dummy, Long> {

    Optional<Dummy> findById(Long id);
}
