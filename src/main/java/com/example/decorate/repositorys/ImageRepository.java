package com.example.decorate.repositorys;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByProdKeyAndImageType(Long id, ImageType type);

    @Query("SELECT img FROM Image img " +
            "WHERE img.imageType ='PRIMARY_IMG' " +
            "AND img.prodKey=:productId")
    Optional<Image> findProductPrimaryImage(Long productId);

    List<Image> findAllByProdKey(Long productId);

    Optional<Image> findById(Long imgId);

    void deleteById(Long imgId);
    @Query("DELETE FROM Image image " +
            "WHERE image.prodKey =:productId AND " +
            "image.id NOT IN :activeImagesIdList")
    void deleteProductInActiveImages(List<Long> activeImagesIdList, Long productId);
}
