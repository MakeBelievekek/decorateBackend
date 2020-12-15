package com.example.decorate.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgUrl;

    private String type;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Home home = (Home) o;
        return id.equals(home.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
