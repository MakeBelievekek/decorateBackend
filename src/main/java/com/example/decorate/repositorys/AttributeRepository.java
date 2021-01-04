package com.example.decorate.repositorys;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.AttributeItem;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Optional<Attribute> findByDescription(String description);

    @Query("SELECT attr FROM Attribute attr WHERE attr.type = 'TYPE' AND attr.description <> '' AND attr.description <> 'Díszpárna' ")
    List<Attribute> getAllCurtainSubType();

    @Query("SELECT attr FROM Attribute attr WHERE attr.id in :attributeList ")
    List<Attribute> getAllAttributeDescription(List<Long> attributeList);

    @Query("SELECT DISTINCT attr FROM Attribute  attr WHERE attr.type = 'TYPE' AND " +
            "attr.description <> '' AND attr.description <>'Díszpárna'")
    List<Attribute> findAllSubTypes();

    @Query("SELECT DISTINCT ai.attribute  FROM AttributeItem ai WHERE " +
            "ai.productKey.type = :productType AND " +
            "ai.attribute.type <> 'TYPE'" +
            "ORDER BY ai.attribute.description")
    List<Attribute> findAllAttributeByProductType(ProductType productType);

    @Query("SELECT DISTINCT ai.attribute FROM AttributeItem ai " +
            "WHERE ai.productKey IN " +
            "(SELECT attritem.productKey FROM AttributeItem attritem WHERE attritem.attribute = :attribute) AND " +
            "ai.attribute.type <> 'TYPE' " +
            "ORDER BY ai.attribute.description")
    List<Attribute> findAllAttributesForSubTypesByAttribute(Attribute attribute);

    @Query("SELECT DISTINCT ai.attribute FROM AttributeItem ai " +
            "WHERE ai.productKey IN " +
            "(SELECT ai.productKey FROM AttributeItem ai WHERE ai.attribute.id = :attributeId) AND " +
            "ai.attribute.type <> 'TYPE' " +
            "ORDER BY ai.attribute.description")
    List<Attribute> findAllAttributesForSubTypesByAttributeId(Long attributeId);
}
