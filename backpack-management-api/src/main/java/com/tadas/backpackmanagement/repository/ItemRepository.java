package com.tadas.backpackmanagement.repository;

import com.tadas.backpackmanagement.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("select item from Item item where item.id in :ids")
    List<Item> findAllByIds(List<Long> ids);
}