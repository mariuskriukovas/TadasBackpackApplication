package com.tadas.backpackmanagement.service.impl;

import com.tadas.backpackmanagement.mapper.TravelerMapper;
import com.tadas.backpackmanagement.model.view.TravelerView;
import com.tadas.backpackmanagement.repository.TravelerRepository;
import com.tadas.backpackmanagement.service.TravelerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service("TravelerService")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TravelerServiceImpl implements TravelerService {
    TravelerRepository travelerRepository;
    TravelerMapper travelerMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<TravelerView> findAllTravelers(Pageable pageable) {
        return travelerRepository.findAll(pageable).map(travelerMapper::toTravelerView);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TravelerView> findAllTravelers() {
        return travelerRepository.findAll().stream().map(travelerMapper::toTravelerView).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveTraveler(TravelerView view) {
        travelerRepository.save(travelerMapper.buildTravelerEntity(view));
    }
}
