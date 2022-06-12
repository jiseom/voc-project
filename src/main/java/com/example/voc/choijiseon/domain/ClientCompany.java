package com.example.voc.choijiseon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class ClientCompany {

    //고객사 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //회사명
    private String name;

    //연락처
    private String tel;

    //귀책 당사자 코드
    private String companyCode = "CLIENT_COMPANY";


}
