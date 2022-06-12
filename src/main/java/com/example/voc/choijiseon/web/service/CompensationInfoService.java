package com.example.voc.choijiseon.web.service;

import com.example.voc.choijiseon.domain.CompensationInfo;
import com.example.voc.choijiseon.domain.Voc;
import com.example.voc.choijiseon.domain.repository.CompensationInfoRepository;
import com.example.voc.choijiseon.domain.repository.VocRepository;
import com.example.voc.choijiseon.web.dto.CompensationInfoRequestDto;
import com.example.voc.choijiseon.web.dto.CompensationInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CompensationInfoService {

    private final CompensationInfoRepository compensationInfoRepository;
    private final ModelMapper modelMapper;

    //배상정보 등록
        public CompensationInfoResponseDto getCompensationInfo(CompensationInfoRequestDto compensationInfoRequestDto, Voc voc) {
            compensationInfoRequestDto.setVoc(voc);
            CompensationInfo compensationInfo = modelMapper.map(compensationInfoRequestDto, CompensationInfo.class);
            CompensationInfo newCompensationInfo = compensationInfoRepository.save(compensationInfo);
            return modelMapper.map(newCompensationInfo, CompensationInfoResponseDto.class);
        }
    //배상정보 목록 조회
    public List<CompensationInfoResponseDto> getCompensationInfoList() {
        List<CompensationInfo> compensationInfoList = compensationInfoRepository.findAll();
        List<CompensationInfoResponseDto> responseDtos = compensationInfoList.
                stream().map(list -> modelMapper.map(list, CompensationInfoResponseDto.class)).collect(Collectors.toList());
        return responseDtos;
    }

    //id로 배상정보 조회
    public CompensationInfoResponseDto getCompensationInfo(Long id) {
        CompensationInfo compensationInfo = compensationInfoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        CompensationInfoResponseDto compensationInfoResponseDto = modelMapper.map(compensationInfo, CompensationInfoResponseDto.class);
        return compensationInfoResponseDto;
    }

}

