package com.example.decorate.domain;

import com.example.decorate.domain.dto.AttributeCreationFormData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AttributeType type;

    @Column(name = "description")
    private String description;

    public Attribute(AttributeCreationFormData attributeCreationFormData) {
        for (AttributeType attributeType : AttributeType.values()) {
            if (attributeType.getType().equals(attributeCreationFormData.getType())) {
                this.type = attributeType;
            }
        }
        this.description = attributeCreationFormData.getDescription();
    }
}
