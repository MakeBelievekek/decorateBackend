package com.example.decorate.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class DecorateUser {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    private String username;

    private String simpleUsername;

    private String password;

    private String email;

    @Column(name = "modified")
    private Instant modified;

    private Instant created;

    private boolean enabled;

    private String authority;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DecorateUser that = (DecorateUser) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
