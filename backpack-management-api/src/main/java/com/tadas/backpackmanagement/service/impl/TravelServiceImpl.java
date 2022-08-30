package com.tadas.backpackmanagement.service.impl;

import com.tadas.backpackmanagement.entity.Travel;
import com.tadas.backpackmanagement.mapper.TravelMapper;
import com.tadas.backpackmanagement.model.view.TravelView;
import com.tadas.backpackmanagement.repository.TravelRepository;
import com.tadas.backpackmanagement.service.TravelService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service("TravelService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TravelServiceImpl implements TravelService {
    TravelRepository travelRepository;
    TravelMapper travelMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<TravelView> findAllTravels(Pageable pageable) {
        return travelRepository.findAll(pageable).map(travelMapper::toTravelView);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelView> findAllTravels() {
        return travelRepository.findAll().stream().map(travelMapper::toTravelView).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveTravel(TravelView view) {
        if (view.getDistanceKilometers() != null && view.getDistanceKilometers() > 0) {
            travelRepository.save(travelMapper.buildTravelEntity(view));
        } else {
            throw new RuntimeException(String.format("DistanceKilometers %s must be more than 0.", view.getDistanceKilometers()));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Double countTravelBagWeight(Long id) {
        Travel travel = travelRepository.findById(id).orElseThrow();

        return travel.getBagItems().stream()
                .map(i -> i.getItem().getWeight() * i.getQuantity())
                .reduce((double) 0, Double::sum);
    }
}
