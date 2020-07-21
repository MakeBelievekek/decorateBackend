package com.example.decorate.repository;

import com.example.decorate.domain.ProductCategoryListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryListItemRepository extends JpaRepository<ProductCategoryListItem, Long> {
}
