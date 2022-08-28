package com.tadas.backpackmanagement.model.view;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class ItemView {
    Long id;
    String name;
    Boolean isMandatory;
    Double weight;
    Double provideKilometers;
}
