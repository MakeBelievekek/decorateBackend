package com.example.decorate.domain;

import com.example.decorate.domain.dto.AttributeCreationFormData;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
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

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

    public Attribute(AttributeCreationFormData attributeCreationFormData) {
        this.type = AttributeType.valueOf(attributeCreationFormData.getType());
        this.description = attributeCreationFormData.getDescription();
        this.created = Instant.now();
    }

    public Attribute(String type, String description) {
        this.type = AttributeType.valueOf(type);
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return id.equals(attribute.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
