package com.example.decorate.mapper;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.AttributeItem;
import com.example.decorate.domain.ProductKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttributeItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "modified", ignore = true)
    AttributeItem createAttributeItemFromAttribute(Attribute attribute, ProductKey productKey);
}
