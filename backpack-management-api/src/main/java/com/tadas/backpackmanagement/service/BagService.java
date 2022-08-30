package com.tadas.backpackmanagement.service;

import com.tadas.backpackmanagement.model.payload.BagPayload;
import com.tadas.backpackmanagement.model.query.BagQuery;
import com.tadas.backpackmanagement.model.view.BagItemView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BagService {
    Page<BagItemView> findAllBagItems(BagQuery query, Pageable pageable);

    void formatBag(BagPayload payload);
}
