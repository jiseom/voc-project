package com.example.voc.choijiseon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //패널티 비용
    private int penalty;

    @OneToOne //일대일 단방향
    @JoinColumn(name = "COMPENSATION_INFO_ID")
    private CompensationInfo compensationInfo;

    //귀책 인정 여부
    private boolean penaltyCheckedByVocTarget;

    //이의 제기 여부
    private boolean vocObjectedByVocTarget;

    @OneToOne
    private DeliveryDriver deliveryDriver;


    //비용청구 후 월급에서 차감
    public int getCurrentSalary(int penalty, int salary) {
        return salary - penalty;
    }


}
