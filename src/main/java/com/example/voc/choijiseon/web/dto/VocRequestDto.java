package com.example.voc.choijiseon.web.dto;

import com.example.voc.choijiseon.domain.nonentity.VocTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VocRequestDto {
    private VocTarget vocTarget;
    private String vocContent;
    private Long clientCompanyId;
}
