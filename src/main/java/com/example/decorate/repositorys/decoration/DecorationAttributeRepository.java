package com.example.decorate.repositorys.decoration;

import com.example.decorate.domain.DecorationAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DecorationAttributeRepository extends JpaRepository<DecorationAttribute, Long> {
    @Query("SELECT d FROM DecorationAttribute d " +
            "WHERE d.attribute.id = :attributeId AND " +
            "d.decoration.id = :decorationId")
    Optional<DecorationAttribute> fetchByAttributeIdAndDecorationId(Long attributeId, Long decorationId);

    @Transactional
    @Modifying
    @Query("DELETE FROM DecorationAttribute d " +
            "WHERE d.decoration.id = :decorationId AND " +
            "d.id NOT IN :activeDecorationAttributeIdList")
    void deleteDecorationNotUsedAttributes(List<Long> activeDecorationAttributeIdList, Long decorationId);

    @Query("SELECT d FROM DecorationAttribute d " +
            "WHERE d.decoration.id =:decorationId")
    List<DecorationAttribute> fetchAllByCurtainId(Long decorationId);

    @Transactional
    @Modifying
    @Query("DELETE FROM DecorationAttribute d " +
            "WHERE d.decoration.id = :decorationId")
    void deleteAllByDecorationId(Long decorationId);
}
