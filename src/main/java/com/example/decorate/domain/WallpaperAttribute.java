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
public class WallpaperAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "key_id")
    private KeyHolder key;

    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @ManyToOne
    @JoinColumn(name = "wallpaper_id")
    private Wallpaper wallpaper;

    @Column(name = "time_stamp")
    private Instant timeStamp = Instant.now();

    public WallpaperAttribute(Attribute attribute, Wallpaper wallpaper, KeyHolder keyHolder) {
        this.key = keyHolder;
        this.attribute = attribute;
        this.wallpaper = wallpaper;
        this.timeStamp = Instant.now();
    }
}
