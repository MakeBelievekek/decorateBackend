package com.example.decorate.domain;

import com.example.decorate.domain.dto.AttributeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class CurtainAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "key_id")
    private KeyHolder key;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "curtain_id")
    private Curtain curtain;

    @Column(name = "time_stamp")
    private Instant timeStamp = Instant.now();

    public CurtainAttribute(KeyHolder key, CurtainType curtainType, Attribute attribute) {
        this.key = key;
        this.timeStamp = Instant.now();
    }

    public CurtainAttribute(Attribute attribute, Curtain curtain, KeyHolder key) {
        this.attribute = attribute;
        this.curtain = curtain;
        this.key = key;
        this.timeStamp = Instant.now();
    }
}