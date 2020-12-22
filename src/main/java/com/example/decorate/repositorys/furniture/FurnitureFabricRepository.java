package com.example.decorate.repositorys.furniture;

import com.example.decorate.domain.FurnitureFabric;
import com.example.decorate.domain.Wallpaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface FurnitureFabricRepository extends JpaRepository<FurnitureFabric, Long> {
    @Query("SELECT ff FROM FurnitureFabric ff ")
    List<FurnitureFabric> getAllFurnitureFabrics();

   @Query("SELECT c FROM FurnitureFabric c, AttributeItem ca " +
            "WHERE c.productKey = ca.productKey AND " +
            "ca.attribute.description in :attributeDescriptions " +
            "GROUP BY c " +
            "HAVING COUNT(c) = :attributeCount ")
    List<FurnitureFabric> findFurnitureFabricByAttributeDesc(List<String> attributeDescriptions, Long attributeCount);

    @Query("SELECT furniture FROM FurnitureFabric furniture ")
    List<FurnitureFabric> getAllFurniture();
}
