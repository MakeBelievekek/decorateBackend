package com.example.decorate.repository;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByProdKeyAndImageType(Long id, ImageType type);
}
