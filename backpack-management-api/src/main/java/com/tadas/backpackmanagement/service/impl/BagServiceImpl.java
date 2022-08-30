package com.tadas.backpackmanagement.service.impl;

import com.tadas.backpackmanagement.entity.BagItem;
import com.tadas.backpackmanagement.entity.Item;
import com.tadas.backpackmanagement.entity.Travel;
import com.tadas.backpackmanagement.mapper.BagItemMapper;
import com.tadas.backpackmanagement.model.payload.BagPayload;
import com.tadas.backpackmanagement.model.query.BagQuery;
import com.tadas.backpackmanagement.model.view.BagItemView;
import com.tadas.backpackmanagement.repository.BagItemRepository;
import com.tadas.backpackmanagement.repository.ItemRepository;
import com.tadas.backpackmanagement.repository.TravelRepository;
import com.tadas.backpackmanagement.service.BagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service("BagService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BagServiceImpl implements BagService {
    BagItemRepository bagItemRepository;
    ItemRepository itemRepository;
    TravelRepository travelRepository;
    BagItemMapper bagItemMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<BagItemView> findAllBagItems(BagQuery query, Pageable pageable) {
        return bagItemRepository.findAllByBagQuery(query, pageable).map(bagItemMapper::toBagItemView);
    }

    @Override
    @Transactional
    public void formatBag(BagPayload payload) {
        Travel travel = travelRepository.findById(payload.getTravelId()).orElseThrow();
        List<Item> items = itemRepository.findAllByIds(payload.getItemIds());

        List<BagItem> bagItems = items.stream().map(e -> BagItem.builder().item(e).travel(travel).build()).collect(Collectors.toList());

        List<BagItem> countedBagItems = bagItems.parallelStream().map(this::toCountedBagItem).toList();

        bagItemRepository.saveAll(countedBagItems);
    }

    private BagItem toCountedBagItem(BagItem bagItem) {
        // if provide_kilometers == NULL means item provides infinity amount off kilometers i.e. tent
        if (bagItem.getItem().getProvideKilometers() == null) {
            bagItem.setQuantity(1);
            return bagItem;
        }

        int quantity = 0;
        double kilometers = bagItem.getItem().getProvideKilometers();

        double travelKilometers = bagItem.getTravel().getDistanceKilometers();
        double itemKilometers = bagItem.getItem().getProvideKilometers();

        while (kilometers <= travelKilometers) {
            kilometers += itemKilometers;
            quantity++;
        }

        bagItem.setQuantity(quantity);
        return bagItem;
    }
}
