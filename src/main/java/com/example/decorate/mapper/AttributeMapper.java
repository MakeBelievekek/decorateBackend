package com.example.decorate.mapper;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.AttributeModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttributeMapper {

    AttributeModel createAttributeModelFromAttribute(Attribute attribute);

    AttributeCreationFormData createAttributeFormData(Attribute attribute);
}
