package com.example.decorate.mapper;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.AttributeItem;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.AttributeModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttributeMapper {

    AttributeModel createAttributeModelFromAttribute(Attribute attribute);

    AttributeCreationFormData createAttributeFormData(Attribute attribute);

    List<AttributeCreationFormData> createAttributeFormData(List<Attribute> attribute);

    List<AttributeModel> attributeListToAttributeModelList(List<Attribute> attribute);

}
