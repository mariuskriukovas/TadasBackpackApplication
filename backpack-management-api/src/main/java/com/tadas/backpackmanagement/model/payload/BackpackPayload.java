package com.tadas.backpackmanagement.model.payload;

import lombok.Data;

import java.util.List;

@Data
public class BackpackPayload {
    Long travelId;
    List<Long> itemIds;
}
