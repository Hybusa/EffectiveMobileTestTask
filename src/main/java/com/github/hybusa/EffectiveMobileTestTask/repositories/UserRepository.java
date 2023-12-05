package com.github.hybusa.EffectiveMobileTestTask.repositories;

import java.util.Optional;

import com.github.hybusa.EffectiveMobileTestTask.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}