package com.example.decorate.domain;

import com.example.decorate.domain.dto.ProductFormData;

import javax.persistence.*;

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

    @Column(name = " description")
    private String productDesc;
    @Column(name = "curtain_type")
    private CurtainType curtainType;
    @Column(name = "height")
    private int height;

    @Column(name = "itemNo")
    private int itemNumber;
    @Column(name = "pattern")
    private int patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition")
    private String composition;

    private String productFamily;
    @Column(name = "cleaning")
    private String cleaningInst;

    public Curtain() {
    }

    public Curtain(ProductFormData productFormData, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.id = keyHolder.getId();
        this.name = productFormData.getName();
        for (CurtainType curtain : CurtainType.values()) {
            if (curtain.getType().equals(productFormData.getCurtainType())) {
                this.curtainType = curtain;
            }
        }
        this.productDesc = productFormData.getProductDesc();
        this.itemNumber = productFormData.getItemNumber();
        this.height = productFormData.getHeight();
        this.patternRep = productFormData.getPatternRep();
        this.price = productFormData.getPrice();
        this.composition = productFormData.getComposition();
        this.productFamily = productFormData.getProductFamily();
        this.cleaningInst = productFormData.getCleaningInst();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KeyHolder getKey() {
        return key;
    }

    public void setKey(KeyHolder key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public CurtainType getType() {
        return curtainType;
    }

    public void setType(CurtainType curtainType) {
        this.curtainType = curtainType;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getPatternRep() {
        return patternRep;
    }

    public void setPatternRep(int patternRep) {
        this.patternRep = patternRep;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    public String getCleaningInst() {
        return cleaningInst;
    }

    public void setCleaningInst(String cleaningInst) {
        this.cleaningInst = cleaningInst;
    }
}
