package com.example.decorate.repositorys.furniture;

import com.example.decorate.domain.FurnitureFabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FurnitureFabricRepository extends JpaRepository<FurnitureFabric, Long> {
    @Query("SELECT ff FROM FurnitureFabric ff ")
    List<FurnitureFabric> getAllFurnitureFabrics();
}
