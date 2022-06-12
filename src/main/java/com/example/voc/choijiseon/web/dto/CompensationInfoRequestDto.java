package com.example.voc.choijiseon.web.dto;

import com.example.voc.choijiseon.domain.Voc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompensationInfoRequestDto {

    //운송사 정보
    //고객사 정보
    //voc 정보
    @NotNull
    private Voc voc;
    @NotBlank
    private String transportCompany; //운송 회사 이름
    @NotBlank
    private Long deliveryDriverId; //운송 기사 아이디



}

