package com.example.voc.choijiseon.domain;

import com.example.voc.choijiseon.domain.nonentity.VocTarget;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Voc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="VOC_ID")
    private Long id;

    //귀책 당사자
    @Enumerated(EnumType.STRING)
    private VocTarget vocTarget;

    //귀책 내용
    private String vocContent;

    //배상 정보 voc 한 건당 여러개의 배상정보가 올 수 있다고 가정할 시,
    @JsonManagedReference
    @OneToMany(mappedBy="voc")
    private List<CompensationInfo> compensationInfo = new ArrayList<CompensationInfo>();

    //고객사 정보
    private Long clientCompanyId;

    //배상 요청 여부
    private boolean askedForCompensationByClient;





}
