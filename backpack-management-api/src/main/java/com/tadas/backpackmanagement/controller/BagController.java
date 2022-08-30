package com.tadas.backpackmanagement.controller;

import com.tadas.backpackmanagement.model.payload.BagPayload;
import com.tadas.backpackmanagement.model.query.BagQuery;
import com.tadas.backpackmanagement.model.view.BagItemView;
import com.tadas.backpackmanagement.service.BagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/bags")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BagController {
    BagService bagService;

    @GetMapping
    public Page<BagItemView> getBag(BagQuery query, Pageable pageable) {
        return bagService.findAllBagItems(query, pageable);
    }

    @PostMapping
    public void createBag(@RequestBody BagPayload payload) {
        bagService.formatBag(payload);
    }
}
