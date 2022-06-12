package com.example.voc.choijiseon.web.service;

import com.example.voc.choijiseon.domain.DeliveryDriver;
import com.example.voc.choijiseon.domain.repository.DeliveryDriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class DeliveryDriverService {

    private final DeliveryDriverRepository deliveryDriverRepository;

    //운송기사 id 가져오기
    public DeliveryDriver getDeliveryDriver(Long deliveryDriverId) {
        return deliveryDriverRepository.findById(deliveryDriverId).orElseThrow(IllegalAccessError::new);
    }
}

