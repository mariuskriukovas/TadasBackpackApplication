package com.tadas.backpackmanagement.repository;

import com.tadas.backpackmanagement.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerRepository extends JpaRepository<Traveler, Long> {
}