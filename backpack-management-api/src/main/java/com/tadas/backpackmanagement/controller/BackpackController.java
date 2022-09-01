package com.tadas.backpackmanagement.controller;

import com.tadas.backpackmanagement.model.payload.BackpackPayload;
import com.tadas.backpackmanagement.model.query.BagQuery;
import com.tadas.backpackmanagement.model.view.BagItemView;
import com.tadas.backpackmanagement.service.BackpackService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/backpack")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BackpackController {
    BackpackService backpackService;

    @GetMapping
    public Page<BagItemView> getBackpackContent(BagQuery query, Pageable pageable) {
        return backpackService.findAllBagItems(query, pageable);
    }

    @PostMapping("/pack")
    public void packBackpack(@RequestBody BackpackPayload payload) {
        backpackService.packBackpack(payload);
    }
}
