package com.tadas.backpackmanagement.repository;

import com.tadas.backpackmanagement.entity.BagItem;
import com.tadas.backpackmanagement.model.query.BagQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BagItemRepository extends JpaRepository<BagItem, Long> {
    @Query(
            "from BagItem bagItem " +
                    " where (:#{#query.getItemId()} is null or exists( select item from Item item where item.id = :#{#query.getItemId()} and bagItem.item = item )) " +
                    " and (:#{#query.getTravelId()} is null or exists( select travel from Travel travel where travel.id = :#{#query.getTravelId()} AND bagItem.travel = travel )) "+
                    " and (:#{#query.getTravelerId()} is null or exists( select traveler from Traveler traveler where traveler.id = :#{#query.getTravelerId()} AND bagItem.travel.traveler = traveler ))"
    )
    Page<BagItem> findAllByBagQuery(BagQuery query, Pageable pageable);

    @Modifying
    @Query(
            "delete from BagItem bagItem " +
                    " where bagItem.id in :ids"
    )
    void deleteAllByIds(List<Long> ids);
}