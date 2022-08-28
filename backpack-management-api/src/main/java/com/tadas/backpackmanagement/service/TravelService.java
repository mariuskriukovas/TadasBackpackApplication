package com.tadas.backpackmanagement.service;

import com.tadas.backpackmanagement.model.view.TravelView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TravelService {
    Page<TravelView> findAllTravels(Pageable pageable);

    void saveTravel(TravelView view);
}
