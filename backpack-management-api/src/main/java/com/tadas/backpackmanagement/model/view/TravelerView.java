package com.tadas.backpackmanagement.model.view;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class TravelerView {
    Long id;
    String name;
}
