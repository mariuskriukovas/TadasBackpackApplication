package com.tadas.backpackmanagement.controller;

import com.tadas.backpackmanagement.model.view.TravelerListView;
import com.tadas.backpackmanagement.service.TravelerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/travelers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TravelerController {

    TravelerService travelerService;

    @GetMapping
    public Page<TravelerListView> getAllTravelers(Pageable pageable) {
        return travelerService.findAllTravelers(pageable);
    }

}
