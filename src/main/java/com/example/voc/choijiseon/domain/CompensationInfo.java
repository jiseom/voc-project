package com.example.voc.choijiseon.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "compensation_info")
public class CompensationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPENSATION_INFO_ID")
    private Long id;

    private String transportCompany;

    private Long deliveryDriverId;

    //private Member 귀책당사자; (운송사정보, 고객사 정보 ,voc 정보)

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "VOC_ID")
    private Voc voc; //voc 정보


}
