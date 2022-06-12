package com.example.voc.choijiseon.web.controller;

import com.example.voc.choijiseon.domain.Voc;
import com.example.voc.choijiseon.domain.repository.CompensationInfoRepository;
import com.example.voc.choijiseon.web.dto.CompensationInfoRequestDto;
import com.example.voc.choijiseon.web.dto.CompensationInfoResponseDto;
import com.example.voc.choijiseon.web.service.CompensationInfoService;
import com.example.voc.choijiseon.web.service.VocService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/compensation")
@RestController
public class CompensationInfoController {
    private final CompensationInfoService compensationInfoService;
    private final VocService vocService;

    //배상 정보 등록
    //배상 요청 여부가 확인되면 배상을 접수한다.
    @PostMapping("/voc/{id}")
    public ResponseEntity createCompensationInfo(@PathVariable Long id, @RequestBody CompensationInfoRequestDto compensationInfoRequestDto) {
        Voc voc = vocService.getVoc(id);
        if (voc.isAskedForCompensationByClient()) {
            CompensationInfoResponseDto compensationInfo= compensationInfoService.getCompensationInfo(compensationInfoRequestDto, voc);
            return new ResponseEntity<>(compensationInfo, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("배상 요청 여부를 확인하세요.");
    }

    //전체 배상 조회
    @GetMapping
    public ResponseEntity getCompensationInfoList() {
        List<CompensationInfoResponseDto> responseDtos = compensationInfoService.getCompensationInfoList();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    //id로 배상 조회
    @GetMapping("/{id}")
    public ResponseEntity getCompensationInfo(@PathVariable Long id) {
        CompensationInfoResponseDto compensationInfoResponseDto = compensationInfoService.getCompensationInfo(id);
        return new ResponseEntity<>(compensationInfoResponseDto, HttpStatus.OK);
    }




}
