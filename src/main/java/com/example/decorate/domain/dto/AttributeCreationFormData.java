package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AttributeCreationFormData {

    private Long id;

    private String type;

    private String description;

    public AttributeCreationFormData(String type, String description) {
        this.description = description;
        this.type = type;
    }
}
