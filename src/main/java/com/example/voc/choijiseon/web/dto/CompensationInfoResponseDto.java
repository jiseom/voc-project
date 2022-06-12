package com.example.voc.choijiseon.web.dto;

import com.example.voc.choijiseon.domain.Voc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompensationInfoResponseDto {
    private Long id;

    private String transportCompany;

    private Long deliveryDriverId;

    private Voc voc; //voc 정보


}
