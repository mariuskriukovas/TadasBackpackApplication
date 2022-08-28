package com.tadas.backpackmanagement.controller;

import com.tadas.backpackmanagement.model.view.TravelView;
import com.tadas.backpackmanagement.service.TravelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/travels")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TravelController {
    TravelService travelService;

    @GetMapping
    public Page<TravelView> getAllTravels(Pageable pageable) {
        return travelService.findAllTravels(pageable);
    }

    @PostMapping
    public void addTravel(@RequestBody TravelView view) {
        travelService.saveTravel(view);
    }

}