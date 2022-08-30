package com.tadas.backpackmanagement.service;

import com.tadas.backpackmanagement.model.view.TravelView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TravelService {
    Page<TravelView> findAllTravels(Pageable pageable);

    List<TravelView> findAllTravels();

    void saveTravel(TravelView view);
}
