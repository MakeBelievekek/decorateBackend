package com.example.decorate.repositorys.decoration;

import com.example.decorate.domain.DecorationAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecorationAttributeRepository extends JpaRepository<DecorationAttribute, Long> {
}
