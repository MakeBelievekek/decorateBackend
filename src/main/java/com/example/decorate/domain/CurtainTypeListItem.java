package com.example.decorate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class CurtainTypeListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "key_id")
    private KeyHolder key;

    @Column(name = "curtain_type")
    @Enumerated(EnumType.STRING)
    private CurtainType curtainType;

    public CurtainTypeListItem(KeyHolder key, CurtainType curtainType) {
        this.key = key;
        this.curtainType = curtainType;
    }
}
