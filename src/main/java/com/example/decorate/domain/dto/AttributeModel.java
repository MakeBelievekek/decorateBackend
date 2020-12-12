package com.example.decorate.domain.dto;

import com.example.decorate.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttributeModel {

    private Long id;

    private String type;

    private String description;

    public AttributeModel(Attribute attribute) {
        this.id = attribute.getId();
        this.type = attribute.getType().getType();
        this.description = attribute.getDescription();
    }

    public AttributeModel(AttributeListItem attributeListItem) {
        this.id = attributeListItem.getId();
        this.type = attributeListItem.getAttribute().getType().toString();
        this.description = attributeListItem.getAttribute().getDescription();
    }

    public AttributeModel(CurtainAttribute curtainAttribute) {
        this.id = curtainAttribute.getId();
        this.type = curtainAttribute.getAttribute().getType().toString();
        this.description = curtainAttribute.getAttribute().getDescription();
    }

    public AttributeModel(WallpaperAttribute wallpaperAttribute) {
        this.id = wallpaperAttribute.getId();
        this.type = wallpaperAttribute.getAttribute().getType().toString();
        this.description = wallpaperAttribute.getAttribute().getDescription();
    }

    public AttributeModel(FurnitureFabricAttribute furnitureFabricAttribute) {
        this.id = furnitureFabricAttribute.getId();
        this.type = furnitureFabricAttribute.getAttribute().getType().toString();
        this.description = furnitureFabricAttribute.getAttribute().getDescription();
    }


}
