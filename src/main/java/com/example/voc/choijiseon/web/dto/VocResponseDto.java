package com.example.voc.choijiseon.web.dto;

import com.example.voc.choijiseon.domain.CompensationInfo;
import com.example.voc.choijiseon.domain.nonentity.VocTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VocResponseDto {

    private Long id;

    //귀책 당사자

    private VocTarget vocTarget;

    //귀책 내용
    private String vocContent;

    //패널티 내용

//    private Penalty penalty;

    private List<CompensationInfo> compensationInfoList;

    //기사 확인 여부
    private boolean penaltyCheckedByCourier;

    //이의 제기 여부
    private boolean vocObjectedByCourier;


}
