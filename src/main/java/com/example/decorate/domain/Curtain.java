package com.example.decorate.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Curtain {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "key_id")
    private ProductKey productKey;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String productDesc;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "itemNo")
    private String itemNumber;

    @Column(name = "pattern")
    private double patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition")
    private String composition;

    private String productFamily;

    @Column(name = "cleaning")
    private String cleaningInst;

    private String typeOfSize;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(
            mappedBy = "productKey",
            fetch = FetchType.EAGER)
    private List<Image> images;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(
            mappedBy = "productKey",
    fetch = FetchType.EAGER)
    private List<AttributeItem> attributeItems;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curtain curtain = (Curtain) o;
        return productKey.equals(curtain.productKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productKey);
    }
}
