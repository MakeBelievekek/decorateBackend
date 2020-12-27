package com.example.decorate.mapper;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurtainMapper {

    @Mapping(target = "curtainType", ignore = true)
    @Mapping(source = "images", target = "imageList")
    @Mapping(source = "attributeItems", target = "attributes")
    CurtainModel curtainToCurtainModel(Curtain curtain);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "attributeItems", ignore = true)
    @Mapping(source = "productKey.id", target = "id")
    @Mapping(source = "productKey", target = "productKey")
    Curtain productCreationToCurtain(ProductCreationFormData productCreationFormData, ProductKey productKey);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "attributeItems", ignore = true)
    @Mapping(target = "modified", expression = "java(java.time.Instant.now())")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "productKey", ignore = true)
    void updateCurtainFields(@MappingTarget Curtain curtain, CurtainModel curtainModel);

    @Mapping(target = "recommendedGlue", ignore = true)
    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "curtainType", ignore = true)
    @Mapping(target = "annotation", ignore = true)
    @Mapping(target = "abrasionResistance", ignore = true)
    @Mapping(source = "images", target = "imageList")
    @Mapping(target = "attributeCreationFormDataList", ignore = true)
    ProductCreationFormData curtainToFormData(Curtain curtain, @MappingTarget ProductCreationFormData productCreationFormData);

    @Mapping(target = "recommendedGlue", ignore = true)
    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "curtainType", ignore = true)
    @Mapping(target = "annotation", ignore = true)
    @Mapping(target = "abrasionResistance", ignore = true)
    @Mapping(source = "images", target = "imageList")
    @Mapping(target = "attributeCreationFormDataList", ignore = true)
    ProductCreationFormData curtainToFormData(Curtain curtain);

}

