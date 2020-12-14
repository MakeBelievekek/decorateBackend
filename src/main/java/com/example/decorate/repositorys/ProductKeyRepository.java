package com.example.decorate.repositorys;

import com.example.decorate.domain.ProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductKeyRepository extends JpaRepository<ProductKey, Long> {
}
