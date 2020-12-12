package com.example.decorate.repositorys.curtain;

import com.example.decorate.domain.CurtainAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurtainAttributeRepository extends JpaRepository<CurtainAttribute, Long> {

    @Query("SELECT curAttr FROM CurtainAttribute curAttr " +
            "WHERE curAttr.attribute.id = :attributeId AND " +
            "curAttr.curtain.id = :curtainId")
    Optional<CurtainAttribute> fetchByAttributeIdAndCurtainId(Long attributeId, Long curtainId);

    @Transactional
    @Modifying
    @Query("DELETE FROM CurtainAttribute curAttr " +
            "WHERE curAttr.curtain.id = :curtainId AND " +
            "curAttr.id NOT IN :activeCurtainAttributeIdList")
    void deleteCurtainNotUsedAttributes(List<Long> activeCurtainAttributeIdList, Long curtainId);

    @Query("SELECT curAttr FROM CurtainAttribute curAttr " +
            "WHERE curAttr.curtain.id =:curtainId")
    List<CurtainAttribute> fetchAllByCurtainId(Long curtainId);

    @Transactional
    @Modifying
    @Query("DELETE FROM CurtainAttribute curAttr " +
            "WHERE curAttr.curtain.id = :curtainId")
    void deleteAllByCurtainId(Long curtainId);
}
