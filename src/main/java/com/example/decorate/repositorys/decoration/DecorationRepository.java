package com.example.decorate.repositorys.decoration;

import com.example.decorate.domain.Decoration;
import com.example.decorate.domain.FurnitureFabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DecorationRepository extends JpaRepository<Decoration, Long> {

    @Query("SELECT curtain FROM Curtain curtain ")
    List<Decoration> getAllDecorations();

    @Query("SELECT c FROM Curtain c, AttributeItem ca " +
            "WHERE c.productKey = ca.productKey AND " +
            "ca.attribute.description in :attributeDescriptions " +
            "GROUP BY c " +
            "HAVING COUNT(c) = :attributeCount ")
    List<Decoration> findDecorationByAttributeDesc(List<String> attributeDescriptions, Long attributeCount);
}
