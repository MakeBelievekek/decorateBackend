package com.example.decorate.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class AttributeItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductKey productKey;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

    public AttributeItem(ProductKey productKey, Attribute attribute) {
        this.productKey = productKey;
        this.attribute = attribute;
    }

    public AttributeItem(Attribute attribute, ProductKey productKey) {
        this.productKey = productKey;
        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeItem that = (AttributeItem) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
