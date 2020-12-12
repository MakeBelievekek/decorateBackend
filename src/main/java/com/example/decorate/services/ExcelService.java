package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.ExcelData;
import com.example.decorate.repositorys.curtain.CurtainRepository;
import com.example.decorate.repositorys.curtain.CurtainAttributeRepository;
import com.example.decorate.repositorys.furniture.FurnitureRepository;
import com.example.decorate.repositorys.wallpaper.WallpaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ExcelService {


    CurtainRepository curtainRepository;
    WallpaperRepository wallpaperRepository;
    KeyHolderService keyHolderService;
    ImageService imageService;
    AttributeService attributeService;
    FurnitureRepository furnitureRepository;
    CurtainAttributeRepository curtainAttributeRepository;

    @Autowired
    public ExcelService(CurtainRepository curtainRepository, WallpaperRepository wallpaperRepository,
                        KeyHolderService keyHolderService, ImageService imageService,
                        AttributeService attributeService, FurnitureRepository furnitureRepository,
                        CurtainAttributeRepository curtainAttributeRepository) {
        this.curtainRepository = curtainRepository;
        this.curtainAttributeRepository = curtainAttributeRepository;
        this.wallpaperRepository = wallpaperRepository;
        this.keyHolderService = keyHolderService;
        this.imageService = imageService;
        this.attributeService = attributeService;
        this.furnitureRepository = furnitureRepository;
    }

    public void save(MultipartFile file) {
        try {
            List<ExcelData> products = ExcelHelper.excelToTutorials(file.getInputStream());
          /*  for (ExcelData product : products) {
                KeyHolder keyHolder = new KeyHolder();
                switch (product.getTypeOfProduct()) {
                    case "WALLPAPER":
                        keyHolderService.saveKey(keyHolder, ProductType.WALLPAPER);
                        Wallpaper wallpaper = new Wallpaper(product, keyHolder);
                        wallpaperRepository.save(wallpaper);
                        imageService.saveImageList(product.getImageList(), wallpaper.getId(), ProductType.WALLPAPER);
                        attributeService.saveAttributesFromExcel(product.getAttributes(), keyHolder);
                        break;
                    case "CURTAIN":
                        keyHolderService.saveKey(keyHolder, ProductType.CURTAIN);
                        System.out.println(keyHolder);
                        System.out.println("---------------------------------------------------------------------------------");
                        System.out.println(product);
                        System.out.println("---------------------------------------------------------------------------------");
                        Curtain curtain = new Curtain(product, keyHolder);
                        curtainRepository.save(curtain);
                        imageService.saveImageList(product.getImageList(), curtain.getId(), ProductType.CURTAIN);
                        attributeService.saveAttributesFromExcel(product.getAttributes(), keyHolder);
                        for (String curtainType : product.getCurtainTypes()) {
                            curtainTypeListItemRepository.save(new CurtainTypeListItem(keyHolder,getCurtainType(curtainType)));
                        }
                        break;
                    case "FURNITURE":
                        keyHolderService.saveKey(keyHolder, ProductType.FURNITURE);
                        FurnitureFabric furnitureFabric = new FurnitureFabric(product, keyHolder);
                        furnitureRepository.save(furnitureFabric);
                        imageService.saveImageList(product.getImageList(), furnitureFabric.getId(), ProductType.FURNITURE);
                        attributeService.saveAttributesFromExcel(product.getAttributes(), keyHolder);
                        break;
                }
            }*/
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public CurtainType getCurtainType(String type) {
        for (CurtainType curtain : CurtainType.values()) {
            if (curtain.getType().equals(type)) {
                return curtain;
            }
        }
        return null;
    }

}
