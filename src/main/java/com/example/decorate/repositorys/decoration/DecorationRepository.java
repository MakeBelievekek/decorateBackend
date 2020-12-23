package com.example.decorate.repositorys.decoration;

import com.example.decorate.domain.Decoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorationRepository extends JpaRepository<Decoration, Long> {

    @Query("SELECT decoration FROM Decoration decoration ")
    List<Decoration> getAllDecorations();

    @Query("SELECT d FROM Decoration d, AttributeItem da " +
            "WHERE d.productKey = da.productKey AND " +
            "da.attribute.description in :attributeDescriptions " +
            "GROUP BY d " +
            "HAVING COUNT(d) = :attributeCount ")
    List<Decoration> findDecorationByAttributeDesc(List<String> attributeDescriptions, Long attributeCount);

    @Query("SELECT d FROM Decoration d, AttributeItem da " +
            "WHERE d.productKey = da.productKey AND " +
            "da.attribute.id in :attributeIds " +
            "GROUP BY d " +
            "HAVING COUNT(d) = :numberOfAttributes ")
    List<Decoration> findAllByAttributeIds(List<Long> attributeIds, Long numberOfAttributes);
}
