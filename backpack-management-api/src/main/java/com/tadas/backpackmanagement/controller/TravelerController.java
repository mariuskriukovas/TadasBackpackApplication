package com.tadas.backpackmanagement.controller;

import com.tadas.backpackmanagement.model.view.TravelerView;
import com.tadas.backpackmanagement.service.TravelerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/travelers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TravelerController {
    TravelerService travelerService;

    @GetMapping
    public Page<TravelerView> getAllTravelers(Pageable pageable) {
        return travelerService.findAllTravelers(pageable);
    }

    @GetMapping("/list")
    public List<TravelerView> getAllTravelers() {
        return travelerService.findAllTravelers();
    }

    @PostMapping
    public void addTraveler(@RequestBody TravelerView view) {
        travelerService.saveTraveler(view);
    }

}
