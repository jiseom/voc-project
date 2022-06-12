package com.example.voc.choijiseon.web.service;

import com.example.voc.choijiseon.domain.CompensationInfo;
import com.example.voc.choijiseon.domain.DeliveryDriver;
import com.example.voc.choijiseon.domain.Penalty;
import com.example.voc.choijiseon.domain.repository.CompensationInfoRepository;
import com.example.voc.choijiseon.domain.repository.DeliveryDriverRepository;
import com.example.voc.choijiseon.domain.repository.PenaltyRepository;
import com.example.voc.choijiseon.web.dto.PenaltyRequestDto;
import com.example.voc.choijiseon.web.dto.PenaltyResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PenaltyService {

    private final CompensationInfoRepository compensationInfoRepository;
    private final PenaltyRepository penaltyRepository;
    private final DeliveryDriverRepository deliveryDriverRepository;
    private final ModelMapper modelMapper;

    //패널티 등록
    public PenaltyResponseDto createPenaltyResponseDto(Long id, PenaltyRequestDto penaltyRequestDto) {
        CompensationInfo compensationInfo = compensationInfoRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
        penaltyRequestDto.setCompensationInfo(compensationInfo);
        Penalty penalty = modelMapper.map(penaltyRequestDto, Penalty.class);
        Penalty save = penaltyRepository.save(penalty);
        return modelMapper.map(save, PenaltyResponseDto.class);
    }
    //기사 귀책 인정 여부
    public PenaltyResponseDto getPenaltyResponseDto(Long id) {
        Penalty penalty = penaltyRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
        penalty.setPenaltyCheckedByVocTarget(true);
        Penalty newPenalty = penaltyRepository.save(penalty);
        return modelMapper.map(newPenalty, PenaltyResponseDto.class);
    }
    //귀책 인정 후 프로세스
    public void acceptedPenaltyProcess(Penalty penalty, DeliveryDriver deliveryDriver) {
        int currentSalary = penalty.getCurrentSalary(penalty.getPenalty(), deliveryDriver.getSalary());
        deliveryDriver.setSalary(currentSalary);
        deliveryDriverRepository.save(deliveryDriver);
    }
    //패널티 전체 목록
    public List<PenaltyResponseDto> getPenaltyResponseDtos() {
        List<Penalty> penaltyList = penaltyRepository.findAll();
        return penaltyList.stream()
                .map(list -> modelMapper.map(list, PenaltyResponseDto.class))
                .collect(Collectors.toList());
    }


    public Penalty findById(Long id) {
        return penaltyRepository.findById(id)
                .orElseThrow(IllegalAccessError::new);
    }

}
