package com.example.decorate.repositorys;

import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ProductCreationFormData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductKeyRepository extends JpaRepository<ProductKey, Long> {

    @Query("SELECT p FROM ProductKey p WHERE p.type =:value")
    List<ProductKey> findByProductType(ProductType value);

}
