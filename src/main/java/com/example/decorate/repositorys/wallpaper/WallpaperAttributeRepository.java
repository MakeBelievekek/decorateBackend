package com.example.decorate.repositorys.wallpaper;

import com.example.decorate.domain.WallpaperAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallpaperAttributeRepository extends JpaRepository<WallpaperAttribute, Long> {
}
