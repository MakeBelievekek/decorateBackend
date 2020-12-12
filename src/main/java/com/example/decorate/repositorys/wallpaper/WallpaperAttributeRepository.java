package com.example.decorate.repositorys.wallpaper;

import com.example.decorate.domain.CurtainAttribute;
import com.example.decorate.domain.WallpaperAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WallpaperAttributeRepository extends JpaRepository<WallpaperAttribute, Long> {

    @Query("SELECT wallAttr FROM WallpaperAttribute wallAttr " +
            "WHERE wallAttr.wallpaper.id = :wallpaperId")
    List<WallpaperAttribute> fetchAllByWallpaperId(Long wallpaperId);

    @Query("SELECT wallAttr FROM WallpaperAttribute wallAttr " +
            "WHERE wallAttr.attribute.id = :attributeId AND " +
            "wallAttr.wallpaper.id = :wallpaperId")
    Optional<WallpaperAttribute> fetchByAttributeIdAndWallpaperId(Long attributeId, Long wallpaperId);

    @Transactional
    @Modifying
    @Query("DELETE FROM WallpaperAttribute wallAttr " +
            "WHERE wallAttr.wallpaper.id = :wallpaperId AND " +
            "wallAttr.id NOT IN :activeWallpaperAttributeIdList")
    void deleteWallpaperNotUsedAttributes(List<Long> activeWallpaperAttributeIdList, Long wallpaperId);

    @Transactional
    @Modifying
    @Query("DELETE FROM WallpaperAttribute wallAttr " +
            "WHERE wallAttr.wallpaper.id = :wallpaperId")
    void deleteAllByWallpaperId(Long wallpaperId);
}

