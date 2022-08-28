package com.tadas.backpackmanagement.mapper;

import com.tadas.backpackmanagement.entity.Travel;
import com.tadas.backpackmanagement.model.view.TravelView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TravelMapper {
    @Autowired
    TravelerMapper travelerMapper;

    @Mapping(target = "traveler", expression = "java(travelerMapper.toTravelerView(entity.getTraveler()))")
    public abstract TravelView toTravelView(Travel entity);

    @Mapping(target = "traveler", expression = "java(travelerMapper.buildTravelerEntity(view.getTraveler()))")
    public abstract Travel buildTravelEntity(TravelView view);
}