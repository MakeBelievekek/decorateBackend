package com.example.decorate.mapper;

import com.example.decorate.domain.FurnitureFabric;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.FurnitureFabricModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FurnitureFabricMapper {

    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "imageList", ignore = true)
    FurnitureFabricModel furnitureFabricToFurnitureFabricModel(FurnitureFabric furnitureFabric);

    @Mapping(target = "id", ignore = true)
    FurnitureFabric productCreationToFurnitureFabric(ProductCreationFormData productCreationFormData, ProductKey productKey);

    @Mapping(target = "modified", expression = "java(java.time.Instant.now())")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "productKey", ignore = true)
    void updateFurnitureFabricFields(@MappingTarget FurnitureFabric furnitureFabric, FurnitureFabricModel furnitureFabricModel);

    @Mapping(target = "imageList", ignore = true)
    @Mapping(target = "attributeCreationFormDataList", ignore = true)
    ProductCreationFormData furnitureFabricToFormData(FurnitureFabric furnitureFabric, @MappingTarget ProductCreationFormData productCreationFormData);
}
