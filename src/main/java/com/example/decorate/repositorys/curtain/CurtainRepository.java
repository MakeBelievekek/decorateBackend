package com.example.decorate.repositorys.curtain;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.Curtain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurtainRepository extends JpaRepository<Curtain, Long>{
    @Query("SELECT curtain FROM Curtain curtain ")
    List<Curtain> getAllCurtains();

    Optional<Curtain> findById(Long curtainId);

    @Query("SELECT c FROM Curtain c, AttributeListItem ca " +
            "WHERE c.key.id = ca.key.id AND " +
            "ca.attribute.description in ('kek')")
    List<Curtain> findwhateva();

    /*asdsasdasdasd
    *
    *
    * */
}
