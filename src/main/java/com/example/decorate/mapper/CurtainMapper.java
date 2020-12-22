package com.example.decorate.mapper;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurtainMapper {

    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "imageList", ignore = true)
    @Mapping(target = "curtainType", ignore = true)
    CurtainModel curtainToCurtainModel(Curtain curtain);

    @Mapping(source = "productKey.id", target = "id")
    @Mapping(source = "productKey", target = "productKey")
    Curtain productCreationToCurtain(ProductCreationFormData productCreationFormData, ProductKey productKey);

    @Mapping(target = "modified", expression = "java(java.time.Instant.now())")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "productKey", ignore = true)
    void updateCurtainFields(@MappingTarget Curtain curtain, CurtainModel curtainModel);

    @Mapping(target = "imageList", ignore = true)
    @Mapping(target = "attributeCreationFormDataList", ignore = true)
    ProductCreationFormData curtainToFormData(Curtain curtain, @MappingTarget ProductCreationFormData productCreationFormData);

}

