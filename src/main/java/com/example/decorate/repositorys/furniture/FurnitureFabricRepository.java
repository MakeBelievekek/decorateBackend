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

    @Query("SELECT ff FROM FurnitureFabric ff ")
    List<FurnitureFabric> getAllFurniture();

    @Query("SELECT ff FROM FurnitureFabric ff, AttributeItem fa " +
            "WHERE ff.productKey = fa.productKey AND " +
            "(fa.attribute.id in :attributeIds OR " +
            ":numberOfAttributesInt = 0)" +
            "GROUP BY ff " +
            "HAVING COUNT(ff) = :numberOfAttributes")
    List<FurnitureFabric> findAllByAttributeIds(List<Long> attributeIds, Long numberOfAttributes);

    @Query("SELECT ff FROM FurnitureFabric ff " +
            "ORDER BY ff.productKey.created")
    List<FurnitureFabric>  fetchAllOrderedByProductCreationTime();
}
