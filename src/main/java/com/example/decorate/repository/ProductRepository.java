package com.example.decorate.repository;

import com.example.decorate.domain.Product;
import com.example.decorate.domain.dto.ProductListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT new com.example.decorate.domain.dto.ProductListItem(p.id,p.productDesc,p.price,p.imgUrl) " +
            "FROM Product as p where p.id =:productId ")
    ProductListItem getProdListItem(@Param("productId") Long id);
}
