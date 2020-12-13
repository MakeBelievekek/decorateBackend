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

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = KeyHolder.class)
    @JoinColumn(name = "key_id")
    private KeyHolder key;

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = Attribute.class)
    private Attribute attribute;

    @ManyToOne(fetch = FetchType.LAZY,
            targetEntity = Curtain.class)
    @JoinColumn(name = "curtain_id")
    private Curtain curtain;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();


    public CurtainAttribute(KeyHolder key, CurtainType curtainType, Attribute attribute) {
        this.key = key;
        this.created = Instant.now();
    }

    public CurtainAttribute(Attribute attribute, Curtain curtain, KeyHolder key) {
        this.attribute = attribute;
        this.curtain = curtain;
        this.key = key;
        this.created = Instant.now();
    }
}
