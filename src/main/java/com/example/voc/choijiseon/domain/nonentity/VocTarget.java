package com.example.voc.choijiseon.domain.nonentity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum VocTarget {

    CLIENT_COMPANY("고객사 귀책","고객사 물건 누락"),
    TRANSPORT_COMPANY("운송사 귀책","오배송 또는 지연 배송");

    private final String title;
    private final String value;

}
