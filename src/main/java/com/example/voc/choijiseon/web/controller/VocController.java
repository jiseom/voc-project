package com.example.voc.choijiseon.web.controller;

import com.example.voc.choijiseon.domain.Voc;
import com.example.voc.choijiseon.web.dto.VocRequestDto;
import com.example.voc.choijiseon.web.dto.VocResponseDto;
import com.example.voc.choijiseon.web.service.VocService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/voc")
public class VocController {
    private final VocService vocService;

    // 고객사에서 클레임 인입을 하면 voc를 등록한다.
    // voc 등록 api
    @PostMapping
    public ResponseEntity createVoc(@RequestBody VocRequestDto VocRequestDto) {
        VocResponseDto vocResponseDto = vocService.createVoc(VocRequestDto);
        return new ResponseEntity<>(vocResponseDto, HttpStatus.OK);
    }


    //배상 요청 여부
    //고객사에서 voc를 확인하고 배상 요청을 한다.
    @PatchMapping("/{id}")
    public ResponseEntity compensationRequest(@PathVariable Long id) {
        VocResponseDto vocResponseDto = vocService.getCompensationRequest(id);
        return new ResponseEntity<>(vocResponseDto, HttpStatus.OK);
    }

    //전체 voc 목록 조회
    @GetMapping
    public ResponseEntity vocList() {
        List<Voc> vocList = vocService.getVocList();
        return new ResponseEntity<>(vocList, HttpStatus.OK);
    }

    //voc 개별조회 (id 로 조회)
    @GetMapping("/{id}")
    public ResponseEntity getVoc(@PathVariable("id") Long id) {
        VocResponseDto responseDto = vocService.findVoc(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
