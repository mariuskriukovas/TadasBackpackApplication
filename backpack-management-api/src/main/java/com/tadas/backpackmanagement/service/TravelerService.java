package com.tadas.backpackmanagement.service;

import com.tadas.backpackmanagement.model.view.TravelerListView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TravelerService {
    Page<TravelerListView> findAllTravelers(Pageable pageable);
}
