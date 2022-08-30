package com.tadas.backpackmanagement.model.view;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class BagItemView {
    Long id;
    Integer quantity;
    TravelView travel;
    ItemView item;
}
