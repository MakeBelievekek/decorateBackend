package com.example.decorate.repositorys;

import com.example.decorate.domain.AttributeListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttributeListItemRepository extends JpaRepository<AttributeListItem, Long> {

    List<AttributeListItem> findAllByKey_Id(Long keyId);

    Optional<AttributeListItem> findById(Long attributeListItemId);

    void deleteById(Long attributeListItemId);
}
