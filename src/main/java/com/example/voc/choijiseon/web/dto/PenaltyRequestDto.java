package com.example.voc.choijiseon.web.dto;

import com.example.voc.choijiseon.domain.CompensationInfo;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PenaltyRequestDto {

    private int penalty;

    private CompensationInfo compensationInfo;

}

