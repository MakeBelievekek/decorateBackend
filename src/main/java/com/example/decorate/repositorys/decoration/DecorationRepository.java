package com.example.decorate.repositorys.decoration;

import com.example.decorate.domain.Decoration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecorationRepository extends JpaRepository<Decoration, Long> {

    @Query("SELECT decoration FROM Decoration decoration ")
    List<Decoration> getAllDecorations();

    @Query("SELECT d FROM Decoration d, AttributeItem da " +
            "WHERE d.productKey = da.productKey AND " +
            "(da.attribute.id in :attributeIds OR " +
            ":numberOfAttributesInt = 0)" +
            "GROUP BY d " +
            "HAVING COUNT(d) = :numberOfAttributes ")
    List<Decoration> findAllByAttributeIds(List<Long> attributeIds, Long numberOfAttributes);

    @Query("SELECT d FROM Decoration d " +
            "ORDER BY d.productKey.created")
    List<Decoration>  fetchAllOrderedByProductCreationTime();
}
