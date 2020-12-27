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

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DecorationMapper {

    @Mapping(source = "images", target = "imageList")
    @Mapping(source = "attributeItems", target = "attributes")
    DecorationModel decorationToDecorationModel(Decoration decoration);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "attributeItems", ignore = true)
    @Mapping(source = "productKey.id", target = "id")
    @Mapping(source = "productKey", target = "productKey")
    Decoration productCreationToDecoration(ProductCreationFormData productCreationFormData, ProductKey productKey);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "attributeItems", ignore = true)
    @Mapping(target = "modified", expression = "java(java.time.Instant.now())")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "productKey", ignore = true)
    void updateDecorationFields(@MappingTarget Decoration decoration, DecorationModel decorationModel);

    @Mapping(target = "typeOfSize", ignore = true)
    @Mapping(target = "productType", ignore = true)
    @Mapping(target = "curtainType", ignore = true)
    @Mapping(target = "cleaningInst", ignore = true)
    @Mapping(target = "abrasionResistance", ignore = true)
    @Mapping(source = "images", target = "imageList")
    @Mapping(source = "attributeItems", target = "attributeCreationFormDataList")
    ProductCreationFormData decorationToFormData(Decoration decoration, @MappingTarget ProductCreationFormData productCreationFormData);

    @Mapping(source = "images", target = "imageList")
    @Mapping(source = "attributeItems", target = "attributeCreationFormDataList")
    ProductCreationFormData decorationToFormData(Decoration decoration);

    List<ProductCreationFormData> decorationListToFormDataList(List<Decoration> decoration);

    List<DecorationModel> decorationListToDecorationModelList(List<Decoration> decorations);
}
