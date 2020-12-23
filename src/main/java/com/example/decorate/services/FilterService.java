package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.AttributeModel;
import com.example.decorate.domain.dto.ProductCategoryDto;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.domain.dto.SearchModel;
import com.example.decorate.mapper.*;
import com.example.decorate.repositorys.AttributeRepository;
import com.example.decorate.repositorys.ImageRepository;
import com.example.decorate.repositorys.curtain.CurtainRepository;
import com.example.decorate.repositorys.decoration.DecorationRepository;
import com.example.decorate.repositorys.furniture.FurnitureFabricRepository;
import com.example.decorate.repositorys.wallpaper.WallpaperRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Transactional
@Service
public class FilterService {
    private final AttributeRepository attributeRepository;
    private final CurtainRepository curtainRepository;
    private final DecorationRepository decorationRepository;
    private final FurnitureFabricRepository furnitureFabricRepository;
    private final WallpaperRepository wallpaperRepository;
    private final AttributeMapper attributeMapper;
    private final CurtainMapper curtainMapper;
    private final FurnitureFabricMapper furnitureFabricMapper;
    private final DecorationMapper decorationMapper;
    private final WallpaperMapper wallpaperMapper;
    private final DTOMapperService dtoMapperService;

    public List<ProductCategoryDto> getAllAttributesByType() {
        List<ProductCategoryDto> allAttributesByType = new ArrayList<>();
        setAttributesByTypeForProductsWhitNoSubType(allAttributesByType);
        setAttributesForProductsWhitSubType(allAttributesByType);
        return allAttributesByType;
    }

    private void setAttributesByTypeForProductsWhitNoSubType(List<ProductCategoryDto> allAttributesByType) {
        ProductType[] productTypes = ProductType.values();

        for (ProductType productType : productTypes) {

            if (!productType.equals(ProductType.CURTAIN)) {
                List<Attribute> allByProductType = this.attributeRepository.findAllAttributeByProductType(productType);
                String productTypeName = productType.toString();

                Map<String, List<AttributeModel>> attributeModelsByType = groupAttributesByType(allByProductType);
                SearchModel searchModel = SearchModel.builder()
                        .productType(productTypeName)
                        .build();

                if (attributeModelsByType != null) {
                    allAttributesByType.add(createProductCategoryDto(searchModel, attributeModelsByType));
                }
            }
        }
    }

    private void setAttributesForProductsWhitSubType(List<ProductCategoryDto> allAttributesByType) {
        List<Attribute> subTypes = attributeRepository.findAllSubTypes();

        for (Attribute subType : subTypes) {
            List<Attribute> attributesForSubTypes = attributeRepository.findAllAttributesForMainAttributes(subType);

            Map<String, List<AttributeModel>> attributeModelsByType = groupAttributesByType(attributesForSubTypes);
            SearchModel searchModel = SearchModel.builder()
                    .productType(subType.getDescription())
                    .subTypeId(subType.getId())
                    .build();

            if (attributeModelsByType != null) {
                allAttributesByType.add(createProductCategoryDto(searchModel, attributeModelsByType));
            }
        }
    }

    private Map<String, List<AttributeModel>> groupAttributesByType(List<Attribute> allByProductType) {
        if (allByProductType.isEmpty()) {
            return null;
        } else {
            return allByProductType.stream()
                    .map(attributeMapper::createAttributeModelFromAttribute)
                    .collect(Collectors.groupingBy(AttributeModel::getType));
        }
    }

    private ProductCategoryDto createProductCategoryDto(SearchModel searchModel, Map<String, List<AttributeModel>> attributeModelsByType) {
        ProductCategoryDto productCategoryDto = ProductCategoryDto.builder()
                .searchModel(searchModel)
                .build();
        attributeModelsByType.forEach((key, attributesByType) -> {
            switch (key) {
                case "COLOR":
                    productCategoryDto.setColor(attributesByType);
                    break;
                case "PATTERN":
                    productCategoryDto.setPattern(attributesByType);
                    break;
                case "STYLE":
                    productCategoryDto.setStyle(attributesByType);
                    break;
            }
        });
        return productCategoryDto;
    }

    public List<ProductCreationFormData> findProductTypeByAttribute(SearchModel searchModel) {
        String productType = searchModel.getProductType();
        List<Long> attributeIds = searchModel.getAttributes().stream()
                .map(AttributeModel::getId)
                .collect(Collectors.toList());

        List<ProductCreationFormData> result = new ArrayList<>();
        if (productType != null && productType.length() > 0) {
            Long numberOfAttributes = (long) attributeIds.size();

            result = getProductCreationFormData(productType, result, attributeIds, numberOfAttributes);
        } else {
            Long subTypeId = searchModel.getSubTypeId();
            attributeIds.add(subTypeId);
            Long numberOfAttributes = (long) attributeIds.size();

            result = curtainRepository.findBySubTypeAttributeAndAttributes(attributeIds, numberOfAttributes).stream()
                    .map(curtain -> {
                        ProductCreationFormData productCreationFormData = curtainMapper.curtainToFormData(curtain);
                        productCreationFormData.setAttributeCreationFormDataList(dtoMapperService.getProductAllAttributeCreationModels(curtain.getProductKey()));
                        productCreationFormData.setImageList(dtoMapperService.getProductAllImageModels(curtain.getProductKey()));
                        return productCreationFormData;
                    })
                    .collect(Collectors.toList());
        }
        return result;
    }

    private List<ProductCreationFormData> getProductCreationFormData(String productType,
                                                                     List<ProductCreationFormData> result,
                                                                     List<Long> attributeIds,
                                                                     Long numberOfAttributes) {
        switch (productType) {
            case "DECORATION":
                result = decorationRepository.findAllByAttributeIds(attributeIds, numberOfAttributes).stream()
                        .map(decoration -> {
                            ProductCreationFormData productCreationFormData = decorationMapper.decorationToFormData(decoration);
                            productCreationFormData.setAttributeCreationFormDataList(dtoMapperService.getProductAllAttributeCreationModels(decoration.getProductKey()));
                            productCreationFormData.setImageList(dtoMapperService.getProductAllImageModels(decoration.getProductKey()));
                            return productCreationFormData;
                        }).collect(Collectors.toList());
                break;
            case "WALLPAPER":
                result = wallpaperRepository.findAllByAttributeIds(attributeIds, numberOfAttributes).stream()
                        .map(wallpaper -> {
                            ProductCreationFormData productCreationFormData = wallpaperMapper.wallpaperToFormData(wallpaper);
                            productCreationFormData.setAttributeCreationFormDataList(dtoMapperService.getProductAllAttributeCreationModels(wallpaper.getProductKey()));
                            productCreationFormData.setImageList(dtoMapperService.getProductAllImageModels(wallpaper.getProductKey()));
                            return productCreationFormData;
                        }).collect(Collectors.toList());
                ;
                break;
            case "FURNITURE":
                result = furnitureFabricRepository.findAllByAttributeIds(attributeIds, numberOfAttributes).stream()
                        .map(furnitureFabric -> {
                            ProductCreationFormData productCreationFormData = furnitureFabricMapper.furnitureFabricToFormData(furnitureFabric);
                            productCreationFormData.setAttributeCreationFormDataList(dtoMapperService.getProductAllAttributeCreationModels(furnitureFabric.getProductKey()));
                            productCreationFormData.setImageList(dtoMapperService.getProductAllImageModels(furnitureFabric.getProductKey()));
                            return productCreationFormData;
                        }).collect(Collectors.toList());
                ;
                break;
        }
        return result;
    }
}
