package com.example.decorate.repositorys.wallpaper;

import com.example.decorate.domain.Wallpaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WallpaperRepository extends JpaRepository<Wallpaper, Long> {

    @Query("SELECT c FROM Wallpaper c, AttributeItem ca " +
            "WHERE c.productKey = ca.productKey AND " +
            "ca.attribute.description in :attributeDescriptions " +
            "GROUP BY c " +
            "HAVING COUNT(c) = :attributeCount ")
    List<Wallpaper> findWallpaperByAttributeDesc(List<String> attributeDescriptions, Long attributeCount);

    @Query("SELECT wallpaper FROM Wallpaper wallpaper ")
    List<Wallpaper> getAllWallpaper();
}
