package com.tadas.backpackmanagement.mapper;

import com.tadas.backpackmanagement.entity.Traveler;
import com.tadas.backpackmanagement.model.view.TravelerView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TravelerMapper {
    public abstract TravelerView toTravelerListView(Traveler entity);

    public abstract Traveler buildTravelerEntity(TravelerView view);
}