package com.example.decorate.repositorys;

import com.example.decorate.domain.Image;
import com.example.decorate.domain.ProductKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT img FROM Image img " +
            "WHERE img.imageType ='PRIMARY_IMG' " +
            "AND img.productKey=:productKey")
    Optional<Image> findProductPrimaryImage(ProductKey productKey);

    List<Image> findAllByProductKey(ProductKey productKey);

    Optional<Image> findById(Long imgId);

    void deleteById(Long imgId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Image image " +
            "WHERE image.productKey =:productKey AND " +
            "image.id NOT IN :activeImagesIdList")
    void deleteProductInActiveImages(List<Long> activeImagesIdList, ProductKey productKey);

    @Query("SELECT image.productKey.id FROM Image image " +
            "WHERE image.productKey =:productKey AND " +
            "image.imageType = 'PRIMARY_IMG' " +
            "GROUP BY image.productKey " +
            "HAVING COUNT(image.productKey) > 1")
    List<Long> findImagesWhitMultiplePrimaryImages(ProductKey productKey);

    @Query("SELECT image FROM Image image WHERE image.productKey.id =:id")
    List<Image> findAllByProductId(Long id);
}
