package com.example.decorate.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class DecorateUser {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    private String username;

    private String simpleUsername;

    private String password;

    private String email;

    private Instant created;

    private boolean enabled;

    private String authority;
}
