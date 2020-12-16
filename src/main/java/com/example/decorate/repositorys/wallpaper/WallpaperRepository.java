package com.example.decorate.repositorys.wallpaper;

import com.example.decorate.domain.Wallpaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface WallpaperRepository extends JpaRepository<Wallpaper, Long> {

    @Query("SELECT c FROM Curtain c, AttributeItem ca " +
            "WHERE c.productKey = ca.productKey AND " +
            "ca.attribute.description in :descList " +
            "GROUP BY c " +
            "HAVING COUNT(c) = :attributeCount ")
    List<Wallpaper> findWallpaperByAttributeDesc(List<String> attributeDescriptions, Long searchParameterCount);
}
