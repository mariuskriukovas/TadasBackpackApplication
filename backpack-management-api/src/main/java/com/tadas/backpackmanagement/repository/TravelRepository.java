package com.tadas.backpackmanagement.repository;

import com.tadas.backpackmanagement.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {
}