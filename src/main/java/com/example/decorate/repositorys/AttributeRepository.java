package com.example.decorate.repositorys;

import com.example.decorate.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Optional<Attribute> findByDescription(String description);

}
