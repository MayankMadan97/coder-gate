package com.github.codergate.services;

import com.github.codergate.entities.ThresholdEntity;
import com.github.codergate.repositories.ThresholdRepository;
import org.springframework.stereotype.Service;

@Service
public class ThresholdService {
    ThresholdRepository thresholdRepository;
    public void createThreshold() {
        ThresholdEntity thresholdEntity = new ThresholdEntity();
        thresholdRepository.save(thresholdEntity);
    }

    public void convertDTOToEntity() {
        ThresholdEntity thresholdEntity = new ThresholdEntity();
    }

    public void convertEntityToDTO(ThresholdEntity thresholdEntity) {

    }
}
