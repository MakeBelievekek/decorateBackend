package com.example.decorate.repository;

import com.example.decorate.domain.Wallpaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WallpaperRepository extends JpaRepository<Wallpaper, Long> {
}
