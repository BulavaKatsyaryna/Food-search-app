package com.example.foodsearchapp.repo;

import com.example.foodsearchapp.model.Status;
import com.example.foodsearchapp.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepo extends JpaRepository<UserStatus, Long> {

    Optional<UserStatus> findByName(Status name);
}
