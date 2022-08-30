package com.tadas.backpackmanagement.service;

import com.tadas.backpackmanagement.model.view.ItemView;
import com.tadas.backpackmanagement.model.view.PreselectedItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {
    Page<ItemView> findAllItems(Pageable pageable);

    List<ItemView> findAllItems();

    List<PreselectedItemView> findAllPreselectedItems();

    void saveItem(ItemView view);
}
