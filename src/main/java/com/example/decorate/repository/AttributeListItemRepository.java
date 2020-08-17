package com.example.decorate.repository;

import com.example.decorate.domain.AttributeListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeListItemRepository extends JpaRepository<AttributeListItem, Long> {
}
