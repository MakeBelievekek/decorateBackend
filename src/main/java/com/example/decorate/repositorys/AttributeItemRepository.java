package com.example.decorate.repositorys;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.AttributeItem;
import com.example.decorate.domain.ProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeItemRepository extends JpaRepository<AttributeItem, Long> {

    @Query("SELECT ai FROM AttributeItem ai " +
            "WHERE ai.attribute = :attribute AND " +
            "ai.productKey = :productKey")
    Optional<AttributeItem> fetchByAttributeAndProductKey(Attribute attribute, ProductKey productKey);

    @Transactional
    @Modifying
    @Query("DELETE FROM AttributeItem ai " +
            "WHERE ai.productKey = :productKey AND " +
            "ai NOT IN :activeProductAttributeItems")
    void deleteProductNotUsedAttributeItems(List<AttributeItem> activeProductAttributeItems, ProductKey productKey);

    @Transactional
    @Modifying
    @Query("DELETE FROM AttributeItem ai " +
            "WHERE ai.productKey = :productKey")
    void deleteAllByProductKey(ProductKey productKey);

    @Query("SELECT ai FROM AttributeItem ai " +
            "WHERE ai.productKey = :productKey")
    List<AttributeItem> findProductAllAttributeItemsByProductKey(ProductKey productKey);

    @Query("SELECT ai FROM AttributeItem ai " +
            "WHERE ai.productKey.id = :id")
    List<AttributeItem> findProductAllAttributeItemsByProductId(Long id);

    @Query("SELECT attributeIt FROM AttributeItem  attributeIt WHERE attributeIt.attribute = :attr ")
    List<AttributeItem> getProductKeysForCurtainSubtype(Attribute attr);

}
