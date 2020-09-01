package com.example.decorate.domain;

import com.example.decorate.domain.dto.AttributeFormData;

import javax.persistence.*;
import java.util.List;

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

    public Attribute() {
    }

    public Attribute(AttributeFormData attributeFormData) {
        for (AttributeType attributeType : AttributeType.values()) {
            if (attributeType.getType().equals(attributeFormData.getType())) {
                this.type = attributeType;
            }
        }
        this.description = attributeFormData.getDescription();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
