package com.tadas.backpackmanagement.service;

import com.tadas.backpackmanagement.model.view.ItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    Page<ItemView> findAllItems(Pageable pageable);

    void saveItem(ItemView view);
}
