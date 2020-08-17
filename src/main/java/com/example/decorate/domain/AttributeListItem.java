package com.example.decorate.domain;

import javax.persistence.*;

@Entity
public class AttributeListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "key_id")
    private KeyHolder key;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    public AttributeListItem() {
    }

    public AttributeListItem(KeyHolder key, Attribute attribute) {
        this.key = key;
        this.attribute = attribute;
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

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
