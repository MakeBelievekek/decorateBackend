package com.example.decorate.repositorys.curtain;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.ProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurtainRepository extends JpaRepository<Curtain, Long> {
    @Query("SELECT c FROM Curtain c ")
    List<Curtain> getAllCurtains();

    Optional<Curtain> findById(Long curtainId);

    @Query("SELECT c FROM Curtain c, AttributeItem ca " +
            "WHERE c.productKey = ca.productKey AND " +
            "ca.attribute.id in :attributeIds " +
            "GROUP BY c " +
            "HAVING COUNT(c) = :numberOfAttributes ")
    List<Curtain> findByAttributeIds(List<Long> attributeIds, Long numberOfAttributes);

    @Query("SELECT c FROM Curtain c " +
            "ORDER BY c.productKey.created")
    List<Curtain> fetchAllOrderedByProductCreationTime();
}
