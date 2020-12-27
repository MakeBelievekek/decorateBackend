package com.example.decorate.mapper;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.AttributeItem;
import com.example.decorate.domain.ProductKey;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.AttributeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttributeItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modified", ignore = true)
    AttributeItem createAttributeItemFromAttribute(Attribute attribute, ProductKey productKey);

    @Mapping(source = "attribute.description", target = "description")
    @Mapping(source = "attribute.type", target = "type")
    @Mapping(source = "attribute.id", target = "id")
    AttributeCreationFormData attributeItemToAttributeCreationFormData(AttributeItem attributeItem);

    List<AttributeCreationFormData> attributeItemToAttributeCreationFormDataList(List<AttributeItem>  attributeItemList);

    List<AttributeModel> attributeItemToAttributeModel(List<AttributeItem>  attributeItemList);

}
