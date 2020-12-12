package com.example.decorate.repositorys.decoration;

import com.example.decorate.domain.Decoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecorationRepository extends JpaRepository<Decoration, Long> {
}
