package com.airbnb.airbnb.repository;

import com.airbnb.airbnb.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {

        //  part 2
    Optional<PropertyUser> findByUsername(String username);
}