package com.example.voc.choijiseon.web.controller;

import com.example.voc.choijiseon.domain.CompensationInfo;
import com.example.voc.choijiseon.domain.DeliveryDriver;
import com.example.voc.choijiseon.domain.Penalty;
import com.example.voc.choijiseon.domain.repository.CompensationInfoRepository;
import com.example.voc.choijiseon.domain.repository.DeliveryDriverRepository;
import com.example.voc.choijiseon.domain.repository.PenaltyRepository;
import com.example.voc.choijiseon.web.dto.PenaltyRequestDto;
import com.example.voc.choijiseon.web.dto.PenaltyResponseDto;
import com.example.voc.choijiseon.web.service.DeliveryDriverService;
import com.example.voc.choijiseon.web.service.PenaltyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/penalty")
@RestController
public class PenaltyController {

    private final PenaltyService penaltyService;
    private final DeliveryDriverService deliveryDriverService;

    //패널티 등록
    //배상 시스템이 적용되면 패널티(비용청구)를 등록한다.
    //app push 하였습니다.
    //기사 id , 배상 id
    @PostMapping("/compensation/{id}")
    public ResponseEntity createPenalty(@PathVariable Long id, @RequestBody PenaltyRequestDto penaltyRequestDto) {
        log.info("APP PUSH");
        PenaltyResponseDto penaltyResponseDto = penaltyService.createPenaltyResponseDto(id, penaltyRequestDto);
        return new ResponseEntity<>(penaltyResponseDto, HttpStatus.OK);
    }

    //기사 귀책 인정 여부
    @PatchMapping("/{id}")
    public ResponseEntity getPenalty(@PathVariable Long id) {
        PenaltyResponseDto responseDto = penaltyService.getPenaltyResponseDto(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 기사가 귀책 인정 후 프로세스
    @PatchMapping("/admin/{id}")
    public ResponseEntity penaltyProcess(@PathVariable Long id) {
        Penalty penalty = penaltyService.findById(id);
        Long deliveryDriverId = penalty.getCompensationInfo().getDeliveryDriverId(); //패널티 id 로 운송기사 id 찾기
        DeliveryDriver deliveryDriver = deliveryDriverService.getDeliveryDriver(deliveryDriverId); //운송기사 id로 객체 가져오기
        penalty.setDeliveryDriver(deliveryDriver); //찾은 후 패널티에 세팅

        if (penalty.isPenaltyCheckedByVocTarget() && deliveryDriver.getId().equals(deliveryDriverId)) {
            //운송기사가 패널티 귀책 인정 하고 패널티의 운송기사 id와 운송기사 객체의 id가 같은지
            log.info("귀책 인정");
            penaltyService.acceptedPenaltyProcess(penalty, deliveryDriver); //패널티 적용 프로세스
            return ResponseEntity.ok().body("패널티가 차감되었습니다.");
        }
        return ResponseEntity.badRequest().body("이의제기를 원하실 경우 문의해주세요."); // 기사가 이의제기할 경우
    }

    //패널티 전체 조회
    @GetMapping
    public ResponseEntity getPenaltyList() {
        List<PenaltyResponseDto> responseDtos = penaltyService.getPenaltyResponseDtos();
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }



}
