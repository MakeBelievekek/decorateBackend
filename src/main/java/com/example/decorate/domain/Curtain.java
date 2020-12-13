package com.example.decorate.domain;

import com.example.decorate.domain.dto.ProductCreationFormData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Curtain {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "key_id")
    private KeyHolder key;

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

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

    public Curtain(ProductCreationFormData productCreationFormData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = productCreationFormData.getName();
        this.productDesc = productCreationFormData.getProductDesc();
        this.itemNumber = productCreationFormData.getItemNumber();
        this.width = productCreationFormData.getWidth();
        this.height = productCreationFormData.getHeight();
        this.patternRep = productCreationFormData.getPatternRep();
        this.price = productCreationFormData.getPrice();
        this.composition = productCreationFormData.getComposition();
        this.productFamily = productCreationFormData.getProductFamily();
        this.cleaningInst = productCreationFormData.getCleaningInst();
        this.typeOfSize = productCreationFormData.getTypeOfSize();
        this.created = Instant.now();
    }

}
