package com.tadas.backpackmanagement.service;

import com.tadas.backpackmanagement.model.view.TravelerView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TravelerService {
    Page<TravelerView> findAllTravelers(Pageable pageable);
    List<TravelerView> findAllTravelers();
    void saveTraveler(TravelerView view);
}
