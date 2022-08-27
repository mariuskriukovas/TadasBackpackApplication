package com.tadas.backpackmanagement.mapper;

import com.tadas.backpackmanagement.entity.Traveler;
import com.tadas.backpackmanagement.model.view.TravelerListView;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class TravelerMapper {
    public abstract TravelerListView toTravelerListView(Traveler entity);
}