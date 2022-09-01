package com.tadas.backpackmanagement.service;

import com.tadas.backpackmanagement.model.payload.BackpackPayload;
import com.tadas.backpackmanagement.model.query.BagQuery;
import com.tadas.backpackmanagement.model.view.BagItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BackpackService {
    Page<BagItemView> findAllBagItems(BagQuery query, Pageable pageable);

    void packBackpack(BackpackPayload payload);
}
