package com.example.decorate.repositorys.curtain;

import com.example.decorate.domain.CurtainAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurtainAttributeRepository extends JpaRepository<CurtainAttribute, Long> {
}
