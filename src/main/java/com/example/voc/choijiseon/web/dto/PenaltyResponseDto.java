package com.example.voc.choijiseon.web.dto;

import com.example.voc.choijiseon.domain.CompensationInfo;
import com.example.voc.choijiseon.domain.Penalty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PenaltyResponseDto {

    private Long id;

    private Penalty penalty;

    private CompensationInfo compensationInfo;

}
