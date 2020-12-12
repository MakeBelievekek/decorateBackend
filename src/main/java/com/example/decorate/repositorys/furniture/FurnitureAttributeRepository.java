package com.example.decorate.repositorys.furniture;

import com.example.decorate.domain.FurnitureAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureAttributeRepository extends JpaRepository<FurnitureAttribute, Long> {
}
