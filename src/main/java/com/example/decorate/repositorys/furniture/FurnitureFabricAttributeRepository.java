package com.example.decorate.repositorys.furniture;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.CurtainAttribute;
import com.example.decorate.domain.FurnitureFabric;
import com.example.decorate.domain.FurnitureFabricAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FurnitureFabricAttributeRepository extends JpaRepository<FurnitureFabricAttribute, Long> {

    @Query("SELECT ffa FROM FurnitureFabricAttribute ffa " +
            "WHERE ffa.attribute.id = :attributeId AND " +
            "ffa.furnitureFabric.id = :furnitureFabricId")
    Optional<FurnitureFabricAttribute> fetchByAttributeIdAndCurtainId(Long attributeId, Long furnitureFabricId);

    @Transactional
    @Modifying
    @Query("DELETE FROM FurnitureFabricAttribute ffa " +
            "WHERE ffa.furnitureFabric.id = :furnitureFabricId AND " +
            "ffa.id NOT IN :activeFurnitureFabricAttributeIdList")
    void deleteFurnitureFabricNotUsedAttributes(List<Long> activeFurnitureFabricAttributeIdList, Long furnitureFabricId);

    @Query("SELECT ffa FROM FurnitureFabricAttribute ffa " +
            "WHERE ffa.furnitureFabric.id =:furnitureFabricId")
    List<FurnitureFabricAttribute> fetchAllByFurnitureFabricId(Long furnitureFabricId);

    @Transactional
    @Modifying
    @Query("DELETE FROM FurnitureFabricAttribute ffa " +
            "WHERE ffa.furnitureFabric.id = :furnitureFabricId")
    void deleteAllByFurnitureFabricId(Long furnitureFabricId);
}

