package com.tadas.backpackmanagement.service.impl;

import com.tadas.backpackmanagement.mapper.ItemMapper;
import com.tadas.backpackmanagement.model.view.ItemView;
import com.tadas.backpackmanagement.repository.ItemRepository;
import com.tadas.backpackmanagement.service.ItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("ItemService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ItemServiceImpl implements ItemService {
    ItemRepository itemRepository;
    ItemMapper itemMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ItemView> findAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable).map(itemMapper::toItemView);
    }

    @Override
    @Transactional
    public void saveItem(ItemView view) {
        itemRepository.save(itemMapper.buildItemEntity(view));
    }
}
