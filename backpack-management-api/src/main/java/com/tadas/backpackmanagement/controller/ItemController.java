package com.tadas.backpackmanagement.controller;

import com.tadas.backpackmanagement.model.view.ItemView;
import com.tadas.backpackmanagement.model.view.PreselectedItemView;
import com.tadas.backpackmanagement.service.ItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/items")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemController {
    ItemService itemService;

    @GetMapping
    public Page<ItemView> getAllItems(Pageable pageable) {
        return itemService.findAllItems(pageable);
    }

    @GetMapping("/list")
    public List<ItemView> getAllItems() {
        return itemService.findAllItems();
    }

    @PostMapping
    public void addItem(@RequestBody ItemView view) {
        itemService.saveItem(view);
    }

}
