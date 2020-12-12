package com.example.decorate.repositorys.furniture;

import com.example.decorate.domain.FurnitureFabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends JpaRepository<FurnitureFabric, Long> {
}
