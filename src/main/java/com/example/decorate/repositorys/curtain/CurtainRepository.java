package com.example.decorate.repositorys.curtain;

import com.example.decorate.domain.Curtain;
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
/*
    @Query("SELECT curtain FROM Curtain curtain, AttributeListItem  attributeListItem " +
            "WHERE curtain.key.id = attributeListItem.key.id " +
            "AND attributeListItem.attribute.description =:attribute")
    List<Curtain> getCurtainsByAttributes(@Param("attribute") String attribute);

    @Query("SELECT  curtain FROM Curtain curtain  " +
            "WHERE curtain.id in " +
            "(SELECT attr.key.id FROM AttributeListItem attr WHERE attr.attribute.description=:attribute )  ")
    List<Curtain> getCurtainsByAttribute(@Param("attribute") String attribute);*/
}
