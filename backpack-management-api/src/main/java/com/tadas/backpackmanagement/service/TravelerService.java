package com.tadas.backpackmanagement.service;

import com.tadas.backpackmanagement.model.view.TravelerView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TravelerService {
    Page<TravelerView> findAllTravelers(Pageable pageable);
    void saveTraveler(TravelerView view);
}
