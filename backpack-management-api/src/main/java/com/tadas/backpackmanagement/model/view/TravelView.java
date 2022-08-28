package com.tadas.backpackmanagement.model.view;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class TravelView {
    Long id;
    String name;
    Double distanceKilometers;
    TravelerView traveler;
}
