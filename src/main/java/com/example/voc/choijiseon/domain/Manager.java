package com.example.voc.choijiseon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Manager {

    //고객사 담당자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //고객사 담당자 성함
    private String tel;

    @ManyToOne
    private ClientCompany clientCompany;
}
