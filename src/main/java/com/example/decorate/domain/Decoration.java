package com.example.decorate.domain;

import com.example.decorate.domain.dto.ProductCreationFormData;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
public class Decoration {
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "key_id")
    private ProductKey productKey;

    @Column(name = "name")
    private String name;

    @Column(name = " description",columnDefinition = "text")
    private String productDesc;

    @Column(name = "itemNo")
    private String itemNumber;

    @Column(name = "width")
    private int width;

    private int height;

    @Column(name = "pattern")
    private double patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition")
    private String composition;

    private String productFamily;

    private String annotation;

    private String recommendedGlue;

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

    public Decoration(ProductCreationFormData productCreationFormData, ProductKey productKey) {
        this.productKey = productKey;
        this.id = productKey.getId();
        this.name = productCreationFormData.getName();
        this.productDesc = productCreationFormData.getProductDesc();
        this.itemNumber = productCreationFormData.getItemNumber();
        this.width = productCreationFormData.getWidth();
        this.height = productCreationFormData.getHeight();
        this.patternRep = productCreationFormData.getPatternRep();
        this.price = productCreationFormData.getPrice();
        this.composition = productCreationFormData.getComposition();
        this.productFamily = productCreationFormData.getProductFamily();
        this.annotation = productCreationFormData.getAnnotation();
        this.recommendedGlue = productCreationFormData.getRecommendedGlue();
        this.created = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Decoration that = (Decoration) o;
        return productKey.equals(that.productKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productKey);
    }
}
