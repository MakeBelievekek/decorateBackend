package com.example.decorate.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class KeyHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;

    @OneToMany(mappedBy = "key")
    private List<AttributeListItem> attributeListItems;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public List<AttributeListItem> getAttributeListItems() {
        return attributeListItems;
    }

    public void setAttributeListItems(List<AttributeListItem> attributeListItems) {
        this.attributeListItems = attributeListItems;
    }

}
