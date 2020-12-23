package com.example.decorate.repositorys.wallpaper;

import com.example.decorate.domain.Wallpaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WallpaperRepository extends JpaRepository<Wallpaper, Long> {

    @Query("SELECT w FROM Wallpaper w, AttributeItem wa " +
            "WHERE w.productKey = wa.productKey AND " +
            "wa.attribute.description in :attributeDescriptions " +
            "GROUP BY w " +
            "HAVING COUNT(w) = :attributeCount ")
    List<Wallpaper> findWallpaperByAttributeDesc(List<String> attributeDescriptions, Long attributeCount);

    @Query("SELECT w FROM Wallpaper w ")
    List<Wallpaper> getAllWallpaper();

    @Query("SELECT w FROM Wallpaper w, AttributeItem wa " +
            "WHERE w.productKey = wa.productKey AND " +
            "wa.attribute.id in :attributeIds " +
            "GROUP BY w " +
            "HAVING COUNT(w) = :numberOfAttributes ")
    List<Wallpaper> findAllByAttributeIds(List<Long> attributeIds, Long numberOfAttributes);
}
