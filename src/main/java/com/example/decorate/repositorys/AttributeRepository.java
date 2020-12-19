package com.example.decorate.repositorys;

import com.example.decorate.domain.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Optional<Attribute> findByDescription(String description);

    @Query("SELECT attr FROM Attribute attr WHERE attr.type = 'TYPE' AND attr.description <> '' AND attr.description <> 'Díszpárna' ")
    List<Attribute> getAllCurtainSubType();

    @Query("SELECT attr FROM Attribute attr WHERE attr.id in :attributeList ")
    List<Attribute> getAllAttributeDescription(List<Long> attributeList);
}
