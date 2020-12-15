package com.example.decorate.repositorys.curtain;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.ProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurtainRepository extends JpaRepository<Curtain, Long>{
    @Query("SELECT curtain FROM Curtain curtain ")
    List<Curtain> getAllCurtains();

    Optional<Curtain> findById(Long curtainId);

    @Query("SELECT c FROM Curtain c, AttributeItem ca " +
            "WHERE c.productKey = ca.productKey AND " +
            "ca.attribute.description in :descList " +
            "GROUP BY c " +
            "HAVING COUNT(c) = :attributeCount ")
    List<Curtain> findCurtainByAttributeDesc(List<String> descList, Long attributeCount);
}
