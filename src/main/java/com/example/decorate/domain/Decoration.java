package com.example.decorate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Decoration {
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "key_id")
    private KeyHolder key;

    @Column(name = "name",columnDefinition = "text")
    private String name;

    @Column(name = " description",columnDefinition = "text")
    private String productDesc;

    @Column(name = "itemNo",columnDefinition = "text")
    private String itemNumber;

    @Column(name = "width")
    private int width;

    private int height;

    @Column(name = "pattern")
    private double patternRep;

    @Column(name = "price")
    private int price;

    @Column(name = "composition",columnDefinition = "text")
    private String composition;
    @Column(columnDefinition = "text")
    private String productFamily;
    @Column(columnDefinition = "text")
    private String annotation;
    @Column(columnDefinition = "text")
    private String recommendedGlue;

    @Column(name = "time_stamp")
    private Instant timeStamp;
}
