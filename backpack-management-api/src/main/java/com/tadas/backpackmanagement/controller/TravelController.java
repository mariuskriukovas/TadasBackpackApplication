package com.tadas.backpackmanagement.controller;

import com.tadas.backpackmanagement.model.view.TravelView;
import com.tadas.backpackmanagement.service.TravelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public List<TravelView> getAllTravels() {
        return travelService.findAllTravels();
    }

    @PostMapping
    public void addTravel(@RequestBody TravelView view) {
        travelService.saveTravel(view);
    }

    @GetMapping("/{id}/weight")
    Double findTravelBagWeight(@PathVariable("id") Long id) {
        return travelService.countTravelBagWeight(id);
    }

    @DeleteMapping("/{id}/bagItems")
    void clearTravelBag(@PathVariable("id") Long id) {
        travelService.clearTravelBag(id);
    }

}
