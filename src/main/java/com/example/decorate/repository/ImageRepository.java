package com.example.decorate.repository;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.ImageType;
import com.example.decorate.domain.ProductType;
import com.example.decorate.domain.dto.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByProdKeyAndImageType(Long id, ImageType type);
}
