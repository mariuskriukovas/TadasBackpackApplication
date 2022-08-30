package com.tadas.backpackmanagement.model.view;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
public class PreselectedItemView {
    Long id;
    String name;
    Boolean selected;
    Boolean disabled;
}
