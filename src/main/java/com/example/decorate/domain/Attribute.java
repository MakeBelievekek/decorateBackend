package com.example.decorate.domain;

import com.example.decorate.domain.dto.AttributeCreationFormData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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

    @Column(name = "time_stamp")
    private Instant timeStamp;

    public Attribute(AttributeCreationFormData attributeCreationFormData) {
        this.type = AttributeType.valueOf(attributeCreationFormData.getType());
        this.description = attributeCreationFormData.getDescription();
        this.timeStamp = Instant.now();
    }
 }
