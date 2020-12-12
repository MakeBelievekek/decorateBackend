package com.example.decorate.repositorys.security;

import com.example.decorate.domain.DecorateUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DecorateUserRepository extends JpaRepository<DecorateUser, Long> {
    Optional<DecorateUser> findByUserId(Long userId);

    Optional<DecorateUser> findByUsername(String username);
}
