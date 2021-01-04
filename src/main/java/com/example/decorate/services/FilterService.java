package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.*;
import com.example.decorate.repositorys.AttributeRepository;
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
    private final ModelCreatorService modelCreatorService;


    public List<ProductCategoryModalDto> getAllAttributesByType() {
        List<ProductCategoryModalDto> allAttributesByType = new ArrayList<>();
        setAttributesForProductsWhitSubType(allAttributesByType);
        setAttributesByTypeForProductsWhitNoSubType(allAttributesByType);
        return allAttributesByType;
    }

    private void setAttributesForProductsWhitSubType(List<ProductCategoryModalDto> allAttributesByType) {
        // TODO: 12/25/2020 kivettem belőle az üres typot és a díszpárna opciót
        //  de a díszpárna nem maradhat attribute mert akkor ez nem egy subtype
        //  hanem ez csak egy boolean ami vel mindennek ami lehet rendelkeznie kell
        //  az üres attributtal is kéne valamit kezdeni
        List<Attribute> subTypes = attributeRepository.findAllSubTypes();

        for (Attribute subType : subTypes) {
            List<Attribute> attributesForSubTypes = attributeRepository.findAllAttributesForSubTypesByAttribute(subType);

            Map<String, List<AttributeModel>> attributeModelsByType = groupAttributesByAttributeType(attributesForSubTypes);
            SearchModel searchModel = new SearchModel();
            searchModel.setSubType(subType.getDescription());
            searchModel.setSubTypeId(subType.getId());

        if (attributeModelsByType != null) {
                allAttributesByType.add(createProductCategoryModalDto(searchModel, attributeModelsByType));
            }
        }
    }

    private void setAttributesByTypeForProductsWhitNoSubType(List<ProductCategoryModalDto> allAttributesByType) {
        ProductType[] productTypes = ProductType.values();

        for (ProductType productType : productTypes) {

            if (!productType.equals(ProductType.CURTAIN)) {
                List<Attribute> allAttributeByProductType = this.attributeRepository
                        .findAllAttributeByProductType(productType);
                String productTypeName = productType.toString();

                Map<String, List<AttributeModel>> attributeModelsByType = groupAttributesByAttributeType(allAttributeByProductType);
                SearchModel searchModel = new SearchModel();
                searchModel.setProductType(productTypeName);

                allAttributesByType.add(createProductCategoryModalDto(searchModel, attributeModelsByType));
            }
        }
    }

    private Map<String, List<AttributeModel>> groupAttributesByAttributeType(List<Attribute> allByProductType) {
        if (allByProductType.isEmpty()) {
            return new HashMap<>();
        } else {
            return allByProductType.stream()
                    .map(modelCreatorService::createAttributeModelFromAttribute)
                    .collect(Collectors.groupingBy(AttributeModel::getType));
        }
    }

    private ProductCategoryModalDto createProductCategoryModalDto(SearchModel searchModel, Map<String, List<AttributeModel>> attributeModelsByType) {
        ProductCategoryModalDto productCategoryModalDto = new ProductCategoryModalDto();
        ProductAttributes productAttributes = new ProductAttributes();
        productCategoryModalDto.setSearchModel(searchModel);
        productCategoryModalDto.setProductAttributes(productAttributes);

        if (searchModel.getSubTypeId() == null) {
            productCategoryModalDto.setProductType(ProductType.valueOf(searchModel.getProductType()).getType());
            productCategoryModalDto.setProductDatabaseName(searchModel.getProductType());
        } else {
            productCategoryModalDto.setProductType(searchModel.getSubType());
        }
        attributeModelsByType.forEach((key, attributesByType) -> {
            switch (key) {
                case "COLOR":
                    productCategoryModalDto.getProductAttributes().setColorList(attributesByType);
                    break;
                case "PATTERN":
                    productCategoryModalDto.getProductAttributes().setPatternList(attributesByType);
                    break;
                case "STYLE":
                    productCategoryModalDto.getProductAttributes().setStyleList(attributesByType);
                    break;
                case "COMPOSITION":
                    productCategoryModalDto.getProductAttributes().setCompositionList(attributesByType);
                    break;
            }
        });
        return productCategoryModalDto;
    }

    public List<ProductCreationFormData> findProductByFilter(SearchModel searchModel) {
        String productType = searchModel.getProductType();
        List<Long> attributeIds = new ArrayList<>();
        boolean hasFilterAttributes = searchModel.getAttributeIds() != null;
        if (hasFilterAttributes) {
            attributeIds.addAll(searchModel.getAttributeIds());
        }

        List<ProductCreationFormData> result = new ArrayList<>();
        if (productType != null && productType.length() > 0) {
            result = getProductCreationFormDataList(productType, result, attributeIds);
        } else {
            Long subTypeId = searchModel.getSubTypeId();
            attributeIds.add(subTypeId);
            Long numberOfAttributes = (long) attributeIds.size();

            List<Curtain> curtainByAttributeAndSubType = curtainRepository
                    .findByAttributeIds(attributeIds, numberOfAttributes);
            result = modelCreatorService.curtainListToProductCreationFormData(curtainByAttributeAndSubType);
        }
        return result;
    }

    private List<ProductCreationFormData> getProductCreationFormDataList(String productType,
                                                                         List<ProductCreationFormData> result,
                                                                         List<Long> attributeIds) {
        Long numberOfAttributes = (long) attributeIds.size();
        boolean noFilterAttributes = numberOfAttributes == 0;

        switch (productType) {
            case "DECORATION":
                List<Decoration> decorationsByAttributeIds;
                if (noFilterAttributes) {
                    decorationsByAttributeIds = decorationRepository.findAll();
                } else {
                    decorationsByAttributeIds = decorationRepository.findAllByAttributeIds(attributeIds, numberOfAttributes);
                }
                result = modelCreatorService.decorationListToProductCreationFormDataList(decorationsByAttributeIds);
                break;
            case "WALLPAPER":
                List<Wallpaper> wallpapersByAttributeIds;
                if (noFilterAttributes) {
                    wallpapersByAttributeIds = wallpaperRepository.findAll();
                } else {
                    wallpapersByAttributeIds = wallpaperRepository.findAllByAttributeIds(attributeIds, numberOfAttributes);
                }
                result = modelCreatorService.wallpaperListToProductCreationFormDataList(wallpapersByAttributeIds);
                break;
            case "FURNITURE":
                List<FurnitureFabric> furnitureFabricByAttributeIds;
                if (noFilterAttributes) {
                    furnitureFabricByAttributeIds = furnitureFabricRepository.findAll();
                } else {
                    furnitureFabricByAttributeIds = furnitureFabricRepository.findAllByAttributeIds(attributeIds, numberOfAttributes);
                }
                result = modelCreatorService.furnitureFabricListToProductCreationFormDataList(furnitureFabricByAttributeIds);
                break;
        }
        return result;
    }

    public List<ProductCreationFormData> fetchAllProductOrderedByCreation() {
        return null;
    }

    public ProductCategoryModalDto findAttributesBySingleFilter(SearchModel searchModel) {
        Long subTypeId = searchModel.getSubTypeId();
        boolean hasSubTypeId = subTypeId != null;

        String productType = searchModel.getProductType();
        boolean hasProductType = productType != null && (!productType.equals(""));

        ProductCategoryModalDto result = null;

        if (hasProductType) {
            ProductType productTypeEnum = ProductType.valueOf(productType);

            List<Attribute> allAttributeByProductType = this.attributeRepository.findAllAttributeByProductType(productTypeEnum);
            Map<String, List<AttributeModel>> attributeModelListGroupByAttributeType = groupAttributesByAttributeType(allAttributeByProductType);
            result = createProductCategoryModalDto(searchModel, attributeModelListGroupByAttributeType);
        } else if(hasSubTypeId) {
            List<Attribute> allAttributeByProductType = this.attributeRepository.findAllAttributesForSubTypesByAttributeId(subTypeId);
            Map<String, List<AttributeModel>> attributeModelListGroupByAttributeType = groupAttributesByAttributeType(allAttributeByProductType);
            result = createProductCategoryModalDto(searchModel, attributeModelListGroupByAttributeType);
        }
        return result;
    }
}
