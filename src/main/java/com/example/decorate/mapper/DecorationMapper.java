package com.example.decorate.mapper;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.Decoration;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.DecorationModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DecorationMapper {

    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "imageList", ignore = true)
    DecorationModel decorationToDecorationModel(Decoration decoration);

    Decoration productCreationToDecoration(ProductCreationFormData productCreationFormData, ProductKey productKey);

    @Mapping(target = "modified", expression = "java(java.time.Instant.now())")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "productKey", ignore = true)
    void updateDecorationFields(@MappingTarget Decoration decoration, DecorationModel decorationModel);
}
