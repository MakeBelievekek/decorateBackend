package com.example.decorate.mapper;

import com.example.decorate.domain.FurnitureFabric;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.FurnitureFabricModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FurnitureFabricMapper {

    @Mapping(source = "images", target = "imageList")
    @Mapping(source = "attributeItems", target = "attributes")
    FurnitureFabricModel furnitureFabricToFurnitureFabricModel(FurnitureFabric furnitureFabric);

    @Mapping(source = "productKey.id", target = "id")
    @Mapping(source = "productKey", target = "productKey")
    FurnitureFabric productCreationToFurnitureFabric(ProductCreationFormData productCreationFormData, ProductKey productKey);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "attributeItems", ignore = true)
    @Mapping(target = "modified", expression = "java(java.time.Instant.now())")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "productKey", ignore = true)
    void updateFurnitureFabricFields(@MappingTarget FurnitureFabric furnitureFabric, FurnitureFabricModel furnitureFabricModel);

    @Mapping(target = "recommendedGlue", ignore = true)
    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "curtainType", ignore = true)
    @Mapping(target = "annotation", ignore = true)
    @Mapping(source = "images", target = "imageList")
    @Mapping(source = "attributeItems", target = "attributeCreationFormDataList")
    ProductCreationFormData furnitureFabricToFormData(FurnitureFabric furnitureFabric, @MappingTarget ProductCreationFormData productCreationFormData);

    @Mapping(target = "recommendedGlue", ignore = true)
    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "curtainType", ignore = true)
    @Mapping(target = "annotation", ignore = true)
    @Mapping(source = "images", target = "imageList")
    @Mapping(source = "attributeItems", target = "attributeCreationFormDataList")
    ProductCreationFormData furnitureFabricToFormData(FurnitureFabric furnitureFabric);

    List<ProductCreationFormData> furnitureFabricListToFormDataList(List<FurnitureFabric> furnitureFabric);

    List<FurnitureFabricModel> furnitureFabricListToFurnitureFabricModelList(List<FurnitureFabric> furnitureFabrics);
}
