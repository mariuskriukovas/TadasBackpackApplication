package com.tadas.backpackmanagement.model.payload;

import lombok.Data;

import java.util.List;

@Data
public class BagPayload {
    Long travelId;
    List<Long> itemIds;
}
