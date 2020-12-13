package com.example.decorate.repositorys.decoration;

import com.example.decorate.domain.Decoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorationRepository extends JpaRepository<Decoration, Long> {

    @Query("SELECT curtain FROM Curtain curtain ")
    List<Decoration> getAllDecorations();
}
