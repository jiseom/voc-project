package com.example.voc.choijiseon.web.service;

import com.example.voc.choijiseon.domain.Voc;
import com.example.voc.choijiseon.domain.repository.VocRepository;
import com.example.voc.choijiseon.web.dto.VocRequestDto;
import com.example.voc.choijiseon.web.dto.VocResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@RequiredArgsConstructor
@Transactional
@Service
public class VocService {

    private final VocRepository vocRepository;
    private final ModelMapper modelMapper;

    //voc 등록
    public VocResponseDto createVoc(VocRequestDto VocRequestDto) {
        Voc voc = modelMapper.map(VocRequestDto, Voc.class);
        Voc newVoc = vocRepository.save(voc);
        return modelMapper.map(newVoc, VocResponseDto.class);
    }

    //고객사로부터 배상 요청이 들어옴
    public VocResponseDto getCompensationRequest(Long id) {
        Voc voc = getVoc(id);
        voc.setAskedForCompensationByClient(true);
        Voc newVoc = vocRepository.save(voc);
       return modelMapper.map(newVoc, VocResponseDto.class);
    }
    //전체 voc 조회
    public List<Voc> getVocList() {
       return vocRepository.findAll();
    }

    //개별 voc 조회
    public VocResponseDto findVoc(Long id) {
        Voc voc = getVoc(id);
        return modelMapper.map(voc, VocResponseDto.class);
        }

    public Voc getVoc(Long id) {
        return vocRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}


