package com.tadas.backpackmanagement.mapper;

import com.tadas.backpackmanagement.entity.Item;
import com.tadas.backpackmanagement.model.view.ItemView;
import com.tadas.backpackmanagement.model.view.PreselectedItemView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ItemMapper {
    public abstract ItemView toItemView(Item entity);

    public abstract Item buildItemEntity(ItemView view);

    @Mapping(target = "selected", source = "isMandatory")
    @Mapping(target = "disabled", source = "isMandatory")
    public abstract PreselectedItemView toPreselectedItemView(Item entity);
}