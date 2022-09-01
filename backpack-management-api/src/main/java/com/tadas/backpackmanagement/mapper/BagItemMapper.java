package com.tadas.backpackmanagement.mapper;

import com.tadas.backpackmanagement.entity.BagItem;
import com.tadas.backpackmanagement.model.view.BagItemView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class BagItemMapper {
    @Autowired
    TravelMapper travelMapper;

    @Autowired
    ItemMapper itemMapper;

    @Mapping(target = "travel", expression = "java(travelMapper.toTravelView(entity.getTravel()))")
    @Mapping(target = "item", expression = "java(itemMapper.toItemView(entity.getItem()))")
    public abstract BagItemView toBagItemView(BagItem entity);

}