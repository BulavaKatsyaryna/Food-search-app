package com.example.foodsearchapp.repo;

import com.example.foodsearchapp.model.EStatus;
import com.example.foodsearchapp.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepo extends JpaRepository<Status, Long> {

    Optional<Status> findByName(EStatus name);
}
